package me.kaiyan.missilewarfare.Events;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.util.Vector;

public class MinecartListener implements Listener {
    @EventHandler
    public void VehicleUpdateEvent(VehicleUpdateEvent event){
        Vehicle cart = event.getVehicle();
        if (cart.getType() == EntityType.MINECART_CHEST) {
            World world = event.getVehicle().getWorld();

            world.loadChunk(cart.getLocation().getChunk());
            world.loadChunk(world.getChunkAt(cart.getLocation().add(new Vector(16, 0, 0))));
            world.loadChunk(world.getChunkAt(cart.getLocation().add(new Vector(-16, 0, 0))));
            world.loadChunk(world.getChunkAt(cart.getLocation().add(new Vector(0, 0, 16))));
            world.loadChunk(world.getChunkAt(cart.getLocation().add(new Vector(0, 0, 16))));
        }
    }
}
