package me.kaiyan.missilewarfare.missiles.target;

import me.kaiyan.missilewarfare.missiles.Missile;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class MissileTargetObject implements TargetObject<Missile<?>> {
    private final @NotNull Missile<?> targetMissile;

    public MissileTargetObject(@NotNull Missile<?> targetMissile) {
        this.targetMissile = targetMissile;
    }

    @Override
    public TargetObjectType getTargetObjectType() {
        return TargetObjectType.MISSILE;
    }

    @Override
    public Vector getTargetObjectVelocity() {
        return targetMissile.getVelocity();
    }

    @Override
    public Location getTargetObjectLocation() {
        return targetMissile.getLocation();
    }

    @Override
    public Missile<?> getTargetObject() {
        return targetMissile;
    }
}
