package me.kaiyan.missilewarfare.missiles;

import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public interface Missile {

    public Vector getPosition();

    public void setPosition(Vector vector);

    public int getSpeed();

    public void setSpeed(int integer);

    public Vector getDirection();

    public void setDirection(Vector vector);

    public World getWorld();

    public void setWorld(World world);

    public Vector getVelocity();

    public Entity getArmourStand();

    public TargetObject getTargetObject();

    public int getPower();

    public int getMaxSpeed();

    public int getManeuverability();
}
