package me.kaiyan.missilewarfare.missiles.flightpattern.sampattern;

import me.kaiyan.missilewarfare.missiles.Missile;
import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.MissileTargetObject;
import org.jetbrains.annotations.NotNull;

public class SAMLaunchPattern extends AbstractPattern<MissileTargetObject> {
    public SAMLaunchPattern(@NotNull Missile<MissileTargetObject> missile) {
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
        int fourthsToTurn = 90 / missile.getManeuverability();
        int leeway = fourthsToTurn * missile.getMaxSpeed();

        double targetHeight = target.getTargetObjectLocation().getY();
        double objectiveHeight = targetHeight - leeway;

        this.calculatePhysics();

        if (missile.getLocation().getY() >= objectiveHeight) {
            this.cancel();
            missile.getFlightPattern().activate();
        }
    }
}
