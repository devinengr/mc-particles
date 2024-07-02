package onl.devin.mc_particles;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import static java.lang.Math.*;

public class Particles extends JavaPlugin implements Listener {

    private void loopParticle(Player player, String trajectory, Location locPlayer) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            private final double RADIUS_PL_SAT = 5;
            private final double RADIUS_SAT_PART = 0.3;
            private double speedOrbit = 0;
            private double speedTravel = 0;

            @Override
            public void run() {
                speedOrbit += 0.3;
                speedTravel += 0.1;

                if (trajectory.equalsIgnoreCase("orbit")) {
                    // determine circle around player
                    // formula: in 1 tick, travel an exact distance along the
                    // circumference defined by speedTravel. If speedTravel is
                    // 0.1, travel 0.1 blocks along the circumference.
                    double x = sin(speedTravel / RADIUS_PL_SAT) * RADIUS_PL_SAT;
                    double z = cos(speedTravel / RADIUS_PL_SAT) * RADIUS_PL_SAT;
                    Location l1 = player.getLocation().clone().add(x, 0, z);

                    // determine direction from player to current circular location
                    // and point the direction sideways
                    Vector dir = l1.clone().subtract(player.getLocation()).toVector();
                    dir.rotateAroundY(toRadians(90));

                    // determine particle location
                    Vector p = new Vector(dir.getX(), 0, dir.getZ())
                            .normalize()
                            .multiply(RADIUS_SAT_PART);
                    p.rotateAroundY(toRadians(90));
                    p.rotateAroundAxis(dir, speedOrbit);
                    // extend dir further in front of player
//                    dir.multiply(speedTravel);
                    // spawn particle
                    Location l2 = l1.clone().add(p);//.add(dir);
                    Location l3 = l1.clone().subtract(p);//.add(dir);
                    player.getWorld().spawnParticle(Particle.DRIPPING_WATER, l2, 5);
                    player.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l3, 5);
                }

                if (trajectory.equalsIgnoreCase("straight")) {
                    Vector dir = locPlayer.getDirection().clone();
                    // determine particle location
                    Vector p = new Vector(dir.getX(), 0, dir.getZ())
                            .normalize()
                            .multiply(RADIUS_SAT_PART);
                    p.rotateAroundY(toRadians(90));
                    p.rotateAroundAxis(dir, speedOrbit);
                    // extend dir further in front of player
                    dir.multiply(speedTravel);
                    // spawn particle
                    Location l1 = locPlayer.clone().add(p).add(dir);
                    Location l2 = locPlayer.clone().subtract(p).add(dir);
                    player.getWorld().spawnParticle(Particle.DRIPPING_WATER, l1, 5);
                    player.getWorld().spawnParticle(Particle.DRIPPING_LAVA, l2, 5);
                }
            }
        }, 1, 1);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
                loopParticle(player, "orbit", player.getLocation());
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            loopParticle(event.getPlayer(), "orbit", player.getLocation());
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (player.getInventory().getItemInMainHand().getType() == Material.NETHERITE_SWORD) {
                player.getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.LIGHTNING_BOLT);
                event.setDamage(500);
            }
        }
    }

    @EventHandler
    public void onSwordClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            Player player = event.getPlayer();
            if (player.getItemInHand().getType() == Material.NETHERITE_SWORD) {
                loopParticle(player, "straight", player.getLocation().clone());
            }
        }
    }

}
