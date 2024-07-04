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

    private void spawnParticles(Player player, TrajectoryType trajectoryType, ParticleEffectType particleEffectType) {
        Trajectory trajectory = new Trajectory(player, trajectoryType);
        ParticleEffect particleEffect = new ParticleEffect(particleEffectType, trajectory, 5, Particle.ELECTRIC_SPARK);
        ParticleRunner particleRunner = new ParticleRunner(this, particleEffect);
        particleRunner.start();
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isOp()) {
//                spawnParticles(player, new TrajectoryTypeOrbitPlayer(), new ParticleEffectTypeDoubleHelix());
                spawnParticles(player, new TrajectoryTypeOrbitPlayer(), new ParticleEffectTypeBasic());
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            spawnParticles(player, new TrajectoryTypeOrbitPlayer(), new ParticleEffectTypeDoubleHelix());
        }
    }

    @EventHandler
    public void onSwordClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR) {
            Player player = event.getPlayer();
            if (player.getItemInHand().getType() == Material.NETHERITE_SWORD) {
                spawnParticles(player, new TrajectoryTypeStraight(), new ParticleEffectTypeDoubleHelix());
            }
        }
    }

}
