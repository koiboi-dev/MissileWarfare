package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.kaiyan.missilewarfare.Blocks.SmallGroundMissileLauncher;
import me.kaiyan.missilewarfare.Items.ManPad;
import me.kaiyan.missilewarfare.Items.MissileItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class CustomItems {
    public static void setup(){
        //Add section to guide
        NamespacedKey categoryId = new NamespacedKey(MissileWarfare.getInstance(), "missile_warfare");
        CustomItemStack categoryItem = new CustomItemStack(Material.GUNPOWDER, "&6Missile Warfare");

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

        sugarfuel.register(MissileWarfare.getInstance());
        //-SUGARFUEL-
        // EXPLOSIVE POWDER
        SlimefunItemStack explosivepowderstack = new SlimefunItemStack("EXPLOSIVEPOWDER", Material.GLOWSTONE_DUST, "Explosive Powder", "Handle with care!");
        ItemStack[] explosivepowderrecipe = {
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST,
                new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER),
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST
        };

        SlimefunItem explosivepowder = new SlimefunItem(group, explosivepowderstack, RecipeType.ENHANCED_CRAFTING_TABLE, explosivepowderrecipe);

        explosivepowder.register(MissileWarfare.getInstance());
        //-EXPLOSIVE POWDER-
        // ROCKET FUEL
        SlimefunItemStack rocketfuelstack = new SlimefunItemStack("ROCKETFUEL", Material.GUNPOWDER, "Rocket Fuel", "Burns with the power of 1000 coal...");
        ItemStack[] rocketfuelrecipe = {
                new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.GUNPOWDER), new ItemStack(Material.GLOWSTONE_DUST),
                new ItemStack(Material.GUNPOWDER), SlimefunItems.COMPRESSED_CARBON, new ItemStack(Material.GUNPOWDER),
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack
        };
        SlimefunItem rocketfuel = new SlimefunItem(group, rocketfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, rocketfuelrecipe);

        rocketfuel.register(MissileWarfare.getInstance());
        //-ROCKETFUEL-
        //SMALL WARHEAD
        SlimefunItemStack smallwarheadstack = new SlimefunItemStack("SMALLWARHEAD", Material.TNT, "Small Missile Warhead", "Used in creation of a missile.", "'Dont touch the red bit'");
        ItemStack[] smallwarheadrecipe = {
                null, SlimefunItems.ALUMINUM_INGOT, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                null, null, null
        };

        SlimefunItem smallwarhead = new SlimefunItem(group, smallwarheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallwarheadrecipe);

        smallwarhead.register(MissileWarfare.getInstance());
        //-SMALL WARHEAD--
        //SMALL BODY
        SlimefunItemStack smallbodystack = new SlimefunItemStack("SMALLBODY", Material.IRON_BLOCK, "Small Missile Body", "Used in the creation of a missile", "'You better not dent that'");
        ItemStack[] smallbodyrecipe = {
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallbody = new SlimefunItem(group, smallbodystack, RecipeType.ENHANCED_CRAFTING_TABLE, smallbodyrecipe);

        smallbody.register(MissileWarfare.getInstance());
        //-SMALL BODY-
        // SMALL FINS
        SlimefunItemStack smallfinstack = new SlimefunItemStack("SMALLFIN", Material.IRON_BOOTS, "Small Missile Fins", "Used in the creation of a missile");
        ItemStack[] smallfinrecipe = {
                null, null, null,
                null, null, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallfin = new SlimefunItem(group, smallfinstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallfinrecipe);

        smallfin.register(MissileWarfare.getInstance());
        //-SMALL FINS-
        //SMALL GtG MISSILE
        SlimefunItemStack smallmissilestack = new SlimefunItemStack("SMALLMISSILE", Material.IRON_SWORD, "SMALL GtG MISSILE", "Small Ground-to-Ground Missile","Normal Variant");
        ItemStack[] smallmissilerecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissile = new MissileItem(group, smallmissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipe, 1, "'Little Timmy never stood a chance...'");

        smallmissile.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE-
        //SMALL GtG MISSILE HE
        SlimefunItemStack smallmissilestackHE = new SlimefunItemStack("SMALLMISSILEHE", Material.IRON_SWORD, "SMALL GtG MISSILE HE", "Small Ground-to-Ground Missile","High-Explosive Variant");
        ItemStack[] smallmissilerecipeHE = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                explosivepowderstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissileHE = new MissileItem(group, smallmissilestackHE, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipeHE, 2, "'Large Timmy never stood a chance...'");

        smallmissileHE.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE HE-
        // SMALL GtG MISSILE LR
        SlimefunItemStack smallmissileLRstack = new SlimefunItemStack("SMALLMISSILELR", Material.IRON_SWORD,"Small GtG Missile LR","Long-Range Variant");
        ItemStack[] smallmissileLRrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack
        };
        MissileItem smallmissileLR = new MissileItem(group, smallmissileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileLRrecipe, 3, "'Far away timmy never stood a chance...'");

        smallmissileLR.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE LR-
        // SMALL GtG MISSILE AC
        SlimefunItemStack smallmissileACstack = new SlimefunItemStack("SMALLMISSILEAC", Material.IRON_SWORD,"Small GtG Missile AC","Accurate Variant");
        ItemStack[] smallmissileACrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                smallfinstack, rocketfuelstack, smallfinstack
        };
        MissileItem smallmissileAC = new MissileItem(group, smallmissileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileACrecipe, 4, "'REALLY small timmy never stood a chance...'");

        smallmissileAC.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE AC-
        //GtG MISSILE LAUNCHER 1x
        SlimefunItemStack groundlauncherstack = new SlimefunItemStack("GROUNDLAUNCHER", Material.DISPENSER, "Ground Launcher", "Shoots a specified area on the ground.", "Use a stick to set target coords", "Shift with a stick to check if it can fire", "Right click with a blaze rod to set cruise alt","Default cruise alt is Y:120", "Needs to be built on 1 green concrete block.");
        ItemStack[] groundlauncherrecipe = {
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.DAMASCUS_STEEL_INGOT
        };
        SmallGroundMissileLauncher groundlauncher = new SmallGroundMissileLauncher(group, groundlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, groundlauncherrecipe);

        groundlauncher.register(MissileWarfare.getInstance());
        //-GtG MISSILE LAUNCHER-
        //MANPAD
        SlimefunItemStack manpadstack = new SlimefunItemStack("MANPAD", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFiNmVlNWJiZTVhZDQyOTY4MGMxYzE1Y2Y0MjBmOTgxMWUxMTRiNzY4NTRmODk5ZjBlZjA4ZmRlMzMyNzk4YyJ9fX0=", "Manpad", "Handheld anti-missile device", "Shift+RMB to begin tracking", "Release Shift fire");
        ItemStack[] manpadrecipe = {
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                explosivepowderstack, sugarfuelstack, sugarfuelstack,
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)
        };
        ManPad manpad = new ManPad(group, manpadstack, RecipeType.ENHANCED_CRAFTING_TABLE, manpadrecipe);
        manpad.register(MissileWarfare.getInstance());
        //-MANPAD-
        //template
        /*SlimefunItemStack %stack = new SlimefunItemStack("%", Material., "N", "L");
        ItemStack[] %recipe = {
                null, null, null,
                null, null, null,
                null, null, null
        };

        SlimefunItem % = new SlimefunItem(group, %stack, RecipeType.ENHANCED_CRAFTING_TABLE, %recipe);

        %.register(AdvancedWarfare.getInstance());*/
        //Missile template =================
        /*
        SlimefunItemStack %stack = new SlimefunItemStack("%", Material.DISPENSER,"Small % Missile","Normal Variant", "Range: % Blocks", "Power: %", "Speed: %", "Accuracy: Within % blocks", "'%'");
        ItemStack[] %recipe = {
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.DAMASCUS_STEEL_INGOT
        };
        SlimefunItem % = new SmallGroundMissileLauncher(group, %stack, RecipeType.ENHANCED_CRAFTING_TABLE, %recipe);

        %.register(AdvancedWarfare.getInstance());
         */

        //ADD RESEARCH
        NamespacedKey basicfuelkey = new NamespacedKey(MissileWarfare.getInstance(), "basic_fuel");
        Research basicfuel = new Research(basicfuelkey, 3467341, "Inedible Sugar", 10);
        basicfuel.addItems(sugarfuel);
        basicfuel.register();

        NamespacedKey explosiveskey = new NamespacedKey(MissileWarfare.getInstance(), "explosives");
        Research explosives = new Research(explosiveskey, 3467321, "Explosive Diarrhea", 15);
        explosives.addItems(explosivepowder);
        explosives.register();

        NamespacedKey groundlauncherskey = new NamespacedKey(MissileWarfare.getInstance(), "groundlauncher");
        Research groundlauncherres = new Research(groundlauncherskey, 3467322, "Ground Missile Launcher", 15);
        groundlauncherres.addItems(groundlauncher);
        groundlauncherres.register();

        NamespacedKey smallgmissilepartskey = new NamespacedKey(MissileWarfare.getInstance(), "smallgmissileparts");
        Research smallgmissileparts = new Research(smallgmissilepartskey, 34673323, "Small Missile", 15);
        smallgmissileparts.addItems(smallwarhead, smallbody, smallfin);
        smallgmissileparts.register();

        NamespacedKey smallgmissilekey = new NamespacedKey(MissileWarfare.getInstance(), "smallgmissile");
        Research smallgmissile = new Research(smallgmissilekey, 34673323, "Unlocked Small Missile!", 20);
        smallgmissile.addItems(smallmissile, smallmissileHE, smallmissileLR);
        smallgmissile.register();

        NamespacedKey advancedfuelkey = new NamespacedKey(MissileWarfare.getInstance(), "advancedfuel");
        Research advancedfuel = new Research(advancedfuelkey, 34673323, "Advanced (and even less edible) Fuels!", 20);
        advancedfuel.addItems(smallmissile, smallmissileHE, smallmissileLR);
        advancedfuel.register();
    }
}
