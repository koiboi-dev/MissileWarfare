package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.kaiyan.missilewarfare.VariantsAPI;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MissileItem extends SlimefunItem {
    public MissileItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int type, String extraLore) {
        super(itemGroup, item, recipeType, recipe);

        MissileClass missileClass = VariantsAPI.missileStatsFromType(type);

        ItemMeta meta = item.getItemMeta();
        List<String> lore = meta.getLore();
        lore.add("Range: "+Math.sqrt(missileClass.range));
        lore.add("Power: "+missileClass.power);
        lore.add("Speed: "+missileClass.speed);
        lore.add("Accuracy: Within "+missileClass.accuracy+" Blocks");
        lore.add(extraLore);
        meta.setLore(lore);
        item.setItemMeta(meta);

        ItemUseHandler use = PlayerRightClickEvent::cancel;
        addItemHandler(use);
    }
}
