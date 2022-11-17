package me.kaiyan.missilewarfare.missiles.flightpattern;

import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.missiles.Missile;
import me.kaiyan.missilewarfare.missiles.target.TargetObject;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractPattern<T extends TargetObject<?>> extends BukkitRunnable {
    private final @NotNull MissileWarfare plugin;
    private final @NotNull Missile<T> missile;

    protected AbstractPattern(@NotNull Missile<T> missile) {
        this.plugin = MissileWarfare.getInstance();
        this.missile = missile;
    }

    // does the physics calculations for every fourth.
    public void calculatePhysics() {
        Vector velocity = this.missile.getVelocity();
        Location location = this.missile.getLocation().clone();
        location.add(velocity);

        this.missile.setLocation(location);
        this.missile.getArmourStand().teleport(location);

        int distanceTravelled = (int) velocity.length();
        int remaining = this.missile.getRemainingRange();
        this.missile.setRemainingRange(remaining - distanceTravelled);
    }

    public @NotNull Missile<T> getMissile() {
        return this.missile;
    }

    public void activate() {
        this.runTaskTimer(this.plugin, 0, 5);
    }
}
