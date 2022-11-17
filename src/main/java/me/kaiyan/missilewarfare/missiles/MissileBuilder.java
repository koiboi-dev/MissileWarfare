package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class MissileBuilder<T extends TargetObject<?>> {

    private Location location;
    private int speed;
    private int maxRange;
    private int remainingRange;

    private ArmorStand armourStand;
    private T target;

    private AbstractPattern<T> launchPattern;
    private AbstractPattern<T> flightPattern;

    private int power;
    private int maxSpeed;
    private int maneuverability;

    public Missile<T> build() {
        return new SimpleMissile<>(this);
    }

    public Location getLocation() {
        return location;
    }

    public MissileBuilder<T> setLocation(Location location) {
        this.location = location;
        return this;
    }

    public int getSpeed() {
        return speed;
    }

    public MissileBuilder<T> setSpeed(int speed) {
        this.speed = speed;
        return this;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public MissileBuilder<T> setMaxRange(int maxRange) {
        this.maxRange = maxRange;
        return this;
    }

    public int getRemainingRange() {
        return remainingRange;
    }

    public MissileBuilder<T> setRemainingRange(int remainingRange) {
        this.remainingRange = remainingRange;
        return this;
    }

    public ArmorStand getArmourStand() {
        return armourStand;
    }

    public MissileBuilder<T> setArmourStand(ArmorStand armourStand) {
        this.armourStand = armourStand;
        return this;
    }

    public T getTarget() {
        return target;
    }

    public MissileBuilder<T> setTarget(T target) {
        this.target = target;
        return this;
    }

    public AbstractPattern<T> getLaunchPattern() {
        return launchPattern;
    }

    public MissileBuilder<T> setLaunchPattern(AbstractPattern<T> launchPattern) {
        this.launchPattern = launchPattern;
        return this;
    }

    public AbstractPattern<T> getFlightPattern() {
        return flightPattern;
    }

    public MissileBuilder<T> setFlightPattern(AbstractPattern<T> flightPattern) {
        this.flightPattern = flightPattern;
        return this;
    }

    public int getPower() {
        return power;
    }

    public MissileBuilder<T> setPower(int power) {
        this.power = power;
        return this;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public MissileBuilder<T> setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    public int getManeuverability() {
        return maneuverability;
    }

    public MissileBuilder<T> setManeuverability(int maneuverability) {
        this.maneuverability = maneuverability;
        return this;
    }


}
