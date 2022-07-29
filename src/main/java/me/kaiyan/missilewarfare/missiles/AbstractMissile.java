package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public abstract class AbstractMissile implements Missile {

    Vector position;
    Vector direction;
    int speed;

    Entity armour_stand;
    TargetObject target_object;

    int power;
    int max_speed;
    int maneuverability;


    @Override
    public Vector getPosition() {
        return this.position;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public Vector getDirection() {
        return this.direction;
    }

    @Override
    public Vector getVelocity() {
        return this.getDirection().multiply(this.getSpeed());
    }

    @Override
    public Entity getArmourStand() {
        return this.armour_stand;
    }

    @Override
    public TargetObject getTargetObject() {
        return this.target_object;
    }

    public int getPower() {
        return this.power;
    }

    public int getMaxSpeed() {
        return this.max_speed;
    }

    public int getManeuverability() {
        return this.maneuverability;
    }
}
