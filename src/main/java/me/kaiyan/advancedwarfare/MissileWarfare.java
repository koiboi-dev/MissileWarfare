package me.kaiyan.advancedwarfare;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;

public class MissileWarfare extends JavaPlugin implements SlimefunAddon {
    public static MissileWarfare plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Read something from your config.yml
        Config cfg = new Config(this);
        CustomItems.setup();
    }

    public static MissileWarfare getInstance(){
        return plugin;
    }

    @Override
    public void onDisable() {
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }
}
