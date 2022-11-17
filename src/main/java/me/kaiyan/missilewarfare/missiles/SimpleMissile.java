package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class SimpleMissile<T extends TargetObject<?>> implements Missile<T> {

    private final int maxRange;
    private final ArmorStand armourStand;
    private final T target;
    private final AbstractPattern<T> launchPattern;
    private final AbstractPattern<T> flightPattern;
    private final int power;
    private final int maxSpeed;
    private final int maneuverability;
    private Location location;
    private int speed;
    private int remainingRange;

    public SimpleMissile(@NotNull MissileBuilder<T> builder) {
        this.flightPattern = builder.getFlightPattern();
        this.launchPattern = builder.getLaunchPattern();
        this.location = builder.getLocation();
        this.speed = builder.getSpeed();
        this.maxRange = builder.getMaxRange();
        this.remainingRange = builder.getRemainingRange();
        this.armourStand = builder.getArmourStand();
        this.target = builder.getTarget();
        this.power = builder.getPower();
        this.maxSpeed = builder.getMaxSpeed();
        this.maneuverability = builder.getManeuverability();
    }

    @Override
    public @NotNull Location getLocation() {
        return location;
    }

    @Override
    public void setLocation(@NotNull Location location) {
        this.location = location;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public @NotNull Vector getVelocity() {
        return this.location.getDirection().multiply(this.speed);
    }

    @Override
    public int getMaxRange() {
        return this.maxRange;
    }

    @Override
    public int getRemainingRange() {
        return this.remainingRange;
    }

    @Override
    public void setRemainingRange(int remainingRange) {
        this.remainingRange = remainingRange;
    }

    @Override
    public ArmorStand getArmourStand() {
        return this.armourStand;
    }

    @Override
    public @NotNull T getTargetObject() {
        return this.target;
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    @Override
    public int getManeuverability() {
        return this.maneuverability;
    }

    @Override
    public @NotNull AbstractPattern<T> getLaunchPattern() {
        return this.launchPattern;
    }

    @Override
    public @NotNull AbstractPattern<T> getFlightPattern() {
        return this.flightPattern;
    }

    @Override
    public void fire(@NotNull T target, @NotNull Location location) {
        this.getLaunchPattern().activate();
    }

    @Override
    public void detonate() {

    }
}
