package me.kaiyan.advancedwarfare.Missiles;


import me.kaiyan.advancedwarfare.MissileWarfare;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MissileController {
    boolean isgroundmissile;
    public Vector pos;
    public Vector target;
    public float speed;
    public World world;
    public double power;
    public boolean launched;
    public int type;

    public MissileController(boolean isgroundmissile, Vector startpos, Vector target, float speed, World world, double power, float accuracy, int type){
        this.isgroundmissile = isgroundmissile;
        pos = startpos;
        this.speed = speed;
        this.world = world;
        this.power = power;
        launched = false;
        this.type = type;

        target = target.add(new Vector((Math.random()-0.5)*accuracy, 0, (Math.random()-0.5)*accuracy));

        this.target = target;
    }

    public void FireMissile(){
        LaunchSeqFast();
        new BukkitRunnable(){
          @Override
          public void run(){
              Update(this);
          }
        }.runTaskTimer(MissileWarfare.getInstance(), 25, 2);
    }

    public void Update(BukkitRunnable run){
        if (isgroundmissile) {
            Vector velocity = new Vector(0, 0, 0);
            if (pos.getY() < 140) {
                velocity.setY(speed * 0.5);
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
                velocity.setY(-speed);
            }
            if (pos.getY() < 80) {
                velocity.setX(0);
                velocity.setZ(0);
            } else if (pos.getY() < 120) {
                velocity.setX(velocity.getX()/4);
                velocity.setZ(velocity.getX()/4);
            }else if (pos.getY() < 100) {
                velocity.setX(velocity.getX()/8);
                velocity.setZ(velocity.getX()/8);
            }
            velocity.setX(Math.round(velocity.getX()));
            velocity.setZ(Math.round(velocity.getZ()));
            pos.add(velocity);
            if (type == 1 || type == 2 || type == 3) {
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
                if (type == 1){
                    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, 0, 0, 0, 0.1, null, true);
                } else if (type == 2){
                    world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.25, null, true);
                    world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.25, null, true);
                } else if (type == 3){
                    world.spawnParticle(Particle.END_ROD, pos.toLocation(world), 0, -velocity.getX()+((Math.random()-0.5)*0.25), -velocity.getY()+((Math.random()-0.5)*0.25), -velocity.getZ()+((Math.random()-0.5)*0.25), 0.3, null, true);
                }
            }
            if (world.getBlockAt(pos.toLocation(world)).getType() != Material.AIR) {
                for (int i = 0; i < 150; i++) {
                    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, Math.random()-0.5, Math.random()*2, Math.random()-0.5, 0.25, null, true);
                    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, Math.random()-0.5, Math.random()*2, Math.random()-0.5, 0.25, null, true);
                    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, pos.toLocation(world), 0, Math.random()-0.5, Math.random()*0.5, Math.random()-0.5, 0.15, null, true);
                    world.spawnParticle(Particle.FLAME, pos.toLocation(world), 0, (Math.random()*2)-0.5, Math.random()*1.5, (Math.random()*2)-0.5, 0.25, null, true);
                }
                world.createExplosion(pos.toLocation(world), (float) power);
                run.cancel();
            }
        }
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
        }.runTaskTimer(MissileWarfare.getInstance(), 13, 1);

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
