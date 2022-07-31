package me.kaiyan.missilewarfare.missiles.target;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface TargetObject<T> {

    public TargetObjectType getTargetObjectType();

    public Vector getTargetObjectVelocity();

    public Location getTargetObjectLocation();

    public T getTargetObject();
}
