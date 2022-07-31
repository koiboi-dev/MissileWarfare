package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public abstract class AbstractMissile<T extends TargetObject<?>> implements Missile<T> {

    Location location;
    int speed;
    int max_range;
    int remaining_range;

    Entity armour_stand;
    T target_object;

    AbstractPattern<T> launch_pattern;
    AbstractPattern<T> flight_pattern;

    int power;
    int max_speed;
    int maneuverability;

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
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
    public Vector getVelocity() {
        return this.location.getDirection().multiply(this.speed);
    }

    @Override
    public int getMaxRange() {
        return this.max_range;
    }

    @Override
    public int getRemainingRange() {
        return this.remaining_range;
    }

    @Override
    public void setRemainingRange(int remaining_range) {
        this.remaining_range = remaining_range;
    }

    @Override
    public Entity getArmourStand() {
        return this.armour_stand;
    }

    @Override
    public T getTargetObject() {
        return this.target_object;
    }

    public int getPower() {
        return this.power;
    }

    public int getMaxSpeed() {
        return this.max_speed;
    }

    public AbstractPattern<T> getLaunchPattern() {
        return this.launch_pattern;
    }

    public AbstractPattern<T> getFlightPattern() {
        return this.flight_pattern;
    }

    public int getManeuverability() {
        return this.maneuverability;
    }

    public void fire(T target, Location location) {
        this.getLaunchPattern().activate();
    }

    public void detonate() {

    }
}
