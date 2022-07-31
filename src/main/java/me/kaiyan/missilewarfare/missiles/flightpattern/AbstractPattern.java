package me.kaiyan.missilewarfare.missiles.flightpattern;

import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.missiles.Missile;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class AbstractPattern<T extends TargetObject<?>> extends BukkitRunnable {
    MissileWarfare plugin;
    Missile<T> missile;

    // does the physics calculations for every fourth.
    public void calculatePhysics() {
        Vector velocity = missile.getVelocity();
        Location location = missile.getLocation().clone();


        location.setX(location.getBlockX() + velocity.getX());
        location.setY(location.getBlockY() + velocity.getY());
        location.setZ(location.getBlockZ() + velocity.getZ());

        missile.setLocation(location);
        missile.getArmourStand().teleport(location);

        int distance_travelled = (int) velocity.length();
        int remaining = missile.getRemainingRange();
        missile.setRemainingRange(remaining - distance_travelled);
    }

    public Missile<T> getMissile() {
        return this.missile;
    }

    public AbstractPattern(Missile<T> missile) {
        this.plugin = MissileWarfare.getInstance();
        this.missile = missile;
    }

    public void activate() {
        this.runTaskTimer(this.plugin, 0, 5);
    }
}
