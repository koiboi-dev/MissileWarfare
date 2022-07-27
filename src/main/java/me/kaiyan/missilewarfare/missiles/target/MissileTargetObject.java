package me.kaiyan.missilewarfare.missiles.target;

import me.kaiyan.missilewarfare.missiles.Missile;
import org.bukkit.util.Vector;

public class MissileTargetObject implements TargetObject<Missile> {
    Missile target_missile;

    public MissileTargetObject(Missile target_missile) {
        this.target_missile = target_missile;
    }

    @Override
    public TargetObjectType getTargetObjectType() {
        return TargetObjectType.MISSILE;
    }

    @Override
    public Vector getTargetObjectVelocity() {
        return target_missile.getVelocity();
    }

    @Override
    public Vector getTargetObjectLocation() {
        return target_missile.getPosition();
    }

    @Override
    public Missile getTargetObject() {
        return target_missile;
    }
}
