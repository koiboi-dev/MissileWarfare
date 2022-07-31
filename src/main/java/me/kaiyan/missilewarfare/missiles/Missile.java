package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.flightpattern.AbstractPattern;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public interface Missile<T extends TargetObject<?>> {

    public Location getLocation();

    public void setLocation(Location location);

    public int getSpeed();

    public void setSpeed(int integer);

    public Vector getVelocity();

    public int getMaxRange();

    public int getRemainingRange();

    public void setRemainingRange(int remaining_range);

    public Entity getArmourStand();

    public T getTargetObject();

    public int getPower();

    public int getMaxSpeed();

    public int getManeuverability();

    public AbstractPattern getLaunchPattern();

    public AbstractPattern getFlightPattern();

    public void fire(T target, Location location);

    public void detonate();
}
