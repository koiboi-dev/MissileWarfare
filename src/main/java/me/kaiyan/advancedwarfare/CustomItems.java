package me.kaiyan.advancedwarfare;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.kaiyan.advancedwarfare.Blocks.GroundMissileLauncher;
import me.kaiyan.advancedwarfare.Items.SmallGtGMissile;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class CustomItems {
    public static void setup(){
        //Add section to guide
        NamespacedKey categoryId = new NamespacedKey(AdvancedWarfare.getInstance(), "advanced_warfare");
        CustomItemStack categoryItem = new CustomItemStack(Material.GUNPOWDER, "&6Advanced Warfare");

        ItemGroup group = new ItemGroup(categoryId, categoryItem);

        //Add Items
        //SUGARFUEL
        SlimefunItemStack sugarfuelstack = new SlimefunItemStack("SUGARFUEL", Material.SUGAR, "Sugar Fuel", "Cheap, high energy, perfect for small missiles.");
        ItemStack[] sugarfuelrecipe = {
                null, SlimefunItems.MAGNESIUM_DUST, null,
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.COAL), SlimefunItems.MAGNESIUM_DUST,
                null, SlimefunItems.MAGNESIUM_DUST, null
        };

        SlimefunItem sugarfuel = new SlimefunItem(group, sugarfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, sugarfuelrecipe);

        sugarfuel.register(AdvancedWarfare.getInstance());
        //-SUGARFUEL-
        // EXPLOSIVE POWDER
        SlimefunItemStack explosivepowderstack = new SlimefunItemStack("EXPLOSIVEPOWDER", Material.SUGAR, "Explosive Powder", "Handle with care!");
        ItemStack[] explosivepowderrecipe = {
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST,
                new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER),
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST
        };

        SlimefunItem explosivepowder = new SlimefunItem(group, explosivepowderstack, RecipeType.ENHANCED_CRAFTING_TABLE, explosivepowderrecipe);

        explosivepowder.register(AdvancedWarfare.getInstance());
        //-EXPLOSIVE POWDER-
        // ROCKET FUEL
        SlimefunItemStack rocketfuelstack = new SlimefunItemStack("ROCKETFUEL", Material.GUNPOWDER, "Rocket Fuel", "Burns with the power of 1000 coal...");
        ItemStack[] rocketfuelrecipe = {
                null, new ItemStack(Material.GUNPOWDER), null,
                new ItemStack(Material.GUNPOWDER), SlimefunItems.COMPRESSED_CARBON, new ItemStack(Material.GUNPOWDER),
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack
        };
        SlimefunItem rocketfuel = new SlimefunItem(group, rocketfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, rocketfuelrecipe);

        rocketfuel.register(AdvancedWarfare.getInstance());
        //-ROCKETFUEL-
        //SMALL WARHEAD
        SlimefunItemStack smallwarheadstack = new SlimefunItemStack("SMALLWARHEAD", Material.TNT, "Small Missile Warhead", "Used in creation of a missile.", "'Dont touch the red bit'");
        ItemStack[] smallwarheadrecipe = {
                null, SlimefunItems.ALUMINUM_INGOT, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                null, null, null
        };

        SlimefunItem smallwarhead = new SlimefunItem(group, smallwarheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallwarheadrecipe);

        smallwarhead.register(AdvancedWarfare.getInstance());
        //-SMALL WARHEAD--
        //SMALL BODY
        SlimefunItemStack smallbodystack = new SlimefunItemStack("SMALLBODY", Material.IRON_BLOCK, "Small Missile Body", "Used in the creation of a missile", "'You better not dent that'");
        ItemStack[] smallbodyrecipe = {
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallbody = new SlimefunItem(group, smallbodystack, RecipeType.ENHANCED_CRAFTING_TABLE, smallbodyrecipe);

        smallbody.register(AdvancedWarfare.getInstance());
        //-SMALL BODY-
        // SMALL FINS
        SlimefunItemStack smallfinstack = new SlimefunItemStack("SMALLFIN", Material.IRON_BOOTS, "Small Missile Fins", "Used in the creation of a missile");
        ItemStack[] smallfinrecipe = {
                null, null, null,
                null, null, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallfin = new SlimefunItem(group, smallfinstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallfinrecipe);

        smallfin.register(AdvancedWarfare.getInstance());
        //-SMALL FINS-
        //SMALL GtG MISSILE
        SlimefunItemStack smallmissilestack = new SlimefunItemStack("SMALLMISSILE", Material.IRON_SWORD, "SMALL GtG MISSILE", "Small Ground-to-Ground Missile", "Range: 3000 Blocks", "Use a stick to set target coords", "Shift with a stick to check if it can fire","'Little Timmy never stood a chance...'");
        ItemStack[] smallmissilerecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        SmallGtGMissile smallmissile = new SmallGtGMissile(group, smallmissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipe);

        smallmissile.register(AdvancedWarfare.getInstance());
        //-SMALL GtG MISSILE-
        //GtG MISSILE LAUNCHER 1x
        SlimefunItemStack groundlauncherstack = new SlimefunItemStack("GROUNDLAUNCHER", Material.DISPENSER, "Ground Launcher", "Shoots a specified area on the ground.", "Needs to be built on 2 green concrete blocks.");
        ItemStack[] groundlauncherrecipe = {
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.DAMASCUS_STEEL_INGOT
        };

        SlimefunItem groundlauncher = new GroundMissileLauncher(group, groundlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, groundlauncherrecipe, smallmissile);

        groundlauncher.register(AdvancedWarfare.getInstance());
        //-GtG MISSILE LAUNCHER-
        //template
        /*SlimefunItemStack %stack = new SlimefunItemStack("%", Material., "N", "L");
        ItemStack[] %recipe = {
                null, null, null,
                null, null, null,
                null, null, null
        };

        SlimefunItem % = new SlimefunItem(group, %stack, RecipeType.ENHANCED_CRAFTING_TABLE, %recipe);

        %.register(AdvancedWarfare.getInstance());*/
    }
}
