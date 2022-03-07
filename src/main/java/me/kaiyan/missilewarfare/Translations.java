package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import net.md_5.bungee.api.ChatColor;

public class Translations {
    public static Config pack;
    public static String missileType = "";

    public static String get(String key){
        return pack.getString(key);
    }

    public static String[] getarr(String key){
        return pack.getStringList(key).toArray(new String[0]);
    }

    public static String getPage(String key){
        StringBuilder output = new StringBuilder();
        String[] arr = getarr(key+".content");

        for (String str : arr){
            str += "\n";
            if (str.contains("[BOLD]")){
                str = str.replace("[BOLD]", "");
                str = ChatColor.BOLD+str;
            }
            output.append(str);
        }

        return output.toString();
    }

    public static String getMaterialName(String material){
        return get("materials."+material+".name");
    }
    public static String[] getMaterialLore(String material){
        return pack.getStringList("materials." + material + ".lore").toArray(new String[0]);
    }

    public static String getTypeLore(){
        return get("missiles."+missileType+".lore");
    }

    public static void setType(String type){
        missileType = type;
    }

    public static String getMissileName(String type){
        return get("missiles."+missileType+".missiles."+type+".name");
    }
    public static String getMissileVariant(String type){
        return get("missiles."+missileType+".missiles."+type+".variant");
    }
    public static String getMissileLore(String type){
        return get("missiles."+missileType+".missiles."+type+".lore");
    }

    public static String getSpecialName(String type){
        return get("specialmissiles."+type+".name");
    }
    public static String[] getSpecialLore(String type){
        return pack.getStringList("specialmissiles."+type+".lore").toArray(new String[0]);
    }
    public static String getSpecialALore(String type){
        return get("specialmissiles."+type+".afterlore");
    }

    public static void setup(Config cfg){
        pack = cfg;
    }
}
