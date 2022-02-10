package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class GuideBook extends SlimefunItem {
    public GuideBook(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        BookMeta meta = (BookMeta) item.getItemMeta();

        meta.setTitle("Missiles for Dummies");
        meta.setAuthor("Kaiyan2006");

        BaseComponent[] homepage = new ComponentBuilder(ChatColor.BOLD+"Missile Warfare\n").color(ChatColor.BOLD)
                .append("The guide book for Missile Warfare\n")
                .append(ChatColor.BOLD+"Contents:\n")
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"Ground Missile Launcher\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"))
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"Anti-Missile Launcher\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "3"))
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"ManPad\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "4"))
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+"Anti-Elytra Launchers").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "5"))
                .create();
        BaseComponent[] groundMissileLauncher = new ComponentBuilder(ChatColor.BOLD+"Ground Missile Launcher\n")
                .append("The Ground Missile Launcher fires missiles at the given coordinates.\n")
                .append("Right clicking with a stick will set its coords\n")
                .append("Right clicking with a blaze rod will set the cruise alt\n")
                .append(ChatColor.BOLD+"Will hit friendly missiles")
                .create();

        BaseComponent[] antiMissileLauncher = new ComponentBuilder(ChatColor.BOLD+"Anti-Missile Launcher\n")
                .append("The Anti-Missile Launcher targets and shoots down any incoming missiles.\n")
                .append("It will shoot down any missiles, whether friendly or not, that comes into its range\n")
                .append("Use a redstone input to disable it from firing.")
                .create();

        BaseComponent[] manpad = new ComponentBuilder(ChatColor.BOLD+"Manpads\n")
                .append("Manpads are handheld items that can be used to identify and shoot down enemy missiles\n")
                .append("Unlike Anti-Missile Launchers Manpads have a guaranteed chance of shooting down opponent missiles\n")
                .append("Manpads are used by holding shift to begin scanning")
                .create();

        BaseComponent[] antiElytraLaunchers = new ComponentBuilder(ChatColor.BOLD+"Anti-Elytra Launchers\n")
                .append("Anti Elytra lanchers fire missiles at players within their radius, breaking their elytra.\n")
                .append("This is really useful for city defence during wars.\nSet ignored players using ")
                .append(ChatColor.BLUE+"PlayerID").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "6"))
                .append(" use a stick to set the ID of the launcher.")
                .create();

        BaseComponent[] playerID = new ComponentBuilder(ChatColor.BOLD+"PlayerID\n")
                .append("PlayerID is used to set players that some launchers use to ignore.\n")
                .append("Use a PlayerList in order to set the ID and the players it should ignore.\n")
                .append(ChatColor.BOLD+"ANYONE CAN SET ANY ID, KEEP YOUR ID'S SECRET\n")
                .create();


        meta.spigot().addPage(homepage);
        meta.spigot().addPage(groundMissileLauncher);
        meta.spigot().addPage(antiMissileLauncher);
        meta.spigot().addPage(manpad);
        meta.spigot().addPage(antiElytraLaunchers);
        meta.spigot().addPage(playerID);

        item.setItemMeta(meta);
    }
}
