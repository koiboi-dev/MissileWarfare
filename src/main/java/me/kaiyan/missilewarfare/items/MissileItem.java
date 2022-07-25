package me.kaiyan.missilewarfare.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.WeaponUseHandler;
import me.kaiyan.missilewarfare.util.Translations;
import me.kaiyan.missilewarfare.util.VariantsAPI;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MissileItem extends SlimefunItem {
    public MissileItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int type, String extraLore) {
        super(itemGroup, item, recipeType, recipe);

        MissileClass missileClass = VariantsAPI.missileStatsFromType(type);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7"+ Translations.get("missiledesc.range")) +Math.sqrt(missileClass.range));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7"+ Translations.get("missiledesc.power")+missileClass.power));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7"+ Translations.get("missiledesc.speed")+missileClass.speed));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7"+ Translations.get("missiledesc.accuracy").replace("{blocks}", String.valueOf(missileClass.accuracy))));
        lore.add(extraLore);
        meta.setLore(lore);
        item.setItemMeta(meta);

        WeaponUseHandler attack = (event, player, itemStack) -> event.setCancelled(true);
        addItemHandler(attack);
    }
}
