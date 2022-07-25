package me.kaiyan.missilewarfare.util;

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import net.md_5.bungee.api.ChatColor;

import java.util.List;

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
            output.append(ChatColor.translateAlternateColorCodes('&', str));
        }

        return output.toString();
    }

    public static String getMaterialName(String material){
        return ChatColor.translateAlternateColorCodes('&',"&e"+get("materials."+material+".name"));
    }
    public static String[] getMaterialLore(String material){
        List<String> lore = pack.getStringList("materials." + material + ".lore");
        for (String str : lore){
            ChatColor.translateAlternateColorCodes('&', "&7"+str);
        }
        return lore.toArray(new String[0]);
    }

    public static String getTypeLore(){
        return ChatColor.translateAlternateColorCodes('&',"&c"+get("missiles."+missileType+".lore"));
    }

    public static void setType(String type){
        missileType = type;
    }

    public static String getMissileName(String type){
        return ChatColor.translateAlternateColorCodes('&',"&e"+get("missiles."+missileType+".missiles."+type+".name"));
    }
    public static String getMissileVariant(String type){
        return ChatColor.translateAlternateColorCodes('&',"&a"+get("missiles."+missileType+".missiles."+type+".variant"));
    }
    public static String getMissileLore(String type){
        return ChatColor.translateAlternateColorCodes('&', "&7"+get("missiles."+missileType+".missiles."+type+".lore"));
    }

    public static String getSpecialName(String type){
        return ChatColor.translateAlternateColorCodes('&', "&c"+get("specialmissiles."+type+".name"));
    }
    public static String[] getSpecialLore(String type){
        List<String> lore = pack.getStringList("specialmissiles."+type+".lore");
        for (String str : lore){
            ChatColor.translateAlternateColorCodes('&', "&7"+str);
        }
        return lore.toArray(new String[0]);
    }
    public static String getSpecialALore(String type){
        return ChatColor.translateAlternateColorCodes('&', "&7"+get("specialmissiles."+type+".afterlore"));
    }

    public static void setup(Config cfg){
        pack = cfg;
    }
}
