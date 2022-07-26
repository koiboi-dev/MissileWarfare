package me.kaiyan.missilewarfare.blocks.util;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/*
This was created to work around the fact that the normal
BlockPlaceHandler requires you to write a function within its
brackets as it is an abstract class and onPlayerPlace is an abstract function.
I wanted the individual block classes to be able to write their own onPlayerPlace
functions in a neat and nice way.

So, this is a wrapper for BlockPlaceHandler that will allow us to
simply give the BlockPlaceHandler a consumer (aka our method)
in a neat way!

Thanks to MoseMister for the tip on this workaround. -ck
 */

public class BlockPlaceHandlerWrapper extends BlockPlaceHandler {
    Consumer<BlockPlaceEvent> eventConsumer;

    public BlockPlaceHandlerWrapper(boolean allowBlockPlacers, Consumer<BlockPlaceEvent> eventConsumer) {
        super(allowBlockPlacers);
        this.eventConsumer = eventConsumer;
    }

    @Override
    public void onPlayerPlace(@Nonnull BlockPlaceEvent blockPlaceEvent) {
        this.eventConsumer.accept(blockPlaceEvent);
    }
}
