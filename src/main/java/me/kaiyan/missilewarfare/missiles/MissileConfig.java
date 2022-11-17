package me.kaiyan.missilewarfare.missiles;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import me.kaiyan.missilewarfare.items.MissileClass;
import me.kaiyan.missilewarfare.util.VariantsAPI;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class MissileConfig {

    public static MissileClass[] missiles;

    public static void setup(Config cfg) {
        List<MissileClass> outMissiles = new ArrayList<>();
        for (String key : cfg.getKeys("missiles")) {
            outMissiles.add(new MissileClass(cfg.getDouble("missiles." + key + ".speed"),
                                             cfg.getInt("missiles." + key + ".range"),
                                             cfg.getInt("missiles." + key + ".power"),
                                             cfg.getInt("missiles." + key + ".accuracy"),
                                             VariantsAPI.getIntTypeFromSlimefunitemID(key)));
        }
        missiles = outMissiles.toArray(new MissileClass[0]);
    }
}
