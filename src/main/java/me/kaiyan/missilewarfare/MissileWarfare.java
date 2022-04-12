package me.kaiyan.missilewarfare;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.kaiyan.missilewarfare.Missiles.MissileConfig;
import me.kaiyan.missilewarfare.Missiles.MissileController;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SingleLineChart;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MissileWarfare extends JavaPlugin implements SlimefunAddon {
    public static StateFlag ALLOW_MISSILE_EXPLODE;
    public static MissileWarfare plugin;
    public static List<MissileController> activemissiles;
    public static boolean worldGuardEnabled = false;
    public static Metrics metrics;
    public static int firedMissiles = 0;
    public static int blocksExploded = 0;

    @Override
    public void onEnable() {
        int pluginId = 14904; // <-- Replace with the id of your plugin!
        metrics = new Metrics(this, pluginId);

        metrics.addCustomChart(new SingleLineChart("missiles_fired", () -> {
            int missiles = firedMissiles;
            firedMissiles = 0;
            return missiles;
        }));
        metrics.addCustomChart(new SingleLineChart("missile_destroy", () -> {
            int blocks = blocksExploded;
            blocksExploded = 0;
            return blocks;
        }));

        getLogger().info("Missile Warfare Starting Up!");

        activemissiles = new ArrayList<>();
        plugin = this;
        // Read something from your config.yml
        Config cfg = new Config(this);
        Config saveFile;
        if (!new File(this.getDataFolder()+"/saveID.yml").exists()) {
            saveFile = new Config(new File(this.getDataFolder() + "/saveID.yml"));
            saveFile.createFile();
        } else {
            saveFile = new Config(new File(this.getDataFolder() + "/saveID.yml"));
        }
        File lang = new File(getDataFolder()+"/lang");
        if (!lang.exists()) {
            generateLangPacks(lang);
        }
        try {
            Translations.setup(new Config(getDataFolder()+"/lang/"+cfg.getString("translation-pack")+".yml"));
            PlayerID.loadPlayers(saveFile);
            MissileConfig.setup(cfg);
            CustomItems.setup();
        } catch (Exception e){
            getLogger().warning(e.toString());
            getLogger().warning("=== !LANG PACK INVALID, REVERTING TO EN LANG PACK! ===");
            getLogger().warning("/brokenLang/ created, the invalid langpack is in there");
            lang.renameTo(new File(getDataFolder()+"/brokenLang/"));
            generateLangPacks(lang);
        }   

        new BukkitRunnable() {
            @Override
            public void run() {
                PlayerID.targets = new ArrayList<>();
            }
        }.runTaskTimer(this, 20, 200);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (activemissiles.isEmpty()) {
                    for (World world : getServer().getWorlds()) {
                        for (Entity entity : world.getEntities()) {
                            if (entity.getCustomName() != null) {
                                if (entity.getCustomName().equals("MissileHolder")) {
                                    entity.remove();
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0, cfg.getInt("other.cleanup-wait-time"));
        getLogger().info("Checking For Worldguard");
        if (getServer().getPluginManager().getPlugin("WorldGuard") != null){
            worldGuardEnabled = true;
            getLogger().info("WorldGuard Enabled!");
            FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
            try {
                // create a flag with the name "my-custom-flag", defaulting to true
                StateFlag flag = new StateFlag("allow-missile-explode", true);
                registry.register(flag);
                ALLOW_MISSILE_EXPLODE = flag; // only set our field if there was no error
            } catch (FlagConflictException e) {
                // some other plugin registered a flag by the same name already.
                // you can use the existing flag, but this may cause conflicts - be sure to check type
                Flag<?> existing = registry.get("allow-missile-explode");
                if (existing instanceof StateFlag) {
                    ALLOW_MISSILE_EXPLODE = (StateFlag) existing;
                } else {
                    getLogger().severe("!! WARNING: WORLDGUARD FLAG ALLOW_MISSILE_EXPLODE HAS BEEN TAKEN BY ANOTHER PLUGIN, WORLDGUARD SUPPORT IS DISABLED !!");
                    worldGuardEnabled = false;
                }
            }
        }
    }

    public static MissileWarfare getInstance(){
        return plugin;
    }

    @Override
    public void onDisable() {
        for (MissileController missile : activemissiles){
            try {
                missile.armourStand.remove();
                missile.update.cancel();
            } catch (NullPointerException e){
                try {
                    missile.update.cancel();
                } catch (NullPointerException ignored){

                }
            }
        }
        PlayerID.savePlayers(new Config(new File(this.getDataFolder()+"/saveID.yml")));
        // Logic for disabling the plugin...
    }

    @Override
    public String getBugTrackerURL() {
        // You can return a link to your Bug Tracker instead of null here
        return null;
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        /*
         * You will need to return a reference to your Plugin here.
         * If you are using your main class for this, simply return "this".
         */
        return this;
    }

    public void generateLangPacks(File lang){
        String[] loadedpacks = MissileWarfare.getInstance().getConfig().getStringList("saved-packs").toArray(new String[0]);
        for (String pack : loadedpacks) {
            saveResource(pack + ".yml", false);
        }

        lang.mkdir();

        File datafolder = getDataFolder();
        for (File file : datafolder.listFiles()){
            if (file.getName().startsWith("pack-")){
                try {
                    Files.move(file.toPath(), new File(lang.getPath(), file.getName()).toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
