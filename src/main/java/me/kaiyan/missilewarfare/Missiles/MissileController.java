package me.kaiyan.missilewarfare.Missiles;


import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.VariantsAPI;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.Random;

public class MissileController {
    public boolean isgroundmissile;
    public Vector pos;
    public Vector target;
    public double speed;
    public World world;
    public double power;
    public boolean launched;
    public int type;
    public Vector dir;
    public BukkitRunnable update;
    public int cruiseAlt;
    public Entity armourStand;
    public int blockcount = 0;
    public boolean deployedCluster = false;

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
        armourStand.setCustomName("MissileHolder");
        //((LivingEntity) armorStand).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 1, true, false));

        if (type != 17){
            deployedCluster = true;
        }

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

        if (type != 17){
            deployedCluster = true;
        }

        MissileWarfare.activemissiles.add(this);
    }

    public void FireMissile(){
        if (isgroundmissile) {
            LaunchSeqFast();
            update = new BukkitRunnable() {
                @Override
                public void run() {
                    Update(this);
                }
            };
            update.runTaskTimer(MissileWarfare.getInstance(), 20, 2);
        }
    }
    public void FireMissileAtMissile(MissileController other){
        LaunchSeqAngled(dir);
        update = new BukkitRunnable() {
            @Override
            public void run() {
                Update(this, other);
            }
        };
        update.runTaskTimer(MissileWarfare.getInstance(), 5, 2);
    }

    public void Update(BukkitRunnable run){
        Vector velocity = getVelocity();
        pos.add(velocity);
        armourStand.teleport(pos.toLocation(world).clone().subtract(new Vector(0, 1.75, 0)));
        VariantsAPI.spawnMissileTrail(world, type, pos, velocity);
        Random rand = new Random();
        if (world.getBlockAt(pos.toLocation(world)).getType() != Material.AIR) {
            if (type == 10) {
                //<editor-fold desc="APT1">
                if (blockcount >= 1 || world.getBlockAt(pos.toLocation(world)).getType() == Material.OBSIDIAN) {
                    explode(run);
                }
                blockcount++;
                //</editor-fold>
            }
            else if (type == 11){
                //<editor-fold desc="APT2">
                if (world.getBlockAt(pos.toLocation(world)).getType() == Material.OBSIDIAN && rand.nextBoolean()){
                    explode(run);
                }
                if (rand.nextDouble() < 0.1){
                    world.getBlockAt(pos.toLocation(world)).setType(Material.AIR);
                    explode(run);
                }
                if (blockcount >= 1) {
                    explode(run);
                }
                blockcount++;
                //</editor-fold>
            }
            else if (type == 12){
                //<editor-fold desc="APT3">
                if (blockcount >= 2) {
                    explode(run);
                }
                if (world.getBlockAt(pos.toLocation(world)).getType() == Material.OBSIDIAN && rand.nextDouble() < 0.75){
                    explode(run);
                }
                if (rand.nextDouble() < 0.25){
                    world.getBlockAt(pos.toLocation(world)).setType(Material.AIR);
                    explode(run);
                }
                blockcount++;
                //</editor-fold>
            }
            else if (type == 13){
                //<editor-fold desc="GASMISSILE">
                new BukkitRunnable() {
                    int loops = 0;
                    @Override
                    public void run() {
                        world.spawnParticle(Particle.REDSTONE, pos.toLocation(world), 5,5, 3, 5, new Particle.DustOptions(Color.fromRGB(0, 255, 0), 20f));
                        for (Player player : world.getPlayers()){
                            if (player.getLocation().toVector().isInSphere(pos, 6)) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 3));
                                player.damage(0.25);
                            }
                        }
                        if (loops >= 400){
                            this.cancel();
                        }
                        loops++;
                    }
                }.runTaskTimer(MissileWarfare.getInstance(), 0, 1);
                explode(run);
                //</editor-fold>
            }
            else {
                explode(run);
            }
        }
    }
    public void Update(BukkitRunnable run, MissileController other){
        this.target = other.pos;
        Vector velocity = getVelocityIgnoreY();
        pos.add(velocity);
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
        world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, (pos.toLocation(world).subtract(velocity.divide(new Vector(2,2,2)))), 0, 0, 0, 0, 0.1, null, true);
        if (target.distanceSquared(pos) < (speed*speed)*1.1){
            if (other == null){
                run.cancel();
            }
            world.createExplosion(pos.toLocation(world), (float) power, false, true, armourStand);
            for (int i = 0; i < 40; i++) {
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, 0.1, null, true);
                world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5, 0.1, null,true);
            }
            if (other.update != null) {
                other.explode(other.update);
                run.cancel();
            }
        }
        if (world.getBlockAt(pos.toLocation(world)).getType() != Material.AIR) {
            world.createExplosion(pos.toLocation(world), (float) power, false, true, armourStand);
        }
    }

    public void explode(BukkitRunnable run){
        for (int i = 0; i < 100; i++) {
            world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() * 2, Math.random() - 0.5, 0.25, null, true);
            world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, Math.random() - 0.5, Math.random() * 2, Math.random() - 0.5, 0.25, null, true);
        }
        world.createExplosion(pos.toLocation(world).add(new Vector(0,1,0)), (float) power, false, true, armourStand);
        Random rand = new Random();
        if (type == 15){
            for (int i = 0; i < 50; i++){
                Vector dir = new Vector((rand.nextFloat()-0.5)*2, (rand.nextFloat()-0.5)*2, (rand.nextFloat()-0.5)*2);
                RayTraceResult result = world.rayTraceBlocks(pos.toLocation(world).add(0,3,0), dir, 10, FluidCollisionMode.ALWAYS, true);
                if (result != null) {
                    Block hitblock = result.getHitBlock();
                    hitblock.getRelative(result.getHitBlockFace()).setType(Material.COBWEB);
                } else {
                    Vector hit = dir.clone();
                    for (int _i = 0; _i < 10; _i++){
                        hit.subtract(new Vector(0,1,0));
                        if (world.getBlockAt(hit.toLocation(world)) != null){
                            Block hitblock = world.getBlockAt(hit.toLocation(world));
                            hitblock.getRelative(BlockFace.UP).setType(Material.COBWEB);
                            break;
                        }
                    }
                }
            }
        }
        else if (type == 18){
            //Lava
            for (int i = 0; i < 2; i++){
                Vector dir = new Vector((rand.nextFloat()-0.5)*2, (rand.nextFloat()-0.5)*2, (rand.nextFloat()-0.5)*2);
                RayTraceResult result = world.rayTraceBlocks(pos.toLocation(world).add(0,3,0), dir, 10, FluidCollisionMode.ALWAYS, true);
                if (result != null) {
                    Block hitblock = result.getHitBlock();
                    hitblock.getRelative(result.getHitBlockFace()).setType(Material.LAVA);
                } else {
                    Vector hit = dir.clone();
                    for (int _i = 0; _i < 20; _i++){
                        hit.subtract(new Vector(0,1,0));
                        if (world.getBlockAt(hit.toLocation(world)) != null){
                            Block hitblock = world.getBlockAt(hit.toLocation(world));
                            hitblock.getRelative(BlockFace.UP).setType(Material.LAVA);
                            break;
                        }
                    }
                }
            }
            //Fire
            for (int i = 0; i < 75; i++){
                Vector dir = new Vector((rand.nextFloat()-0.5)*2, (rand.nextFloat()-0.5)*2, (rand.nextFloat()-0.5)*2);
                RayTraceResult result = world.rayTraceBlocks(pos.toLocation(world).add(0,3,0), dir, 10, FluidCollisionMode.ALWAYS, true);
                if (result != null) {
                    Block hitblock = result.getHitBlock();
                    hitblock.getRelative(result.getHitBlockFace()).setType(Material.FIRE);
                } else {
                    Vector hit = dir.clone();
                    for (int _i = 0; _i < 10; _i++){
                        hit.subtract(new Vector(0,1,0));
                        if (world.getBlockAt(hit.toLocation(world)) != null){
                            Block hitblock = world.getBlockAt(hit.toLocation(world));
                            hitblock.getRelative(BlockFace.UP).setType(Material.FIRE);
                            break;
                        }
                    }
                }
            }
        }
        armourStand.remove();
        MissileWarfare.activemissiles.remove(this);
        run.cancel();
    }

    public Vector getVelocity(){
        Vector velocity = new Vector(0, 0, 0);
        if (pos.getY() < cruiseAlt) {
            velocity.setY(speed * 0.75);
        }
        float xdist = (float) (target.getX() - pos.getX());
        float zdist = (float) (target.getZ() - pos.getZ());

        if (Math.abs(xdist) > speed) {
            if (xdist < 0) {
                velocity.setX(-speed);
            } else {
                velocity.setX(speed);
            }
        }
        if (Math.abs(zdist) > speed) {
            if (zdist < 0) {
                velocity.setZ(-speed);
            } else {
                velocity.setZ(speed);
            }
        }
        if (Math.abs(xdist) < 20 && Math.abs(zdist) < 20) {
            world.loadChunk(pos.toLocation(world).getChunk());
            velocity.setY(-1);
            if (!deployedCluster){
                for (int i = 0; i < Math.round(Math.random()*10); i++) {
                    deployedCluster = true;
                    MissileController missile = new MissileController(true, pos.clone(), target.clone(), 2, world, 2, 40, 17, 120);
                    missile.deployedCluster = true;
                    missile.FireMissile();
                }
            }
        }
        if (pos.getY() < cruiseAlt-40) {
            velocity.setX(0);
            velocity.setZ(0);
        }else if (pos.getY() < cruiseAlt) {
            velocity.setX(velocity.getX()/4);
            velocity.setZ(velocity.getX()/4);
        }else if (pos.getY() < cruiseAlt-20) {
            velocity.setX(velocity.getX()/8);
            velocity.setZ(velocity.getX()/8);
        }
        velocity.setX(velocity.getX());
        velocity.setZ(velocity.getZ());
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

    public void LaunchSeqAngled(Vector dir){
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
