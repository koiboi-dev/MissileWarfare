package me.kaiyan.missilewarfare.missiles.target;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public interface TargetObject<T> {

    TargetObjectType getTargetObjectType();

    Vector getTargetObjectVelocity();

    Location getTargetObjectLocation();

    T getTargetObject();
}
