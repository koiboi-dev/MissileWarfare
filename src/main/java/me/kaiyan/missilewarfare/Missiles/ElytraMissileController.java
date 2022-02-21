package me.kaiyan.missilewarfare.Missiles;

import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.PlayerID;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ElytraMissileController {
    public float speed;
    public float power;
    public Vector pos;
    public Vector startpos;
    public World world;
    public Player player;
    public int loosetime;
    public int startdist;

    public ElytraMissileController(float speed, float power, Vector startpos, World world, Player player){
        this.speed = speed;
        pos = startpos;
        this.world = world;
        this.player = player;
        this.power = power;
        startdist = (int) startpos.distanceSquared(player.getLocation().toVector());
        this.startpos = startpos;
        this.loosetime = (int) (System.currentTimeMillis()+5000);
    }

    public void LaunchSeq(){
        new BukkitRunnable(){
            int loops = 0;
            @Override
            public void run() {
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random(), Math.random() - 0.5, 0.1);
                world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, (Math.random() - 0.5)/0.9, Math.random()+0.25, (Math.random() - 0.5)/0.9, 0.2);
                if (loops > 15) {
                    this.cancel();
                }
                loops++;
            }
        }.runTaskTimer(MissileWarfare.getInstance(), 5, 1);

        new BukkitRunnable(){
            int loops = 0;
            @Override
            public void run() {
                world.spawnParticle(Particle.CLOUD, pos.toLocation(world), 0, Math.random() - 0.5, Math.random()-1, Math.random() - 0.5, 0.1);
                if (loops < 20) {
                    this.cancel();
                }
                loops++;
            }
        }.runTaskTimer(MissileWarfare.getInstance(), 0, 1);
    }

    public Vector getVelocityIgnoreY(){
        Vector velocity = new Vector(0, 0, 0);

        float xdist = (float) (player.getLocation().getX() - pos.getX());
        float zdist = (float) (player.getLocation().getZ() - pos.getZ());
        float ydist = (float) (player.getLocation().getY() - pos.getY());

        if (xdist != 0) {
            if (xdist < 0) {
                velocity.setX(-speed);
            } else {
                velocity.setX(speed);
            }
        }
        if (ydist != 0){
            if (ydist < 0) {
                velocity.setY(-speed);
            } else {
                velocity.setY(speed);
            }
        }
        if (zdist != 0) {
            if (zdist < 0) {
                velocity.setZ(-speed);
            } else {
                velocity.setZ(speed);
            }
        }
        velocity.setX(Math.round(velocity.getX()));
        velocity.setZ(Math.round(velocity.getZ()));
        return velocity;
    }

    public void Update(BukkitRunnable run, Player other){
        Vector velocity = getVelocityIgnoreY();
        pos.add(velocity);
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, (pos.toLocation(world).subtract(velocity.divide(new Vector(2,2,2)))), 0, 0, 0, 0, 0.1, null, true);
        if (other.getLocation().distanceSquared(pos.toLocation(world)) < (speed*speed)*1.1){
            world.createExplosion(pos.toLocation(world), power, false, false, player);
            for (int i = 0; i < 40; i++) {
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, 0.1, null, true);
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, 0.1, null,true);
            }
            if (player.isGliding()){
                ItemStack elytra = player.getEquipment().getChestplate();
                Damageable meta = (Damageable)elytra.getItemMeta();
                meta.setDamage(elytra.getType().getMaxDurability()-5);
                elytra.setItemMeta(meta);
                player.getEquipment().setChestplate(elytra);
                run.cancel();
                PlayerID.targets.remove(player);
            }
            else {
                run.cancel();
                PlayerID.targets.remove(player);
            }
        }
        if (world.getBlockAt(pos.toLocation(world)).getType() != Material.AIR) {
            world.createExplosion(pos.toLocation(world), power, false, false, player);
            run.cancel();
            PlayerID.targets.remove(player);
        }
        if (!player.isGliding()){
            ComponentBuilder builder = new ComponentBuilder("");
            if (loosetime <= System.currentTimeMillis()){
                builder.append("!!! LOST MISSILE !!!").color(ChatColor.DARK_GREEN);
            } else {
                builder.append("Losing Missile...").color(ChatColor.GREEN);
            }
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, builder.create());
        } else {
            ComponentBuilder builder = new ComponentBuilder("");

            builder.append("!! MISSILE INCOMING !!").bold(true).color(ChatColor.DARK_RED);

            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, builder.create());
        }
    }

    public void FireMissile(Player other){
        LaunchSeq();
        new BukkitRunnable() {
            @Override
            public void run() {
                Update(this, other);
            }
        }.runTaskTimer(MissileWarfare.getInstance(), 5, 2);
    }
}
