package me.kaiyan.missilewarfare.Blocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockDispenseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.Missiles.ElytraMissileController;
import me.kaiyan.missilewarfare.PlayerID;
import me.kaiyan.missilewarfare.Translations;
import me.kaiyan.missilewarfare.VariantsAPI;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class AntiElytraLauncher extends SlimefunItem{
    public final int range = 490000;

    public AntiElytraLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        //cancel thing on place
        BlockPlaceHandler blockPlaceHandler = new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockData data = event.getBlockPlaced().getBlockData();
                ((Directional)data).setFacing(BlockFace.UP);
                event.getBlockPlaced().setBlockData(data);
                Block block = event.getBlockPlaced();
                //Block bottom = world.getBlockAt(event.getBlock().getLocation().subtract(new Vector(0, 2, 0)));
                if (correctlyBuilt(block)){
                    event.getPlayer().sendMessage(Translations.get("messages.launchers.createantielytra.success"));
                }else{
                    event.getPlayer().sendMessage(Translations.get("messages.launchers.createantielytra.failure"));
                }
            }
        };
        addItemHandler(blockPlaceHandler);

        BlockDispenseHandler blockDispenseHandler = this::blockDispense;
        addItemHandler(blockDispenseHandler);

        addItemHandler(new BlockTicker(){

            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                TileState state = (TileState) block.getState();
                PersistentDataContainer cont = state.getPersistentDataContainer();
                Player locked = null;
                if (!block.isBlockPowered()) {
                    Collection<? extends Player> missiles = MissileWarfare.getInstance().getServer().getOnlinePlayers();
                    if (!missiles.isEmpty()) {
                        for (Player player : missiles) {
                            if (block.getLocation().distanceSquared(player.getLocation()) < range) {
                                if (player.isGliding() && !PlayerID.targets.contains(player)) {
                                    List<OfflinePlayer> ignore = PlayerID.players.get(cont.get(new NamespacedKey(MissileWarfare.getInstance(), "groupid"), PersistentDataType.STRING));
                                    if (ignore == null){
                                        locked = player;
                                        break;
                                    } else if (ignore.contains(player)) {
                                        locked = player;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    state.update();
                    if (locked != null){
                        UUID playerUUID = locked.getUniqueId();
                        PlayerID.targets.add(MissileWarfare.getInstance().getServer().getPlayer(playerUUID));

                        Player finalLocked = locked;
                        finalLocked.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(Translations.get("messages.elytraattack.locking")).color(ChatColor.RED).create());
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                Player player = MissileWarfare.getInstance().getServer().getPlayer(playerUUID);
                                if (player == null){
                                    PlayerID.targets = new ArrayList<>();
                                    return;
                                }
                                if (player.isGliding()) {
                                    finalLocked.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(Translations.get("messages.elytraattack.locked")).color(ChatColor.DARK_RED).create());
                                    fireMissile((Dispenser) block.getState(), MissileWarfare.getInstance().getServer().getPlayer(playerUUID));
                                }else if (player.isInsideVehicle()){
                                    if (!player.getVehicle().isOnGround()){
                                        finalLocked.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(Translations.get("messages.elytraattack.locked")).color(ChatColor.DARK_RED).create());
                                        fireMissile((Dispenser) block.getState(), MissileWarfare.getInstance().getServer().getPlayer(playerUUID));
                                    }
                                }
                            }
                        }.runTaskLater(MissileWarfare.getInstance(), 80);
                    }
                }
            }
        });

        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);
    }

    private void onBlockRightClick(PlayerRightClickEvent event) {
        if (SlimefunItem.getByItem(event.getItem()) == SlimefunItem.getById("PLAYERLIST")){
            event.cancel();
            TileState state = (TileState) event.getClickedBlock().get().getBlockData();
            PersistentDataContainer cont = state.getPersistentDataContainer();
            cont.set(new NamespacedKey(MissileWarfare.getInstance(), "groupid"), PersistentDataType.STRING, event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(MissileWarfare.getInstance(), "id"), PersistentDataType.STRING));
            state.update();
        }
    }

    private void blockDispense(BlockDispenseEvent event, Dispenser dispenser, Block block, SlimefunItem item) {
        event.setCancelled(true);
    }

    public void fireMissile(Dispenser disp, Player target){
        ItemStack missileitem = VariantsAPI.getOtherFirstMissile(disp.getInventory(), SlimefunItem.getById("ANTIELYTRAMISSILE"));
        if (SlimefunItem.getByItem(missileitem) == SlimefunItem.getById("ANTIELYTRAMISSILE")) {
            ItemUtils.consumeItem(missileitem, false);
            ElytraMissileController missile = new ElytraMissileController(5, 2.5f, disp.getBlock().getLocation().add(new Vector(0.5, 1.5, 0.5)).toVector(), disp.getWorld(), target);
            missile.FireMissile(target);
        } else {
            PlayerID.targets.remove(target);
        }
    }

    public boolean correctlyBuilt(Block block) {
        return block.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN;
    }
}
