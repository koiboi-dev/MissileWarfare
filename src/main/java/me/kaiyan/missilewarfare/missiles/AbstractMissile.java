package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public abstract class AbstractMissile implements Missile {

    Vector position;
    Vector direction;
    int speed;

    World world;

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
    public void setPosition(Vector pos) {
        this.position = pos;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int integer) {
        this.speed = integer;
    }

    @Override
    public Vector getDirection() {
        return this.direction;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void setWorld(World world) {
        this.world  = world;
    }

    @Override
    public void setDirection(Vector vector) {
        this.direction = vector;
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
