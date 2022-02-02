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
                .append(ChatColor.BLUE+"Ground Missile Launcher\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"))
                .append(ChatColor.BLUE+"Anti-Missile Launcher\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "3"))
                .create();
        BaseComponent[] groundMissileLauncher = new ComponentBuilder(ChatColor.BOLD+"Ground Missile Launcher\n")
                .append("The Ground Missile Launcher fires missiles at the given coordinates.\n\n")
                .append("Right clicking with a stick will set its coords\n")
                .append("Right clicking with a blaze rod will set the ")
                .append("cruise alt\n")
                .create();

        meta.spigot().addPage(homepage);
        meta.spigot().addPage(groundMissileLauncher);

        item.setItemMeta(meta);
    }
}
