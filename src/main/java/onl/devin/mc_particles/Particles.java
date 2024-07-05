package onl.devin.mc_particles;

import onl.devin.mc_particles.command.ParticleCommand;
import onl.devin.mc_particles.command.ParticleCommandCompleter;
import onl.devin.mc_particles.effect.ParticleEffect;
import onl.devin.mc_particles.effect.ParticleEffectType;
import onl.devin.mc_particles.trajectory.Trajectory;
import onl.devin.mc_particles.trajectory.TrajectoryType;
import org.bukkit.*;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Particles extends JavaPlugin implements Listener {

    private void spawnParticles(Player player, TrajectoryType trajectoryType, ParticleEffectType particleEffectType) {
        Trajectory trajectory = new Trajectory(player, trajectoryType);
        ParticleEffect particleEffect = new ParticleEffect(particleEffectType, trajectory, 5, Particle.ELECTRIC_SPARK);
        ParticleRunner particleRunner = new ParticleRunner(this, particleEffect);
        particleRunner.start();
    }

    private void registerParticleCommand() {
        ParticleCommand command = new ParticleCommand();
        ParticleCommandCompleter completer = new ParticleCommandCompleter(command);
        this.getCommand(command.getName()).setExecutor(command);
        this.getCommand(command.getName()).setTabCompleter(completer);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        registerParticleCommand();
    }

}
