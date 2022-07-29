package me.kaiyan.missilewarfare.missiles.flightpattern;

import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.missiles.Missile;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class AbstractPattern  extends BukkitRunnable {
    MissileWarfare plugin;
    Missile missile;

    public AbstractPattern(Missile missile) {
        this.plugin = MissileWarfare.getInstance();
        this.missile = missile;
    }

    public void activate() {
        this.runTaskTimer(this.plugin, 0, 5);
    }
}
