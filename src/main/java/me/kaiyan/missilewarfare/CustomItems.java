package me.kaiyan.missilewarfare;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.kaiyan.missilewarfare.Blocks.AntiElytraLauncher;
import me.kaiyan.missilewarfare.Blocks.AntiMissileLauncher;
import me.kaiyan.missilewarfare.Blocks.GroundMissileLauncher;
import me.kaiyan.missilewarfare.Items.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomItems {
    public static void setup(){
        //Add section to guide
        NamespacedKey categoryId = new NamespacedKey(MissileWarfare.getInstance(), "missile_warfare");
        CustomItemStack categoryItem = new CustomItemStack(Material.GUNPOWDER, "&6Missile Warfare");

        ItemGroup group = new ItemGroup(categoryId, categoryItem);

        //Add Items
        //<editor-fold desc="CREATE ITEMSTACKS">
        //<editor-fold desc="SUGARFUEL">
        SlimefunItemStack sugarfuelstack = new SlimefunItemStack("SUGARFUEL", Material.SUGAR, "Sugar Fuel", "Cheap, high energy, perfect for small missiles.");
        ItemStack[] sugarfuelrecipe = {
                null, SlimefunItems.MAGNESIUM_DUST, null,
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.COAL), SlimefunItems.MAGNESIUM_DUST,
                null, SlimefunItems.MAGNESIUM_DUST, null
        };

        SlimefunItem sugarfuel = new SlimefunItem(group, sugarfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, sugarfuelrecipe);
        //</editor-fold>
        //<editor-fold desc="GUIDEBOOK">
        SlimefunItemStack guidestack = new SlimefunItemStack("GUIDEBOOK", Material.WRITTEN_BOOK, "Guide Book", "It will guide you?!?");
        ItemStack[] guiderecipe = {
                null, sugarfuelstack, null,
                sugarfuelstack, new ItemStack(Material.BOOK), sugarfuelstack,
                null, sugarfuelstack, null
        };

        GuideBook guide = new GuideBook(group, guidestack, RecipeType.ENHANCED_CRAFTING_TABLE, guiderecipe);
        //</editor-fold>
        //<editor-fold desc="EXPLOSIVEPOWDER">
        SlimefunItemStack explosivepowderstack = new SlimefunItemStack("EXPLOSIVEPOWDER", Material.GLOWSTONE_DUST, "Explosive Powder", "Handle with care!");
        ItemStack[] explosivepowderrecipe = {
                SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST,
                SlimefunItems.MAGNESIUM_DUST, new ItemStack(Material.COAL), SlimefunItems.MAGNESIUM_DUST,
                SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST, SlimefunItems.MAGNESIUM_DUST
        };

        SlimefunItem explosivepowder = new SlimefunItem(group, explosivepowderstack, RecipeType.ENHANCED_CRAFTING_TABLE, explosivepowderrecipe);


        //</editor-fold>
        //<editor-fold desc="COMPRESSEDEXPLOSIVES">
        SlimefunItemStack compressedpowderstack = new SlimefunItemStack("COMPRESSEDEXPLOSIVES", Material.YELLOW_CONCRETE, "Solid Explosive Powder", "Compressed so hard it became a solid");
        ItemStack[] compressedpowderrecipe = {
                null, new ItemStack(Material.GUNPOWDER), null,
                explosivepowderstack, null, explosivepowderstack,
                null, new ItemStack(Material.GUNPOWDER), null,
        };
        SlimefunItem compressedpowder = new SlimefunItem(group, compressedpowderstack, RecipeType.COMPRESSOR, compressedpowderrecipe);
        //</editor-fold>
        //<editor-fold desc="ULTRALITE_INGOT">
        SlimefunItemStack ultraliteingotstack = new SlimefunItemStack("ULTRALITE_INGOT", Material.BRICK, "Ultra-Lite Ingot", "Super Lightweight ingot used for missiles");
        ItemStack[] ultraliteingotrecipe = {
                SlimefunItems.IRON_DUST, SlimefunItems.ALUMINUM_INGOT, SlimefunItems.COPPER_DUST,
                null, null, null,
                null, null, null
        };

        SlimefunItem ultraliteingot = new SlimefunItem(group, ultraliteingotstack, RecipeType.SMELTERY, ultraliteingotrecipe);
        //</editor-fold>
        //<editor-fold desc="ULTRALITE_PLATE">
        SlimefunItemStack ultraliteplatestack = new SlimefunItemStack("ULTRALITE_PLATE", Material.IRON_INGOT, "Ultra-Lite Plate", "A super lightweight plate for missiles");
        ItemStack[] ultraliteplaterecipe = {
                ultraliteingotstack, null, ultraliteingotstack,
                null, new ItemStack(Material.COAL), null,
                ultraliteingotstack, null, ultraliteingotstack
        };

        SlimefunItem ultraliteplate = new SlimefunItem(group, ultraliteplatestack, RecipeType.COMPRESSOR, ultraliteplaterecipe);
        //</editor-fold>
        //<editor-fold desc="SIMPLE_FLIGHT_COMPUTER">
        SlimefunItemStack simpleflightcomputerstacks = new SlimefunItemStack("SIMPLE_FLIGHT_COMPUTER", Material.POWERED_RAIL, "Basic Flight Computer", "A simple computer capable of guiding a missile");
        SlimefunItemStack simpleflightcomputerstack = (SlimefunItemStack) simpleflightcomputerstacks.clone();
        simpleflightcomputerstacks.setAmount(32);
        simpleflightcomputerstack.setAmount(1);
        ItemStack[] simpleflightcomputerrecipe = {
                ultraliteingotstack, SlimefunItems.BASIC_CIRCUIT_BOARD, ultraliteingotstack,
                new ItemStack(Material.REDSTONE), SlimefunItems.BASIC_CIRCUIT_BOARD, new ItemStack(Material.REDSTONE),
                ultraliteingotstack, SlimefunItems.BASIC_CIRCUIT_BOARD, ultraliteingotstack
        };

        SlimefunItem simpleflightcomputer = new SlimefunItem(group, simpleflightcomputerstack, RecipeType.ENHANCED_CRAFTING_TABLE, simpleflightcomputerrecipe);
        simpleflightcomputer.setRecipeOutput(simpleflightcomputerstacks);
        //</editor-fold>
        //<editor-fold desc="RADAR">
        SlimefunItemStack radarstack = new SlimefunItemStack("RADAR", Material.ACTIVATOR_RAIL, "Radar", "Used when guiding anti-air missiles");
        ItemStack[] radarrecipe = {
                null, ultraliteplatestack, null,
                ultraliteplatestack, simpleflightcomputerstack, ultraliteplatestack,
                new ItemStack(Material.REDSTONE), null, new ItemStack(Material.REDSTONE)
        };

        SlimefunItem radar = new SlimefunItem(group, radarstack, RecipeType.ENHANCED_CRAFTING_TABLE, radarrecipe);
        //</editor-fold>
        //<editor-fold desc="ROCKETFUEL">
        SlimefunItemStack rocketfuelstack = new SlimefunItemStack("ROCKETFUEL", Material.GUNPOWDER, "Rocket Fuel", "Burns with the power of 1000 coal...");
        ItemStack[] rocketfuelrecipe = {
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack,
                new ItemStack(Material.GLOWSTONE_DUST), new ItemStack(Material.COAL), new ItemStack(Material.GLOWSTONE_DUST),
                explosivepowderstack, new ItemStack(Material.GUNPOWDER), explosivepowderstack
        };
        SlimefunItem rocketfuel = new SlimefunItem(group, rocketfuelstack, RecipeType.ENHANCED_CRAFTING_TABLE, rocketfuelrecipe);
        //</editor-fold>
        //<editor-fold desc="SMALLWARHEAD">
        SlimefunItemStack smallwarheadstack = new SlimefunItemStack("SMALLWARHEAD", Material.TNT, "Simple Missile Warhead", "Used in creation of a missile.", "'Dont touch the red bit'");
        ItemStack[] smallwarheadrecipe = {
                null, SlimefunItems.ALUMINUM_INGOT, null,
                SlimefunItems.ALUMINUM_INGOT, explosivepowderstack, SlimefunItems.ALUMINUM_INGOT,
                null, null, null
        };

        SlimefunItem smallwarhead = new SlimefunItem(group, smallwarheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallwarheadrecipe);
        //</editor-fold>
        //<editor-fold desc="SMALLBODY">
        SlimefunItemStack smallbodystack = new SlimefunItemStack("SMALLBODY", Material.IRON_BLOCK, "Simple Missile Body", "Used in the creation of a missile", "'You better not dent that'");
        ItemStack[] smallbodyrecipe = {
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT,
                ultraliteingotstack, simpleflightcomputerstack, ultraliteingotstack,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallbody = new SlimefunItem(group, smallbodystack, RecipeType.ENHANCED_CRAFTING_TABLE, smallbodyrecipe);
        //</editor-fold>
        //<editor-fold desc="SMALLFIN">
        SlimefunItemStack smallfinstack = new SlimefunItemStack("SMALLFIN", Material.IRON_BOOTS, "Simple Missile Fins", "Used in the creation of a missile");
        ItemStack[] smallfinrecipe = {
                null, null, null,
                null, simpleflightcomputerstack, null,
                SlimefunItems.ALUMINUM_INGOT, null, SlimefunItems.ALUMINUM_INGOT
        };

        SlimefunItem smallfin = new SlimefunItem(group, smallfinstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallfinrecipe);
        //</editor-fold>
        //<editor-fold desc="SMALLMISSILE">
        SlimefunItemStack smallmissilestack = new SlimefunItemStack("SMALLMISSILE", Material.IRON_SWORD, "Simple GtG Missile", "Small Ground-to-Ground Missile","Normal Variant");
        ItemStack[] smallmissilerecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissile = new MissileItem(group, smallmissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipe, 1, "'Little Timmy never stood a chance...'");
        //</editor-fold>
        //<editor-fold desc="SMALLMISSILEHE">
        SlimefunItemStack smallmissilestackHE = new SlimefunItemStack("SMALLMISSILEHE", Material.IRON_SWORD, "Simple GtG Missile HE", "Small Ground-to-Ground Missile","High-Explosive Variant");
        ItemStack[] smallmissilerecipeHE = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                explosivepowderstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };

        MissileItem smallmissileHE = new MissileItem(group, smallmissilestackHE, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissilerecipeHE, 2, "'Large Timmy never stood a chance...'");
        //</editor-fold>
        //<editor-fold desc="SMALLMISSILELR">
        SlimefunItemStack smallmissileLRstack = new SlimefunItemStack("SMALLMISSILELR", Material.IRON_SWORD,"Simple GtG Missile LR","Long-Range Variant");
        ItemStack[] smallmissileLRrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack
        };
        MissileItem smallmissileLR = new MissileItem(group, smallmissileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileLRrecipe, 3, "'Far away timmy never stood a chance...'");
        //</editor-fold>
        //<editor-fold desc="SMALLMISSILEAC">
        SlimefunItemStack smallmissileACstack = new SlimefunItemStack("SMALLMISSILEAC", Material.IRON_SWORD,"Simple GtG Missile AC","Accurate Variant");
        ItemStack[] smallmissileACrecipe = {
                explosivepowderstack, smallwarheadstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                smallfinstack, rocketfuelstack, smallfinstack
        };
        MissileItem smallmissileAC = new MissileItem(group, smallmissileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, smallmissileACrecipe, 4, "'REALLY small timmy never stood a chance...'");
        //</editor-fold>
        //<editor-fold desc="GROUNDLAUNCHER">
        SlimefunItemStack groundlauncherstack = new SlimefunItemStack("GROUNDLAUNCHER", Material.DISPENSER, "Ground Launcher", "Shoots a specified area on the ground.", "Use a stick to set target coords", "Shift with a stick to check if it can fire", "Right click with a blaze rod to set cruise alt","Default cruise alt is Y:120", "Needs to be built on 1 green concrete block.");
        ItemStack[] groundlauncherrecipe = {
                SlimefunItems.REINFORCED_PLATE, null, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.REINFORCED_PLATE, new ItemStack(Material.FLINT_AND_STEEL), SlimefunItems.REINFORCED_PLATE
        };
        GroundMissileLauncher groundlauncher = new GroundMissileLauncher(group, groundlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, groundlauncherrecipe);
        //</editor-fold>
        //<editor-fold desc="MISSILEBODY">
        SlimefunItemStack missilebodystack = new SlimefunItemStack("MISSILEBODY", Material.SMOOTH_STONE, "Missile Body", "Missile body containing a flight computer");
        ItemStack[] missilebodyrecipe = {
                ultraliteplatestack, simpleflightcomputerstack, ultraliteplatestack,
                ultraliteplatestack, rocketfuelstack, ultraliteplatestack,
                ultraliteplatestack, rocketfuelstack, ultraliteplatestack
        };
        SlimefunItemStack missilebodystacks = (SlimefunItemStack) missilebodystack.clone();
        missilebodystacks.setAmount(4);

        SlimefunItem missilebody = new SlimefunItem(group, missilebodystack, RecipeType.ENHANCED_CRAFTING_TABLE, missilebodyrecipe);

        missilebody.setRecipeOutput(missilebodystacks);

        //</editor-fold>
        //<editor-fold desc="MISSILEFINS">
        SlimefunItemStack finsstack = new SlimefunItemStack("MISSILEFINS", Material.GOLDEN_BOOTS, "Missile Fins", "Able to move and direct the missile better");
        ItemStack[] finsrecipe = {
                null, null, null,
                ultraliteplatestack, null, ultraliteplatestack,
                ultraliteplatestack, null, ultraliteplatestack
        };

        SlimefunItem fins = new SlimefunItem(group, finsstack, RecipeType.ENHANCED_CRAFTING_TABLE, finsrecipe);
        //</editor-fold>
        //<editor-fold desc="ANTIAIRMISSILE">
        SlimefunItemStack antiAirMissilestack = new SlimefunItemStack("ANTIAIRMISSILE", Material.IRON_SWORD,"Anti Air Missile","Can Shoot Down Air Targets", "Used in a Anti Missile Launcher");
        ItemStack[] antiAirMissilerecipe = {
                null, smallwarheadstack, null,
                explosivepowderstack, missilebodystack, explosivepowderstack,
                rocketfuelstack, finsstack, rocketfuelstack
        };
        MissileItem antiAirMissile = new MissileItem(group, antiAirMissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, antiAirMissilerecipe, 5, "'Timmy's fireworks have been canceled'");
        //</editor-fold>
        //<editor-fold desc="ANTIELYTRAMISSILE">
        SlimefunItemStack antielytramissilestack = new SlimefunItemStack("ANTIELYTRAMISSILE", Material.GOLDEN_SWORD,"Anti Elytra Missile","Shoots Down People In Elytra", "Used in a Anti Elytra Launcher");
        ItemStack[] antielytramissilerecipe = {
                null, smallwarheadstack, null,
                explosivepowderstack, missilebodystack, explosivepowderstack,
                rocketfuelstack, finsstack, rocketfuelstack
        };
        MissileItem antielytramissile = new MissileItem(group, antielytramissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, antielytramissilerecipe, 5, "'Airborne Timmy Never Stood A Chance'");
        //</editor-fold>
        //<editor-fold desc="ANTIMISSILELAUNCHER">
        SlimefunItemStack antiairlauncherstack = new SlimefunItemStack("ANTIMISSILELAUNCHER", Material.DISPENSER, "Anti-Missile Launcher", "Targets and shoots down other missiles in the area.", "Use redstone to disable it", "Needs to be built on obsidian", "(Not Along Diagonals)");
        ItemStack[] antiairlauncherrecipe = {
                SlimefunItems.SILVER_INGOT, SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.SILVER_INGOT,
                SlimefunItems.SILVER_INGOT, null, SlimefunItems.SILVER_INGOT,
                SlimefunItems.LEAD_INGOT, new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.LEAD_INGOT
        };
        AntiMissileLauncher antiairlauncher = new AntiMissileLauncher(group, antiairlauncherstack, RecipeType.ENHANCED_CRAFTING_TABLE, antiairlauncherrecipe);
        //</editor-fold>
        //<editor-fold desc="MANPAD">
        SlimefunItemStack manpadstack = new SlimefunItemStack("MANPAD", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjFiNmVlNWJiZTVhZDQyOTY4MGMxYzE1Y2Y0MjBmOTgxMWUxMTRiNzY4NTRmODk5ZjBlZjA4ZmRlMzMyNzk4YyJ9fX0=", "Manpad", "Handheld anti-missile device", "Shift+RMB to begin tracking", "Release Shift fire");
        ItemStack[] manpadrecipe = {
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT),
                explosivepowderstack, sugarfuelstack, sugarfuelstack,
                new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT)
        };
        ManPad manpad = new ManPad(group, manpadstack, RecipeType.ENHANCED_CRAFTING_TABLE, manpadrecipe);
        //</editor-fold>
        //<editor-fold desc="MISSILE">
        SlimefunItemStack missilestack = new SlimefunItemStack("MISSILE", Material.GOLDEN_SWORD,"GtG Missile","Normal Variant");
        ItemStack[] missilerecipe = {
                null, smallwarheadstack, null,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missile = new MissileItem(group, missilestack, RecipeType.ENHANCED_CRAFTING_TABLE, missilerecipe, 6, "'Your friendly neighbourhood missile'");
        //</editor-fold>
        //<editor-fold desc="MISSILEHE">
        SlimefunItemStack missileHEstack = new SlimefunItemStack("MISSILEHE", Material.GOLDEN_SWORD,"GtG Missile HE","High Explosive Variant");
        ItemStack[] missileHErecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileHE = new MissileItem(group, missileHEstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileHErecipe, 7, "'Your not-so friendly neighbourhood missile'");
        //</editor-fold>
        //<editor-fold desc="MISSILELR">
        SlimefunItemStack missileLRstack = new SlimefunItemStack("MISSILELR", Material.GOLDEN_SWORD,"GtG Missile LR","Long Range Variant");
        ItemStack[] missileLRrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileLR = new MissileItem(group, missileLRstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileLRrecipe, 8, "'Your friendly state missile'");
        //</editor-fold>
        //<editor-fold desc="MISSILEAC">
        SlimefunItemStack missileACstack = new SlimefunItemStack("MISSILEAC", Material.GOLDEN_SWORD,"GtG Missile AC","Accurate Variant");
        ItemStack[] missileACrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, smallfinstack, rocketfuelstack,
        };
        MissileItem missileAC = new MissileItem(group, missileACstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileACrecipe, 9, "'Your friendly home-defending missile'");
        //</editor-fold>
        //<editor-fold desc="PLAYERLIST">
        SlimefunItemStack playerliststack = new SlimefunItemStack("PLAYERLIST", Material.ENCHANTED_BOOK,"Player Lister","Right Click to set the players", "for a ID", "THESE ID'S ARE GLOBAL", "ANYONE CAN EDIT THEM IF", "THEY HAVE THE ID");
        ItemStack[] playerlistrecipe = {
                null,ultraliteingotstack,null,
                ultraliteingotstack,new ItemStack(Material.BOOK),ultraliteingotstack,
                null,ultraliteingotstack,null
        };
        PlayerList playerList = new PlayerList(group, playerliststack, RecipeType.ENHANCED_CRAFTING_TABLE, playerlistrecipe);
        //</editor-fold>
        //<editor-fold desc="ANTIELYTRA">
        SlimefunItemStack antielytrastack = new SlimefunItemStack("ANTIELYTRA", Material.DISPENSER, "Anti-Elytra Missile Launcher", "Fires a missile at nearby player", "that are using an elytra", "Excluding those on the", "ID assigned to this", "use a stick to set the group ID");
        ItemStack[] antielytralauncherrecipe = {
                ultraliteplatestack, null, ultraliteplatestack,
                SlimefunItems.REINFORCED_PLATE, null, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.REINFORCED_PLATE
        };
        AntiElytraLauncher antiElytraLauncher = new AntiElytraLauncher(group, antielytrastack, RecipeType.ENHANCED_CRAFTING_TABLE, antielytralauncherrecipe);

        MissileWarfare main = MissileWarfare.getInstance();
        //</editor-fold>
        //<editor-fold desc="WARHEAD">
        SlimefunItemStack warheadstack = new SlimefunItemStack("WARHEAD", Material.TNT, "Missile Warhead", "Used in creation of a missile.", "'Potato-be-gone'");
        ItemStack[] warheadrecipe = {
                null, ultraliteingotstack, null,
                ultraliteingotstack, compressedpowderstack, ultraliteingotstack,
                null, null, null
        };

        SlimefunItem warhead = new SlimefunItem(group, warheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, warheadrecipe);
        //</editor-fold>
        //<editor-fold desc="REINFORCEDWARHEAD">
        SlimefunItemStack warheadAPstack = new SlimefunItemStack("WARHEADAP", Material.TNT, "Armour Piercing Missile", "Used in creation of a missile.", "'Potato-be-gone'");
        ItemStack[] warheadAPrecipe = {
                null, SlimefunItems.LEAD_INGOT, null,
                ultraliteingotstack, compressedpowderstack, ultraliteingotstack,
                null, null, null
        };

        SlimefunItem warheadAP = new SlimefunItem(group, warheadAPstack, RecipeType.ENHANCED_CRAFTING_TABLE, warheadAPrecipe);
        //</editor-fold>
        //<editor-fold desc="APMISSILET1">
        SlimefunItemStack missileAPstack = new SlimefunItemStack("MISSILEAPONE", Material.DIAMOND_SWORD,"GtG Missile AP Tier 1","Armour Piercing Variant", "Goes through 1 block before exploding", "Wont go through obsidian");
        ItemStack[] missileAPrecipe = {
                compressedpowderstack, smallwarheadstack, compressedpowderstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                smallfinstack, finsstack, smallfinstack,
        };
        MissileItem missileAP = new MissileItem(group, missileAPstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileAPrecipe, 10, "'Your Basement Isnt Safe For Long!'");
        //</editor-fold>
        //<editor-fold desc="APMISSILET2">
        SlimefunItemStack missileAPtstack = new SlimefunItemStack("MISSILEAPTWO", Material.DIAMOND_SWORD,"GtG Missile AP Tier 2","Armour Piercing Variant", "Goes through 1 block before exploding", "50% Chance to go through obsidian", "10% chance to break the hit obsidian");
        ItemStack[] missileAPtrecipe = {
                ultraliteingotstack,ultraliteplatestack,ultraliteingotstack,
                ultraliteingotstack,missileAPstack,ultraliteingotstack,
                sugarfuelstack,sugarfuelstack,sugarfuelstack
        };
        MissileItem missileAPt = new MissileItem(group, missileAPtstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileAPtrecipe, 11, "'Your bunker isnt safe for long!'");
        //</editor-fold>
        //<editor-fold desc="APMISSILET3">
        SlimefunItemStack missileAPtrstack = new SlimefunItemStack("MISSILEAPTHR", Material.DIAMOND_SWORD,"GtG Missile AP Tier 3","Armour Piercing Variant", "Goes through 2 block before exploding", "75% Chance to go through obsidian", "25% chance to break the hit obsidian");
        ItemStack[] missileAPtrrecipe = {
                ultraliteingotstack,ultraliteplatestack,ultraliteingotstack,
                ultraliteingotstack,missileAPtstack,ultraliteingotstack,
                sugarfuelstack,sugarfuelstack,sugarfuelstack
        };
        MissileItem missileAPtr = new MissileItem(group, missileAPtrstack, RecipeType.ENHANCED_CRAFTING_TABLE, missileAPtrrecipe, 12, "'Your bunker definitely isnt safe for long!'");
        //</editor-fold>
        //<editor-fold desc="CHLORINE">
        SlimefunItemStack chlorinestack = new SlimefunItemStack("CHLORINE", Material.SUGAR,"Chlorine","Poisonous", "Causes a slow and painful death!");
        ItemStack[] chlorinerecipe = {
                new ItemStack(Material.SOUL_SAND), null, null,
                null, null, null,
                null, null, null
        };
        SlimefunItem chlorine = new SlimefunItem(group, chlorinestack, RecipeType.ORE_WASHER, chlorinerecipe);
        RecipeType.ORE_WASHER.register(chlorinerecipe, chlorinestack);
        chlorine.addItemHandler((ItemUseHandler) playerRightClickEvent -> playerRightClickEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 2)));
        //</editor-fold>
        //<editor-fold desc="CHLORINEPELLET">
        SlimefunItemStack chlorinepelletstack = new SlimefunItemStack("CHLORINEPELLET", Material.LIME_DYE,"Chlorine Pellet","A pellet of chlorine");
        ItemStack[] chlorinepelletrecipe = {
                chlorinestack, SlimefunItems.SULFATE, chlorinestack,
                SlimefunItems.SULFATE, SlimefunItems.SALT, SlimefunItems.SULFATE,
                chlorinestack, SlimefunItems.SULFATE, chlorinestack
        };
        SlimefunItem chlorinepellet = new SlimefunItem(group, chlorinepelletstack, RecipeType.COMPRESSOR, chlorinepelletrecipe);
        chlorine.addItemHandler((ItemUseHandler) playerRightClickEvent -> playerRightClickEvent.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 10, 2)));
        //</editor-fold>
        //<editor-fold desc="GASMISSILE">
        SlimefunItemStack missilegasstack = new SlimefunItemStack("MISSILEGAS", Material.GOLDEN_SWORD,"GtG Gas Missile","Gas Deploying Variant", "Deploys gas on hitting a target");
        ItemStack[] missilegasrecipe = {
                chlorinepelletstack, chlorinepelletstack, chlorinepelletstack,
                rocketfuelstack, missilebodystack, rocketfuelstack,
                rocketfuelstack, finsstack, rocketfuelstack
        };
        MissileItem missilegas = new MissileItem(group, missilegasstack, RecipeType.ENHANCED_CRAFTING_TABLE, missilegasrecipe, 13, "'Why level the place when its empty?'");
        //</editor-fold>
        //<editor-fold desc="EXCAVATIONMISSILE">
        SlimefunItemStack excabmissilestack = new SlimefunItemStack("MISSILEEXCAV", Material.WOODEN_SWORD,"Excavation Missile","Utility Missile", "Has a large explosion radius but little damage", "Used for mining");
        ItemStack[] excabmissilerecipe = {
                explosivepowderstack, explosivepowderstack, explosivepowderstack,
                sugarfuelstack, smallbodystack, sugarfuelstack,
                sugarfuelstack, smallfinstack, sugarfuelstack
        };
        MissileItem excavmissile = new MissileItem(group, excabmissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, excabmissilerecipe, 14, "'Better than a 100 Pickaxes!'");
        //</editor-fold>
        //<editor-fold desc="COBWEBMISSILE">
        SlimefunItemStack stickymissilestack = new SlimefunItemStack("MISSILESTICK", Material.IRON_SWORD,"Sticky Missile","Trap Missile", "A small explosion that releases cobwebs");
        ItemStack[] stickymissilerecipe = {
                new ItemStack(Material.STRING), new ItemStack(Material.STRING), new ItemStack(Material.STRING),
                sugarfuelstack, missilebodystack, sugarfuelstack,
                rocketfuelstack, finsstack, rocketfuelstack
        };
        MissileItem stickymissile = new MissileItem(group, stickymissilestack, RecipeType.ENHANCED_CRAFTING_TABLE, stickymissilerecipe, 15, "'Dont ask what its made of.'");
        //</editor-fold>
        //<editor-fold desc="ADVANCEDMISSILEBODY">
        SlimefunItemStack advancedmissilebodystack = new SlimefunItemStack("ADVANCEDMISSILEBODY", Material.GRAY_CONCRETE, "Advanced Missile Body", "An upgraded version ofthe missile body");
        ItemStack[] advancedmissilebodyrecipe = {
                rocketfuelstack, simpleflightcomputerstack, rocketfuelstack,
                ultraliteplatestack, missilebodystack, ultraliteplatestack,
                rocketfuelstack, simpleflightcomputerstack, rocketfuelstack
        };

        SlimefunItem advancedmissilebody = new SlimefunItem(group, advancedmissilebodystack, RecipeType.ENHANCED_CRAFTING_TABLE, advancedmissilebodyrecipe);

        missilebody.setRecipeOutput(missilebodystacks);

        //</editor-fold>
        //<editor-fold desc="LARGEWARHEAD">
        SlimefunItemStack heavywarheadstack = new SlimefunItemStack("HEAVYWARHEAD", Material.RED_CONCRETE, "Heavy Warhead", "A warhead packed with heavy explosives");
        ItemStack[] heavywarheadrecipe = {
                compressedpowderstack, warheadstack, compressedpowderstack,
                warheadstack, ultraliteplatestack, warheadstack,
                compressedpowderstack, warheadstack, compressedpowderstack
        };

        SlimefunItem heavywarhead = new SlimefunItem(group, heavywarheadstack, RecipeType.ENHANCED_CRAFTING_TABLE, heavywarheadrecipe);
        //</editor-fold>
        //<editor-fold desc="ICBM">
        SlimefunItemStack icbmstack = new SlimefunItemStack("MISSILEICBM", Material.DIAMOND_SWORD,"ICBM","Long-Range Missile", "Inter-Continental Ballistic Missile");
        ItemStack[] icbmrecipe = {
                rocketfuelstack, heavywarheadstack, rocketfuelstack,
                rocketfuelstack, advancedmissilebodystack, rocketfuelstack,
                rocketfuelstack, SlimefunItems.STEEL_THRUSTER, rocketfuelstack
        };
        MissileItem icbm = new MissileItem(group, icbmstack, RecipeType.ENHANCED_CRAFTING_TABLE, icbmrecipe, 16, "'Nowhere is safe.'");
        //</editor-fold>
        //</editor-fold>
        //Register All
        //<editor-fold desc="== Register Items ==">
        //Guides
        guide.register(main);
        playerList.register(main);
        //Missile Launchers
        groundlauncher.register(main);
        antiElytraLauncher.register(main);
        antiairlauncher.register(main);
        //Handhelds
        manpad.register(main);
        // Materials
        sugarfuel.register(main);
        explosivepowder.register(main);
        compressedpowder.register(main);
        ultraliteingot.register(main);
        ultraliteplate.register(main);
        chlorine.register(main);
        chlorinepellet.register(main);
        simpleflightcomputer.register(main);
        radar.register(main);
        rocketfuel.register(main);
        //Missile Parts
        smallwarhead.register(main);
        warhead.register(main);
        warheadAP.register(main);
        smallbody.register(main);
        smallfin.register(main);
        missilebody.register(main);
        fins.register(main);
        advancedmissilebody.register(main);
        heavywarhead.register(main);
        //Missile Types
        antiAirMissile.register(main);
        antielytramissile.register(main);
        smallmissile.register(main);
        smallmissileHE.register(main);
        smallmissileLR.register(main);
        smallmissileAC.register(main);
        missile.register(main);
        missileHE.register(main);
        missileLR.register(main);
        missileAC.register(main);
        missileAP.register(main);
        missileAPt.register(main);
        missileAPtr.register(main);
        missilegas.register(main);
        excavmissile.register(main);
        stickymissile.register(main);
        icbm.register(main);

        //</editor-fold>

        //templates
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
        //<editor-fold desc="RESEARCH">
        NamespacedKey basicfuelkey = new NamespacedKey(main, "basic_fuel");
        Research basicfuel = new Research(basicfuelkey, 3467341, "Inedible Sugar", 10);
        basicfuel.addItems(sugarfuel);
        basicfuel.register();

        NamespacedKey explosiveskey = new NamespacedKey(main, "explosives");
        Research explosives = new Research(explosiveskey, 3447321, "Explosive Diarrhea", 15);
        explosives.addItems(explosivepowder, compressedpowder);
        explosives.register();

        NamespacedKey chlorinekey = new NamespacedKey(main, "chlorine");
        Research chlorineres = new Research(chlorinekey, 214141, "Cleaner Pools!", 15);
        chlorineres.addItems(chlorine, chlorinepellet);
        chlorineres.register();

        NamespacedKey groundlauncherskey = new NamespacedKey(main, "groundlauncher");
        Research groundlauncherres = new Research(groundlauncherskey, 34117322, "Ground Missile Launcher", 15);
        groundlauncherres.addItems(groundlauncher);
        groundlauncherres.register();

        NamespacedKey antimissilekey = new NamespacedKey(main, "antimissilelauncher");
        Research antimissileres = new Research(antimissilekey, 3424321, "Iron Dome.", 15);
        antimissileres.addItems(antiairlauncher, manpad, antiAirMissile);
        antimissileres.register();

        NamespacedKey antielytramissilekey = new NamespacedKey(main, "antielytramissilelauncher");
        Research antielytramissileres = new Research(antielytramissilekey, 34213253, "Ender Dome?", 20);
        antielytramissileres.addItems(antiElytraLauncher, antielytramissile);
        antielytramissileres.register();

        NamespacedKey smallgmissilepartskey = new NamespacedKey(main, "smallgmissileparts");
        Research smallgmissileparts = new Research(smallgmissilepartskey, 2667313, "Missile with extra steps", 15);
        smallgmissileparts.addItems(smallwarhead, smallbody, smallfin);
        smallgmissileparts.register();

        NamespacedKey gmissilepartskey = new NamespacedKey(main, "gmissileparts");
        Research gmissileparts = new Research(gmissilepartskey, 2667313, "Missile with extra steps", 15);
        gmissileparts.addItems(warhead, warheadAP, missilebody, fins);
        gmissileparts.register();

        NamespacedKey smallgmissilekey = new NamespacedKey(main, "smallgmissile");
        Research smallgmissile = new Research(smallgmissilekey, 35673323, "5 Shades Of Gray", 20);
        smallgmissile.addItems(smallmissile, smallmissileHE, smallmissileLR, smallmissileLR, smallmissileAC);
        smallgmissile.register();

        NamespacedKey advancedfuelkey = new NamespacedKey(main, "advancedfuel");
        Research advancedfuel = new Research(advancedfuelkey, 3461423, "Advanced (and even less edible) Fuels!", 20);
        advancedfuel.addItems(rocketfuel);
        advancedfuel.register();

        NamespacedKey missilepartskey = new NamespacedKey(main, "missileparts");
        Research missileparts = new Research(missilepartskey, 4461423, "Missile with even more steps", 25);
        missileparts.addItems(ultraliteingot, ultraliteplate, simpleflightcomputer, radar);
        missileparts.register();

        NamespacedKey gmissilekey = new NamespacedKey(main, "gmissile");
        Research gmissile = new Research(gmissilekey, 341243, "The Colors Of The Rainbow!", 20);
        gmissile.addItems(missile, missileHE, missileLR, missileAC);

        NamespacedKey gmissileAPkey = new NamespacedKey(main, "gmissileAP");
        Research gmissileAP = new Research(gmissileAPkey, 341246,"The Penetrator Trio", 15);
        gmissileAP.addItems(missileAP, missileAPt, missileAPtr);

        NamespacedKey gmissileGASkey = new NamespacedKey(main, "gmissileGAS");
        Research gmissileGAS = new Research(gmissileGASkey, 341226,"One Ball Man", 15);
        gmissileGAS.addItems(missilegas);
        //</editor-fold>
    }
}
