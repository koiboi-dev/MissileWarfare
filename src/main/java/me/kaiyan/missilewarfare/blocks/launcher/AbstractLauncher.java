package me.kaiyan.missilewarfare.blocks.launcher;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.kaiyan.missilewarfare.blocks.AbstractBlock;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractLauncher extends AbstractBlock {
    public AbstractLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }



}
