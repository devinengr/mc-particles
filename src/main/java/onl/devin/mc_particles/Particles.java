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


    private void registerParticleCommand() {
        ParticleCommand command = new ParticleCommand(this);
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
