package me.kaiyan.missilewarfare.blocks.launcher;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockDispenseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.blocks.AbstractBlock;
import me.kaiyan.missilewarfare.blocks.util.BlockPlaceHandlerWrapper;
import me.kaiyan.missilewarfare.items.MissileClass;
import me.kaiyan.missilewarfare.missiles.MissileController;
import me.kaiyan.missilewarfare.util.VariantsAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class AbstractLauncher extends AbstractBlock {
    public AbstractLauncher(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        // TODO find a way to make sure the first two lines (24, 25) are handled by AbstractBlock instead of AbstractLauncher.
        addItemHandler((BlockUseHandler) this::onBlockRightClick);
        addItemHandler(new BlockPlaceHandlerWrapper(false, this::onPlayerPlace)); // is there a way I could just feed onPlayerPlace here ?????
        addItemHandler((BlockDispenseHandler) this::onBlockDispense);
    }

    public abstract void onBlockDispense(
            BlockDispenseEvent blockDispenseEvent, Dispenser dispenser, Block block, SlimefunItem slimefunItem);

    public void fireMissile(Dispenser disp) {
        ItemStack missileitem = VariantsAPI.getFirstMissile(disp.getInventory());
        int type = VariantsAPI.getIntTypeFromSlimefunitem(SlimefunItem.getByItem(missileitem));

        MissileClass missile = VariantsAPI.missileStatsFromType(type);
        boolean fired = fireMissile(disp, missile);
        if (fired) {
            ItemUtils.consumeItem(missileitem, false);
        }
    }

    public abstract boolean fireMissile(Dispenser disp, MissileClass missile);

    // TODO: This is a temporary solution. You need to find a way to
    // use the same launch sequence for both missiles, and then put
    // the load of making their way to the target onto the missile
    // logic.
    public abstract boolean fireMissileSAM(Dispenser dispenser, MissileController missile);
}
