package me.kaiyan.missilewarfare.missiles.flightpattern.sampattern;

import me.kaiyan.missilewarfare.missiles.Missile;
import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.MissileTargetObject;
import org.bukkit.util.Vector;

public class SAMLaunchPattern extends AbstractPattern<MissileTargetObject> {
    public SAMLaunchPattern(Missile<MissileTargetObject> missile) {
        super(missile);
    }

    @Override
    public void run() {
        /*
        ========================================================
        SAM Launch Pattern Algorithm, ck

        As with every other pattern, this works every 5 ticks.
        Every 5 ticks, this will get the location of the targeted missile,
        take its height, and set its objective height to the targeted missile's
        height minus the leeway required for it to perform a 90-degree turn
        depending on its manoeuvrability.
        Once it is at its objective height it will hand control over to the
        flight pattern.
        ========================================================
         */

        Missile<MissileTargetObject> missile = this.getMissile();
        MissileTargetObject target = missile.getTargetObject();

        // we want the boy to be zooming as it launches
        missile.setSpeed(missile.getMaxSpeed());

        // how many fourth of a second would it take to turn 90-degrees
        int fourths_to_turn = 90 / missile.getManeuverability();
        int leeway = fourths_to_turn * missile.getMaxSpeed();

        double target_height = target.getTargetObjectLocation().getY();
        double objective_height = target_height - leeway;

        this.calculatePhysics();

        if(missile.getLocation().getY() >= objective_height) {
            this.cancel();
            missile.getFlightPattern().activate();
        }

    }
}
