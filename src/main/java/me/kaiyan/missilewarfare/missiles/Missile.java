package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public interface Missile {

    public Vector getPosition();

    public int getSpeed();

    public Vector getDirection();

    public Vector getVelocity();

    public Entity getArmourStand();

    public TargetObject getTargetObject();
}
