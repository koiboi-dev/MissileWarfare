package me.kaiyan.missilewarfare.missiles.target;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class StationaryTargetObject implements TargetObject<Location> {
    private final @NotNull Location targetLocation;

    public StationaryTargetObject(@NotNull Location targetLocation) {
        this.targetLocation = targetLocation;
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
    public Location getTargetObjectLocation() {
        return this.targetLocation;
    }

    @Override
    public Location getTargetObject() {
        return this.targetLocation;
    }
}
