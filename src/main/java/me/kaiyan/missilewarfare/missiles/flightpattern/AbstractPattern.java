package me.kaiyan.missilewarfare.missiles.flightpattern;

import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.missiles.Missile;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class AbstractPattern  extends BukkitRunnable {
    MissileWarfare plugin;
    Missile missile;

    // does the physics calculations for every tick.
    private void calcPhys() {
        Vector velocity = missile.getVelocity();
        missile.setPosition(missile.getPosition().add(velocity));

        missile.getArmourStand().teleport(missile.getPosition().toLocation(missile.getWorld()));


    }

    public AbstractPattern(Missile missile) {
        this.plugin = MissileWarfare.getInstance();
        this.missile = missile;
    }

    public void activate() {
        this.runTaskTimer(this.plugin, 0, 5);
    }
}
