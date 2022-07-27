package me.kaiyan.missilewarfare.missiles.target;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class StationaryTargetObject implements TargetObject<Location> {
    Location target_location;

    public StationaryTargetObject(Location target_location) {
        this.target_location = target_location;
    }

    @Override
    public TargetObjectType getTargetObjectType() {
        return TargetObjectType.STATIONARY;
    }

    @Override
    public Vector getTargetObjectVelocity() {
        return new Vector(0, 0, 0);
    }

    @Override
    public Vector getTargetObjectLocation() {
        return this.target_location.toVector();
    }

    @Override
    public Location getTargetObject() {
        return this.target_location;
    }
}
