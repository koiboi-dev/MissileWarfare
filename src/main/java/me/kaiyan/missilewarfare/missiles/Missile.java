package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface Missile<T extends TargetObject<?>> {

    @NotNull Location getLocation();

    void setLocation(@NotNull Location location);

    int getSpeed();

    void setSpeed(int speed);

    @NotNull Vector getVelocity();

    int getMaxRange();

    int getRemainingRange();

    void setRemainingRange(int remainingRange);

    ArmorStand getArmourStand();

    @NotNull T getTargetObject();

    int getPower();

    int getMaxSpeed();

    int getManeuverability();

    @NotNull AbstractPattern<T> getLaunchPattern();

    @NotNull AbstractPattern<T> getFlightPattern();

    void fire(@NotNull T target, @NotNull Location location);

    void detonate();
}
