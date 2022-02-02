package me.kaiyan.missilewarfare.Missiles;


import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.VariantsAPI;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Random;

public class MissileController {
    public boolean isgroundmissile;
    public Vector pos;
    public Vector target;
    public float speed;
    public World world;
    public double power;
    public boolean launched;
    public int type;
    public Vector dir;
    public BukkitTask update;
    public int cruiseAlt;
    public Entity armourStand;

    public MissileController(boolean isgroundmissile, Vector startpos, Vector target, float speed, World world, double power, float accuracy, int type, int cruiseAlt){
        this.isgroundmissile = isgroundmissile;
        pos = startpos;
        this.speed = speed;
        this.world = world;
        this.power = power;
        launched = false;
        this.type = type;
        this.cruiseAlt = cruiseAlt;

        Random rand = new Random(System.nanoTime());
        target = target.add(new Vector((rand.nextDouble()-0.5)*accuracy, 0, (rand.nextDouble()-0.5)*accuracy));

        this.target = target;
        dir = new Vector(0,0,0);

        armourStand = world.spawnEntity(pos.toLocation(world), EntityType.ARMOR_STAND);
        armourStand.setPersistent(true);
        ((LivingEntity) armourStand).getEquipment().setHelmet(new ItemStack(Material.GREEN_CONCRETE));
        armourStand.setGravity(false);
        ((LivingEntity) armourStand).setInvisible(true);
        //((LivingEntity) armorStand).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, true, false));

        MissileWarfare.activemissiles.add(this);
    }
    public MissileController(boolean isgroundmissile, Vector startpos, Vector target, float speed, World world, double power, float accuracy, int type, Vector dir){
        this.isgroundmissile = isgroundmissile;
        pos = startpos;
        this.speed = speed;
        this.world = world;
        this.power = power;
        launched = false;
        this.type = type;

        target = target.add(new Vector((Math.random()-0.5)*accuracy, 0, (Math.random()-0.5)*accuracy));

        this.target = target;
        this.dir = dir;
    }

    public void FireMissile(){
        if (isgroundmissile) {
            LaunchSeqFast();
            update = new BukkitRunnable() {
                @Override
                public void run() {
                    Update(this);
                }
            }.runTaskTimer(MissileWarfare.getInstance(), 20, 2);
        }
    }
    public void FireMissileAtMissile(MissileController other){
        LaunchSeqAngled(dir);
        update = new BukkitRunnable() {
            @Override
            public void run() {
                Update(this, other);
            }
        }.runTaskTimer(MissileWarfare.getInstance(), 5, 2);
    }

    public void Update(BukkitRunnable run){
        Vector velocity = getVelocity();
        pos.add(velocity);
        armourStand.teleport(pos.toLocation(world).clone().subtract(new Vector(0, 1.75, 0)));
        VariantsAPI.spawnMissileTrail(world, type, pos, velocity);
        if (world.getBlockAt(pos.toLocation(world)).getType() != Material.AIR) {
            for (int i = 0; i < 150; i++) {
                world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, Math.random()-0.5, Math.random()*2, Math.random()-0.5, 0.25, null, true);
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random()-0.5, Math.random()*2, Math.random()-0.5, 0.25, null, true);
                world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, Math.random()-0.5, Math.random()*0.5, Math.random()-0.5, 0.15, null, true);
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, (Math.random()*2)-0.5, Math.random()*1.5, (Math.random()*2)-0.5, 0.25, null, true);
            }
            world.createExplosion(pos.toLocation(world), (float) power);
            armourStand.remove();
            run.cancel();
            MissileWarfare.activemissiles.remove(this);
        }
    }
    public void Update(BukkitRunnable run, MissileController other){
        this.target = other.pos;
        Vector velocity = getVelocityIgnoreY();
        pos.add(velocity);
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, (pos.toLocation(world).subtract(velocity.divide(new Vector(2,2,2)))), 0, 0, 0, 0, 0.1, null, true);
        if (target.distanceSquared(pos) < (speed*speed)*1.1){
            world.createExplosion(pos.toLocation(world), (float) power);
            for (int i = 0; i < 40; i++) {
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, 0.1, null, true);
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, 0.1, null,true);
            }
            if (other.update != null) {
                other.update.cancel();
            }
            other.armourStand.remove();
            MissileWarfare.activemissiles.remove(other);
            run.cancel();
        }
        if (world.getBlockAt(pos.toLocation(world)).getType() != Material.AIR) {
            world.createExplosion(pos.toLocation(world), (float) power);
        }
    }

    public Vector getVelocity(){
        Vector velocity = new Vector(0, 0, 0);
        if (pos.getY() < cruiseAlt) {
            velocity.setY(speed * 0.75);
        }
        float xdist = (float) (target.getX() - pos.getX());
        float zdist = (float) (target.getZ() - pos.getZ());

        if (xdist != 0) {
            if (xdist < 0) {
                velocity.setX(-speed);
            } else {
                velocity.setX(speed);
            }
        }
        if (zdist != 0) {
            if (zdist < 0) {
                velocity.setZ(-speed);
            } else {
                velocity.setZ(speed);
            }
        }
        if (xdist < 15 && zdist < 15) {
            world.loadChunk(pos.toLocation(world).getChunk());
            velocity.setY(-speed);
        }
        if (pos.getY() < cruiseAlt-40) {
            velocity.setX(0);
            velocity.setZ(0);
        } else if (pos.getY() < cruiseAlt) {
            velocity.setX(velocity.getX()/4);
            velocity.setZ(velocity.getX()/4);
        }else if (pos.getY() < cruiseAlt-20) {
            velocity.setX(velocity.getX()/8);
            velocity.setZ(velocity.getX()/8);
        }
        velocity.setX(Math.round(velocity.getX()));
        velocity.setZ(Math.round(velocity.getZ()));
        return velocity;
    }
    public Vector getVelocityIgnoreY(){
        Vector velocity = new Vector(0, 0, 0);

        float xdist = (float) (target.getX() - pos.getX());
        float zdist = (float) (target.getZ() - pos.getZ());
        float ydist = (float) (target.getY() - pos.getY());

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


    public void LaunchSeqFast(){
        new BukkitRunnable(){
            int loops = 0;
            @Override
            public void run() {
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random(), Math.random() - 0.5, 0.1);
                world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, (Math.random() - 0.5)/0.9, Math.random()+0.25, (Math.random() - 0.5)/0.9, 0.2);
                launched = true;
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

    public void     LaunchSeqAngled(Vector dir){
        //First Launch
        new BukkitRunnable(){
            int loops = 0;
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, -dir.getX()+(Math.random() - 0.5), -dir.getY()+(Math.random() - 0.5), -dir.getZ()+(Math.random() - 0.5), 0.1);
                    world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, -dir.getX()+(Math.random() - 0.5), -dir.getY()+(Math.random() - 0.5), -dir.getZ()+(Math.random() - 0.5), 0.1);
                    world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -dir.getX()+(Math.random() - 0.5), -dir.getY()+(Math.random() - 0.5), -dir.getZ()+(Math.random() - 0.5), 0.1);
                    world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, dir.getX()+(Math.random() - 0.5), dir.getY()+(Math.random() - 0.5), dir.getZ()+(Math.random() - 0.5), 0.1);
                }
                if (loops > 5) {
                    this.cancel();
                }
                loops++;
            }
        }.runTaskTimer(MissileWarfare.getInstance(), 0, 1);
    }
    /*
    new BukkitRunnable(){
            int loops = 0;
            @Override
            public void run() {
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random(), Math.random() - 0.5, 0.1);
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, (Math.random() - 0.5)/0.9, Math.random()+0.25, (Math.random() - 0.5)/0.9, 0.2);
                launched = true;
                if (loops > 15) {
                    this.cancel();
                }
                loops++;
            }
        }.runTaskTimer(AdvancedWarfare.getInstance(), 13, 1);

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
        }.runTaskTimer(AdvancedWarfare.getInstance(), 0, 1);
     */
}
