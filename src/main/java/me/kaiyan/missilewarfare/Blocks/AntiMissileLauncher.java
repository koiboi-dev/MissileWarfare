package me.kaiyan.missilewarfare.Blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockDispenseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.Missiles.MissileController;
import me.kaiyan.missilewarfare.Translations;
import me.kaiyan.missilewarfare.VariantsAPI;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.List;

public class AntiMissileLauncher extends SlimefunItem{
    public final int range = 40000;

    public AntiMissileLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
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
                    event.getPlayer().sendMessage(Translations.get("messages.launchers.createantiair.success"));
                }else{
                    event.getPlayer().sendMessage(Translations.get("messages.launchers.createantiair.failure"));
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
                if (!block.isBlockPowered()) {
                    List<MissileController> missiles = MissileWarfare.activemissiles;
                    MissileController locked = null;
                    if (!missiles.isEmpty()) {
                        for (MissileController missile : missiles) {
                            if (block.getLocation().distanceSquared(missile.pos.toLocation(missile.world)) < range && missile.isgroundmissile) {
                                locked = missile;
                                break;
                            }
                        }
                    }
                    state.update();
                    try {
                        if (locked != null && cont.get(new NamespacedKey(MissileWarfare.getInstance(), "timesincelastshot"), PersistentDataType.INTEGER) <= System.currentTimeMillis()) {
                            cont.set(new NamespacedKey(MissileWarfare.getInstance(), "timesincelastshot"), PersistentDataType.INTEGER, (int)System.currentTimeMillis()+1000);
                            fireMissile((Dispenser) block.getState(), locked);
                        }
                    } catch (NullPointerException e){
                        cont.set(new NamespacedKey(MissileWarfare.getInstance(), "timesincelastshot"), PersistentDataType.INTEGER, Integer.MIN_VALUE);
                        state.update();
                    }
                }
            }
        });
    }

    private void blockDispense(BlockDispenseEvent event, Dispenser dispenser, Block block, SlimefunItem item) {
        event.setCancelled(true);
    }

    /*@Deprecated
        public void fireMissile(PlayerRightClickEvent event){
            Dispenser disp = (Dispenser) Objects.requireNonNull(event.getInteractEvent().getClickedBlock()).getState();
            int type = hasAmmo(disp.getInventory(), (SmallGtGMissile) itemMissile);
            if (type !=0){
                TileState state = (TileState) Objects.requireNonNull(event.getInteractEvent().getClickedBlock()).getState();
                PersistentDataContainer cont = state.getPersistentDataContainer();
                int[] coords = cont.get(new NamespacedKey(AdvancedWarfare.getInstance(), "coords"), PersistentDataType.INTEGER_ARRAY);
                event.getPlayer().sendMessage(Arrays.toString(coords));
                if (coords == null) {
                    event.getPlayer().sendMessage("You need to input coordinates!");
                    return;
                }
                MissileController missile = new MissileController(true, event.getInteractEvent().getClickedBlock().getLocation().add(new Vector(0.5, 1, 0.5)).toVector(), new Vector(coords[0], 0, coords[1]), 1, event.getPlayer().getWorld(), 3, 30);
                missile.FireMissile();
            }
        }
         */
    public void fireMissile(Dispenser disp, MissileController target){
        ItemStack missileitem = VariantsAPI.getOtherFirstMissile(disp.getInventory(), SlimefunItem.getById("ANTIAIRMISSILE"));
        if (SlimefunItem.getByItem(missileitem) == SlimefunItem.getById("ANTIAIRMISSILE")) {
            ItemUtils.consumeItem(missileitem, false);
            MissileController missile = new MissileController(false, disp.getBlock().getLocation().add(new Vector(0.5, 1.35, 0.5)).toVector(), new Vector(0, 0, 0), 8, disp.getWorld(), 3, 0, 0, new Vector(0, 0, 0));
            missile.FireMissileAtMissile(target);
        }
    }

    public boolean correctlyBuilt(Block block) {
        return block.getRelative(BlockFace.DOWN).getType() == Material.OBSIDIAN;
    }
}
