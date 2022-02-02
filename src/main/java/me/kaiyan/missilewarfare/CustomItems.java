package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.kaiyan.missilewarfare.Blocks.AntiMissileLauncher;
import me.kaiyan.missilewarfare.Blocks.GroundMissileLauncher;
import me.kaiyan.missilewarfare.Items.GuideBook;
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
        //-SUGARFUEL-
        //INFOITEM
        SlimefunItemStack guidestack = new SlimefunItemStack("GUIDEBOOK", Material.WRITTEN_BOOK, "Guide Book", "It will guide you?!?");
        ItemStack[] guiderecipe = {
                null, sugarfuelstack, null,
                sugarfuelstack, new ItemStack(Material.BOOK), sugarfuelstack,
                null, sugarfuelstack, null
        };

        GuideBook guide = new GuideBook(group, guidestack, RecipeType.ENHANCED_CRAFTING_TABLE, guiderecipe);

        guide.register(MissileWarfare.getInstance());
        sugarfuel.register(MissileWarfare.getInstance());
        //-INFOITEM-
        // EXPLOSIVE POWDER
        SlimefunItemStack explosivepowderstack = new SlimefunItemStack("EXPLOSIVEPOWDER", Material.GLOWSTONE_DUST, "Explosive Powder", "Handle with care!");
        ItemStack[] explosivepowderrecipe = {
                SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST,
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.GUNPOWDER), SlimefunItems.MAGNESIUM_DUST,
                SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST
        };

        SlimefunItem explosivepowder = new SlimefunItem(group, explosivepowderstack, RecipeType.ENHANCED_CRAFTING_TABLE, explosivepowderrecipe);

        explosivepowder.register(MissileWarfare.getInstance());
        //-EXPLOSIVE POWDER-
        SlimefunItemStack compressedpowderstack = new SlimefunItemStack("COMPRESSEDEXPLOSIVES", Material.YELLOW_CONCRETE, "Solid Explosive Powder", "Compressed so hard it became a solid");
        ItemStack[] compressedpowderrecipe = {
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack,
                SlimefunItems.MAGNESIUM_DUST, explosivepowderstack, SlimefunItems.MAGNESIUM_DUST,
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack
        };

        SlimefunItem compressedpowder = new SlimefunItem(group, compressedpowderstack, RecipeType.COMPRESSOR, compressedpowderrecipe);

        compressedpowder.register(MissileWarfare.getInstance());
        // ULTRALITE INGOT
        SlimefunItemStack ultraliteingotstack = new SlimefunItemStack("ULTRALITE_INGOT", Material.BRICK, "Ultra-Lite Ingot", "Super Lightweight ingot used for missiles");
        ItemStack[] ultraliteingotrecipe = {
                SlimefunItems.IRON_DUST, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.COPPER_DUST,
                null, null, null,
                null, null, null
        };

        SlimefunItem ultraliteingot = new SlimefunItem(group, ultraliteingotstack, RecipeType.SMELTERY, ultraliteingotrecipe);

        ultraliteingot.register(MissileWarfare.getInstance());
        //-ULTRALITE INGOT-
        // ULTRALITE PLATE
        SlimefunItemStack ultraliteplatestack = new SlimefunItemStack("ULTRALITE_PLATE", Material.IRON_INGOT, "Ultra-Lite Plate", "A super lightweight plate for missiles");
        ItemStack[] ultraliteplaterecipe = {
                ultraliteingotstack, null, ultraliteingotstack,
                null, new ItemStack(Material.COAL), null,
                ultraliteingotstack, null, ultraliteingotstack
        };

        SlimefunItem ultraliteplate = new SlimefunItem(group, ultraliteplatestack, RecipeType.COMPRESSOR, ultraliteplaterecipe);

        ultraliteplate.register(MissileWarfare.getInstance());
        //-ULTRALITE PLATE-
        // Flight Computer Simple
        SlimefunItemStack simpleflightcomputerstacks = new SlimefunItemStack("SIMPLE_FLIGHT_COMPUTER", Material.POWERED_RAIL, "Basic Flight Computer", "A simple computer capable of guiding a missile");
        ItemStack simpleflightcomputerstack = simpleflightcomputerstacks.clone();
        simpleflightcomputerstacks.setAmount(2);
        simpleflightcomputerstack.setAmount(1);
        ItemStack[] simpleflightcomputerrecipe = {
                ultraliteingotstack, ultraliteplatestack, ultraliteingotstack,
                new ItemStack(Material.REDSTONE), SlimefunItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.REDSTONE),
                ultraliteingotstack, ultraliteplatestack, ultraliteingotstack
        };

        SlimefunItem simpleflightcomputer = new SlimefunItem(group, simpleflightcomputerstacks, RecipeType.ENHANCED_CRAFTING_TABLE, simpleflightcomputerrecipe);
        simpleflightcomputer.register(MissileWarfare.getInstance());
        //-Flight Computer Simple-
        // Radar Part
        SlimefunItemStack radarstack = new SlimefunItemStack("RADAR", Material.ACTIVATOR_RAIL, "Radar", "Used when guiding anti-air missiles");
        ItemStack[] radarrecipe = {
                null, ultraliteplatestack, null,
                ultraliteplatestack, simpleflightcomputerstack, ultraliteplatestack,
                new ItemStack(Material.REDSTONE), null, new ItemStack(Material.REDSTONE)
        };

        SlimefunItem radar = new SlimefunItem(group, radarstack, RecipeType.ENHANCED_CRAFTING_TABLE, radarrecipe);

        radar.register(MissileWarfare.getInstance());
        //-Radar Part-
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
        //simple WARHEAD
        SlimefunItemStack smallwarheadstack = new SlimefunItemStack("SMALLWARHEAD", Material.TNT, "Simple Missile Warhead", "Used in creation of a missile.", "'Dont touch the red bit'");
        ItemStack[] smallwarheadrecipe = {
                null, SlimefunItems.ALUMINUM_INGOT, null,
                SlimefunItems.ALUMINUM_INGOT, explosivepowderstack, SlimefunItems.ALUMINUM_INGOT,
                null, null, null
        };

        SlimefunItem smallwarhead = new SlimefunItem(group, smallwarheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallwarheadrecipe);

        smallwarhead.register(MissileWarfare.getInstance());
        //-SMALL WARHEAD-
        //SMALL BODY
        SlimefunItemStack smallbodystack = new SlimefunItemStack("SMALLBODY", Material.IRON_BLOCK, "Simple Missile Body", "Used in the creation of a missile", "'You better not dent that'");
        ItemStack[] smallbodyrecipe = {
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                ultraliteingotstack, simpleflightcomputerstack, ultraliteingotstack,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallbody = new SlimefunItem(group, smallbodystack, RecipeType.ENHANCED_CRAFTING_TABLE, smallbodyrecipe);

        smallbody.register(MissileWarfare.getInstance());
        //-SMALL BODY-
        // SMALL FINS
        SlimefunItemStack smallfinstack = new SlimefunItemStack("SMALLFIN", Material.IRON_BOOTS, "Simple Missile Fins", "Used in the creation of a missile");
        ItemStack[] smallfinrecipe = {
                null, null, null,
                null, simpleflightcomputerstack, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallfin = new SlimefunItem(group, smallfinstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallfinrecipe);

        smallfin.register(MissileWarfare.getInstance());
        //-SMALL FINS-
        //SMALL GtG MISSILE
        SlimefunItemStack smallmissilestack = new SlimefunItemStack("SMALLMISSILE", Material.IRON_SWORD, "Simple GtG Missile", "Small Ground-to-Ground Missile","Normal Variant");
        ItemStack[] smallmissilerecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissile = new MissileItem(group, smallmissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipe, 1, "'Little Timmy never stood a chance...'");

        smallmissile.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE-
        //SMALL GtG MISSILE HE
        SlimefunItemStack smallmissilestackHE = new SlimefunItemStack("SMALLMISSILEHE", Material.IRON_SWORD, "Simple GtG Missile HE", "Small Ground-to-Ground Missile","High-Explosive Variant");
        ItemStack[] smallmissilerecipeHE = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                explosivepowderstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissileHE = new MissileItem(group, smallmissilestackHE, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipeHE, 2, "'Large Timmy never stood a chance...'");

        smallmissileHE.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE HE-
        // SMALL GtG MISSILE LR
        SlimefunItemStack smallmissileLRstack = new SlimefunItemStack("SMALLMISSILELR", Material.IRON_SWORD,"Simple GtG Missile LR","Long-Range Variant");
        ItemStack[] smallmissileLRrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack
        };
        MissileItem smallmissileLR = new MissileItem(group, smallmissileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileLRrecipe, 3, "'Far away timmy never stood a chance...'");

        smallmissileLR.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE LR-
        // SMALL GtG MISSILE AC
        SlimefunItemStack smallmissileACstack = new SlimefunItemStack("SMALLMISSILEAC", Material.IRON_SWORD,"Simple GtG Missile AC","Accurate Variant");
        ItemStack[] smallmissileACrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                smallfinstack, rocketfuelstack, smallfinstack
        };
        MissileItem smallmissileAC = new MissileItem(group, smallmissileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileACrecipe, 4, "'REALLY small timmy never stood a chance...'");

        smallmissileAC.register(MissileWarfare.getInstance());
        //-SMALL GtG MISSILE AC-
        // Small GtG MISSILE LAUNCHER 1x
        SlimefunItemStack groundlauncherstack = new SlimefunItemStack("GROUNDLAUNCHER", Material.DISPENSER, "Ground Launcher", "Shoots a specified area on the ground.", "Use a stick to set target coords", "Shift with a stick to check if it can fire", "Right click with a blaze rod to set cruise alt","Default cruise alt is Y:120", "Needs to be built on 1 green concrete block.");
        ItemStack[] groundlauncherrecipe = {
                SlimefunItems.STEEL_INGOT, null, SlimefunItems.STEEL_INGOT,
                SlimefunItems.DURALUMIN_INGOT, null, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.DAMASCUS_STEEL_INGOT
        };
        GroundMissileLauncher groundlauncher = new GroundMissileLauncher(group, groundlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, groundlauncherrecipe);

        groundlauncher.register(MissileWarfare.getInstance());
        //- Small GtG MISSILE LAUNCHER-
        //MISSILE BODY
        SlimefunItemStack missilebodystack = new SlimefunItemStack("MISSILE_BODY", Material.SMOOTH_STONE, "Missile Body", "Missile body containing a flight computer");
        ItemStack[] missilebodyrecipe = {
                ultraliteplatestack, simpleflightcomputerstack, ultraliteplatestack,
                ultraliteplatestack, rocketfuelstack, ultraliteplatestack,
                ultraliteplatestack, rocketfuelstack, ultraliteplatestack
        };

        SlimefunItem missilebody = new SlimefunItem(group, missilebodystack, RecipeType.ENHANCED_CRAFTING_TABLE, missilebodyrecipe);

        missilebody.register(MissileWarfare.getInstance());
        // - MISSILE BODY-
        // Advanced Fins
        SlimefunItemStack finsstack = new SlimefunItemStack("MISSILE_FINS", Material.GOLDEN_BOOTS, "Missile Fins", "Able to move and direct the missile better");
        ItemStack[] finsrecipe = {
                null, null, null,
                ultraliteplatestack, null, ultraliteplatestack,
                ultraliteplatestack, null, ultraliteplatestack
        };

        SlimefunItem fins = new SlimefunItem(group, finsstack, RecipeType.ENHANCED_CRAFTING_TABLE, finsrecipe);

        fins.register(MissileWarfare.getInstance());
        // - ADVANCED FINS-
        // GtA MISSILE
        SlimefunItemStack antiAirMissilestack = new SlimefunItemStack("ANTIAIRMISSILE", Material.IRON_SWORD,"Anti Air Missile","Can Shoot Down Air Targets", "Used in a Anti Missile Launcher");
        ItemStack[] antiAirMissilerecipe = {
                null, radarstack, null,
                explosivepowderstack, missilebodystack, explosivepowderstack,
                rocketfuelstack, finsstack, rocketfuelstack
        };
        MissileItem antiAirMissile = new MissileItem(group, antiAirMissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, antiAirMissilerecipe, 5, "'Airborne Timmy Never Stood A Chance'");

        antiAirMissile.register(MissileWarfare.getInstance());
        //-GtA MISSILE
        // GtA MISSILE LAUNCHER 1x
        SlimefunItemStack antiairlauncherstack = new SlimefunItemStack("ANTIMISSILELAUNCHER", Material.DISPENSER, "Anti-Missile Launcher", "Targets and shoots down other missiles in the area.", "Use redstone to disable it", "Needs to be built surrounded by Coal Blocks.", "(Not Along Diagonals)");
        ItemStack[] antiairlauncherrecipe = {
                SlimefunItems.SILVER_INGOT, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.SILVER_INGOT,
                SlimefunItems.SILVER_INGOT, null, SlimefunItems.SILVER_INGOT,
                SlimefunItems.LEAD_INGOT, new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.LEAD_INGOT
        };
        AntiMissileLauncher antiairlauncher = new AntiMissileLauncher(group, antiairlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, antiairlauncherrecipe);

        antiairlauncher.register(MissileWarfare.getInstance());
        //- GtA MISSILE LAUNCHER-
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

        // GtGMissile
        SlimefunItemStack missilestack = new SlimefunItemStack("MISSILE", Material.GOLDEN_SWORD,"GtG Missile","Normal Variant");
        ItemStack[] missilerecipe = {
                null, smallwarheadstack, null,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missile = new MissileItem(group, missilestack, RecipeType.ENHANCED_CRAFTING_TABLE, missilerecipe, 6, "'Your friendly neighbourhood missile'");

        missile.register(MissileWarfare.getInstance());
        //-GtGMissile-
        // GtGMissileHE
        SlimefunItemStack missileHEstack = new SlimefunItemStack("MISSILEHE", Material.GOLDEN_SWORD,"GtG Missile HE","High Explosive Variant");
        ItemStack[] missileHErecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileHE = new MissileItem(group, missileHEstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileHErecipe, 7, "'Your not-so friendly neighbourhood missile'");

        missileHE.register(MissileWarfare.getInstance());
        //-GtGMissileHE-
        // GtGMissileLR
        SlimefunItemStack missileLRstack = new SlimefunItemStack("MISSILELR", Material.GOLDEN_SWORD,"GtG Missile LR","Long Range Variant");
        ItemStack[] missileLRrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileLR = new MissileItem(group, missileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileLRrecipe, 8, "'Your friendly state missile'");

        missileLR.register(MissileWarfare.getInstance());
        //-GtGMissileLR-
        // GtGMissileLR
        SlimefunItemStack missileACstack = new SlimefunItemStack("MISSILEAC", Material.GOLDEN_SWORD,"GtG Missile AC","Long Range Variant");
        ItemStack[] missileACrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileAC = new MissileItem(group, missileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileACrecipe, 9, "'Your friendly home-defending missile'");

        missileAC.register(MissileWarfare.getInstance());
        //-GtGMissileLR-

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
        Research explosives = new Research(explosiveskey, 3447321, "Explosive Diarrhea", 15);
        explosives.addItems(explosivepowder);
        explosives.register();

        NamespacedKey groundlauncherskey = new NamespacedKey(MissileWarfare.getInstance(), "groundlauncher");
        Research groundlauncherres = new Research(groundlauncherskey, 34117322, "Ground Missile Launcher", 15);
        groundlauncherres.addItems(groundlauncher);
        groundlauncherres.register();

        NamespacedKey smallgmissilepartskey = new NamespacedKey(MissileWarfare.getInstance(), "smallgmissileparts");
        Research smallgmissileparts = new Research(smallgmissilepartskey, 2667313, "Missile with extra steps", 15);
        smallgmissileparts.addItems(smallwarhead, smallbody, smallfin);
        smallgmissileparts.register();

        NamespacedKey smallgmissilekey = new NamespacedKey(MissileWarfare.getInstance(), "smallgmissile");
        Research smallgmissile = new Research(smallgmissilekey, 35673323, "The colors of the rainbows", 20);
        smallgmissile.addItems(smallmissile, smallmissileHE, smallmissileLR, smallmissileLR);
        smallgmissile.register();

        NamespacedKey advancedfuelkey = new NamespacedKey(MissileWarfare.getInstance(), "advancedfuel");
        Research advancedfuel = new Research(advancedfuelkey, 3461423, "Advanced (and even less edible) Fuels!", 20);
        advancedfuel.addItems(rocketfuel);
        advancedfuel.register();

        NamespacedKey missilepartskey = new NamespacedKey(MissileWarfare.getInstance(), "missileparts");
        Research missileparts = new Research(missilepartskey, 4461423, "Missile with even more steps", 25);
        missileparts.addItems(ultraliteingot, ultraliteplate, simpleflightcomputer, radar);
        missileparts.register();
    }
}
