package me.kaiyan.missilewarfare.blocks.launcher;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.items.MissileClass;
import me.kaiyan.missilewarfare.missiles.MissileController;
import me.kaiyan.missilewarfare.util.Translations;
import me.kaiyan.missilewarfare.util.VariantsAPI;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
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

public class SAMLauncher extends AbstractLauncher {
    public final int range = 40000;

    public SAMLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

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
                    cont.set(new NamespacedKey(MissileWarfare.getInstance(), "timesincelastshot"), PersistentDataType.INTEGER, (int) System.currentTimeMillis() + 1000);
                    fireMissileSAM((Dispenser) block.getState(), locked);
                }
            } catch (NullPointerException e) {
                cont.set(new NamespacedKey(MissileWarfare.getInstance(), "timesincelastshot"), PersistentDataType.INTEGER, Integer.MIN_VALUE);
                state.update();
            }
        }
    }

    @Override
    public void onBlockRightClick(PlayerRightClickEvent event) {

    }

    @Override
    public void onPlayerPlace(BlockPlaceEvent event) {
        BlockData data = event.getBlockPlaced().getBlockData();
        ((Directional) data).setFacing(BlockFace.UP);
        event.getBlockPlaced().setBlockData(data);
        if (this.checkSupportBlock(event)) {
            event.getPlayer().sendMessage(Translations.get("messages.launchers.createantiair.success"));
        } else {
            event.getPlayer().sendMessage(Translations.get("messages.launchers.createantiair.failure"));
        }
    }

    @Override
    public Material getSupportBlock() {
        return Material.OBSIDIAN;
    }

    @Override
    public void onBlockDispense(BlockDispenseEvent blockDispenseEvent, Dispenser dispenser, Block block, SlimefunItem slimefunItem) {

    }

    @Override
    public boolean fireMissile(Dispenser disp, MissileClass missile) {
        return false;
    }

    @Override
    public boolean fireMissileSAM(Dispenser disp, MissileController target) {
        ItemStack missileitem = VariantsAPI.getOtherFirstMissile(disp.getInventory(), SlimefunItem.getById("ANTIAIRMISSILE"));
        if (SlimefunItem.getByItem(missileitem) == SlimefunItem.getById("ANTIAIRMISSILE")) {
            ItemUtils.consumeItem(missileitem, false);
            MissileController missile = new MissileController(false, disp.getBlock().getLocation().add(new Vector(0.5, 1.35, 0.5)).toVector(), new Vector(0, 0, 0), 8, disp.getWorld(), 3, 0, 0, new Vector(0, 0, 0));
            missile.FireMissileAtMissile(target);
        }
        return true;
    }
}
