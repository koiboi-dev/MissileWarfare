package me.kaiyan.missilewarfare.blocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public interface Block {

    void preRegister();

    // no clue what this does, I just refactor - ck
    public boolean isSynchronized();

    // all events that will be required to keep a block functioning. - ck
    void tick(org.bukkit.block.Block block, SlimefunItem slimefunItem, Config config);
    void onBlockRightClick(PlayerRightClickEvent event);

    void onPlayerPlace(BlockPlaceEvent event);

    // support blocks are the blocks below the dispensers in the
    // GtG Launchers, Anti-Elytra Launchers, and Anti-Missile Launchers.
    public Material getSupportBlock();

    boolean checkSupportBlock(BlockPlaceEvent event);
}
