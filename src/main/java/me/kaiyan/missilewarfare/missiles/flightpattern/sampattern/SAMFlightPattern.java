package me.kaiyan.missilewarfare.missiles.flightpattern.sampattern;

import me.kaiyan.missilewarfare.missiles.Missile;
import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.MissileTargetObject;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class SAMFlightPattern extends AbstractPattern<MissileTargetObject> {
    public SAMFlightPattern(@NotNull Missile<MissileTargetObject> missile) {
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
        double distanceByTarget = target.getTargetObject().getSpeed() * time;
        // the vector of the travel of the target
        Vector targetTravel = target.getTargetObjectLocation().getDirection().multiply(distanceByTarget);
        // the vector representing the point where the target will be
        Vector targetPoint = target.getTargetObjectLocation().toVector().add(targetTravel);

        // draw a vector between the target and our position and normalise it
        // so we can use the vector to find the Euler Angle we need to face the
        // target.
        Vector vectorToTarget = targetPoint.subtract(missile.getLocation().toVector());
        vectorToTarget.normalize();

        // The Vector to Euler Angle conversion provided by
        // Char on the Slimefun discord. Thank you char!
        double yaw = StrictMath.atan(vectorToTarget.getZ() / vectorToTarget.getX());
        double pitch = StrictMath.asin(vectorToTarget.getY());

        // Well, all that's left is to direct the missile towards our target point
        Location mutableLocation = missile.getLocation().clone();
        mutableLocation.setYaw((float) yaw);
        mutableLocation.setPitch((float) pitch);
        missile.setLocation(mutableLocation);

        // and finally detonate if out of fuel or will be out of fuel. Don't want
        // the SAM falling onto the base its protecting!
        if (missile.getRemainingRange() < missile.getVelocity().length()) {
            missile.detonate();
        }
    }
}
