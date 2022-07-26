package me.kaiyan.missilewarfare.listeners;

import me.kaiyan.missilewarfare.MissileWarfare;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Objects;

public class ExplosionEventListener implements Listener {
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event){
        if (Objects.equals(event.getEntity().getCustomName(), "MissileHolder")){
            MissileWarfare.blocksExploded += event.blockList().size();
        }
    }
}
