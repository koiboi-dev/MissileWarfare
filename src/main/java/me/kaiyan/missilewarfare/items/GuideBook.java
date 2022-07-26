package me.kaiyan.missilewarfare.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.kaiyan.missilewarfare.util.Translations;
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

        meta.setTitle(ChatColor.translateAlternateColorCodes('&', Translations.get("guidebook.title")));
        meta.setAuthor(ChatColor.translateAlternateColorCodes('&', Translations.get("guidebook.author")));

        String[] content = Translations.getarr("guidebook.homepage.content");

        BaseComponent[] homepage = new ComponentBuilder(ChatColor.BOLD+ Translations.get("guidebook.homepage.title") +"\n").color(ChatColor.BOLD)
                .append(Translations.get("guidebook.homepage.subtitle")+"\n")
                .append(ChatColor.BOLD+Translations.get("guidebook.homepage.contenttitle")+"\n")
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+content[0]+"\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"))
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+content[1]+"\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "3"))
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+content[2]+"\n").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "4"))
                .append(ChatColor.BLUE+""+ChatColor.UNDERLINE+content[3]+"").event(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "5"))
                .create();

        BaseComponent[] groundMissileLauncher = new ComponentBuilder(ChatColor.BOLD+Translations.get("guidebook.groundlauncherpage.title")+"\n")
                .append(Translations.getPage("guidebook.groundlauncherpage"))
                .create();

        BaseComponent[] antiMissileLauncher = new ComponentBuilder(ChatColor.BOLD+Translations.get("guidebook.antimissilepage.title")+"\n")
                .append(Translations.getPage("guidebook.antimissilepage"))
                .create();

        BaseComponent[] manpad = new ComponentBuilder(ChatColor.BOLD+Translations.get("guidebook.manpadpage.title")+"\n")
                .append(Translations.getPage("guidebook.manpadpage"))
                .create();

        BaseComponent[] antiElytraLaunchers = new ComponentBuilder(ChatColor.BOLD+Translations.get("guidebook.antielytrapage.title")+"\n")
                .append(Translations.getPage("guidebook.antielytrapage"))
                .create();

        BaseComponent[] playerID = new ComponentBuilder(ChatColor.BOLD+Translations.get("guidebook.playeridpage.title")+"\n")
                .append(Translations.getPage("guidebook.playeridpage"))
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
