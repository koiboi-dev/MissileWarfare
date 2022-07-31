package me.kaiyan.missilewarfare.missiles.flightpattern.sampattern;

import me.kaiyan.missilewarfare.missiles.Missile;
import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.MissileTargetObject;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class SAMFlightPattern extends AbstractPattern<MissileTargetObject> {
    public SAMFlightPattern(Missile<MissileTargetObject> missile) {
        super(missile);
    }

    @Override
    public void run() {
        /*
        ========================================================
        SAM Guidance Algorithm, ck

        Calculates the amount of time the SAM would take to reach the
        target in a straight line,
        then calculates the position of the target that time ahead and
        adjusts its course towards it.

        It will also check for its fuel, and detonate if it decides it will not
        reach the target in time
        ========================================================
         */

        Missile<MissileTargetObject> missile = this.getMissile();
        MissileTargetObject target = missile.getTargetObject();

        double distance = missile.getLocation().distance(target.getTargetObjectLocation());
        double time = distance / (missile.getSpeed()); // time is in fourths

        // the amount the target will have travelled by the time
        double distance_by_target = target.getTargetObject().getSpeed() * time;
        // the vector of the travel of the target
        Vector target_travel = target.getTargetObjectLocation().getDirection().multiply(distance_by_target);
        // the vector representing the point where the target will be
        Vector target_point = target.getTargetObjectLocation().toVector().add(target_travel);

        // draw a vector between the target and our position and normalise it
        // so we can use the vector to find the Euler Angle we need to face the
        // target.
        Vector vector_to_target = target_point.subtract(missile.getLocation().toVector());
        vector_to_target.normalize();

        // The Vector to Euler Angle conversion provided by
        // Char on the Slimefun discord. Thank you char!
        double yaw = Math.atan(vector_to_target.getZ() / vector_to_target.getX());
        double pitch = Math.asin(vector_to_target.getY());

        // Well, all that's left is to direct the missile towards our target point
        Location mutable_location = missile.getLocation().clone();
        mutable_location.setYaw((float) yaw);
        mutable_location.setPitch((float) pitch);
        missile.setLocation(mutable_location);

        // and finally detonate if out of fuel or will be out of fuel. Don't want
        // the SAM falling onto the base its protecting!
        if(missile.getRemainingRange() < missile.getVelocity().length()) {
            missile.detonate();
        }
    }
}
