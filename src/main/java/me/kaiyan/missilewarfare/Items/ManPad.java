package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.Missiles.MissileController;
import me.kaiyan.missilewarfare.Translations;
import me.kaiyan.missilewarfare.VariantsAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class ManPad extends SlimefunItem {
    public static List<Player> active = new ArrayList<>();
    public final int range = 300*300;

    public ManPad(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        ItemUseHandler itemUseHandler = this::itemUse;
        addItemHandler(itemUseHandler);
    }

    private void itemUse(PlayerRightClickEvent event) {
        SlimefunItem manpad = event.getSlimefunItem().get();
        if (event.getPlayer().isSneaking() && !active.contains(event.getPlayer())) {
            event.getPlayer().sendMessage(Translations.get("messages.manpad.locking"));
            active.add(event.getPlayer());
            new BukkitRunnable() {
                public int scanLinepos = 0;
                public boolean scanLineReturn = false;

                @Override
                public void run() {
                    //check if changed weapon
                    if (!event.getSlimefunItem().isPresent()) {
                        event.getPlayer().sendMessage(Translations.get("messages.manpad.itemchanged"));
                    } else if (event.getSlimefunItem().get() != manpad) {
                        event.getPlayer().sendMessage(Translations.get("messages.manpad.itemchanged"));
                    }
                    MissileController lockedmissile = null;
                    //Get Missiles In Range
                    List<MissileController> missiles = new ArrayList<>();
                    for (MissileController missile : MissileWarfare.activemissiles) {
                        if (missile.isgroundmissile) {
                            if (missile.pos.distanceSquared(event.getPlayer().getLocation().toVector()) < range) {
                                missiles.add(missile);
                            }
                        }
                    }

                    //Get looked at missile
                    float mindist = 100000;

                    for (MissileController missile : missiles) {
                        Vector dir = event.getPlayer().getLocation().getDirection();
                        Location loc = event.getPlayer().getLocation();
                        Vector target = loc.add(dir.multiply(loc.distance(missile.pos.toLocation(event.getPlayer().getWorld())))).toVector();
                        float dist = (float) Math.floor(target.distanceSquared(missile.pos));
                        float acdist = (float) loc.distanceSquared(missile.pos.toLocation(event.getPlayer().getWorld()));
                        if (target.isInSphere(missile.pos, missile.speed+((acdist/range)*8)) && dist < mindist) {
                            lockedmissile = missile;
                            mindist = dist;
                        }
                    }
                    ComponentBuilder scanline = new ComponentBuilder();
                    if (lockedmissile == null) {
                        scanline.append("[").color(ChatColor.WHITE);
                        if (scanLinepos == 0) {
                            scanLinepos++;
                            scanline.append("X").color(ChatColor.BLUE);
                            scanline.append("XX").color(ChatColor.WHITE);
                            event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.B));
                            scanLineReturn = false;
                        } else if (scanLinepos == 2) {
                            scanLinepos--;
                            scanLineReturn = true;
                            scanline.append("XX").color(ChatColor.WHITE);
                            scanline.append("X").color(ChatColor.BLUE);
                            event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.PIANO, Note.flat(1, Note.Tone.B));
                        } else if (!scanLineReturn) {
                            scanLinepos++;
                            scanline.append("X").color(ChatColor.WHITE);
                            scanline.append("X").color(ChatColor.BLUE);
                            scanline.append("X").color(ChatColor.WHITE);
                        } else if (scanLineReturn) {
                            scanLinepos--;
                            scanline.append("X").color(ChatColor.WHITE);
                            scanline.append("X").color(ChatColor.BLUE);
                            scanline.append("X").color(ChatColor.WHITE);
                        }
                        scanline.append("]").color(ChatColor.WHITE);
                    } else {
                        scanline.append("MissileType: " + VariantsAPI.getStrVariantFromInt(lockedmissile.type)).color(ChatColor.GOLD);
                        scanline.append("[");
                        scanline.append("XXX").color(ChatColor.RED);
                        scanline.append("]").color(ChatColor.WHITE);
                        scanline.append("Dist: " + Math.round(event.getPlayer().getLocation().distance(lockedmissile.pos.toLocation(event.getPlayer().getWorld())))).color(ChatColor.GOLD);
                        event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.STICKS, Note.flat(1, Note.Tone.E));
                        event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.GUITAR, Note.flat(1, Note.Tone.F));
                        event.getPlayer().playNote(event.getPlayer().getLocation(), Instrument.FLUTE, Note.flat(1, Note.Tone.E));
                    }

                    // SendScanLine
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, scanline.create());

                    // Check if sneaking
                    if (!event.getPlayer().isSneaking()) {
                        if (lockedmissile == null){
                            event.getPlayer().sendMessage(Translations.get("messages.manpad.notarget"));
                            this.cancel();
                        } else {
                            active.remove(event.getPlayer());
                            MissileController missile = new MissileController(false, event.getPlayer().getLocation().toVector(), lockedmissile.pos, 5, event.getPlayer().getWorld(), 3, 0, 1, event.getPlayer().getLocation().getDirection());
                            missile.FireMissileAtMissile(lockedmissile);
                            event.getPlayer().getInventory().remove(manpad.getItem());
                            event.getPlayer().updateInventory();
                            this.cancel();
                        }
                    }
                }
            }.runTaskTimer(MissileWarfare.getInstance(), 0, 2);
        }
        /*else {
            //DEBUGGING TOOL, IF IN RELEASED BUILD CONTACT ME IMMEDIATELY.
            MissileController missile = new MissileController(true, event.getPlayer().getLocation().toVector(), event.getPlayer().getLocation().toVector(), 5, event.getPlayer().getWorld(), 5, 5, 1);
            MissileWarfare.activemissiles.add(missile);
            event.getPlayer().sendMessage("Spawned debug missile at : "+missile.pos);
        }*/
    }
}
