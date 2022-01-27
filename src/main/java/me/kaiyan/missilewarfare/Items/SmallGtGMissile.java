package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.WeaponUseHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class SmallGtGMissile extends SlimefunItem{
    public SmallGtGMissile(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        ItemMeta meta = item.getItemMeta();
        meta.getLore().add(org.bukkit.ChatColor.stripColor(UUID.randomUUID().toString()));
        item.setItemMeta(meta);
    }

    @Override
    public void preRegister() {
        WeaponUseHandler weaponUseHandler = this::weaponUsed;
        addItemHandler(weaponUseHandler);
    }

    private void weaponUsed(EntityDamageByEntityEvent event, Player player, ItemStack itemStack) {
        event.setCancelled(true);
    }
}
