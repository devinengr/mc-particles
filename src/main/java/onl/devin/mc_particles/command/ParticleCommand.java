package onl.devin.mc_particles.command;

import onl.devin.mc_particles.ParticleRunner;
import onl.devin.mc_particles.effect.ParticleEffect;
import onl.devin.mc_particles.effect.ParticleEffectEnum;
import onl.devin.mc_particles.effect.ParticleEffectType;
import onl.devin.mc_particles.trajectory.Trajectory;
import onl.devin.mc_particles.trajectory.TrajectoryEnum;
import onl.devin.mc_particles.trajectory.TrajectoryType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static onl.devin.mc_particles.command.ParticleCommandChecker.checkForInvalidOptions;
import static onl.devin.mc_particles.command.ParticleCommandChecker.firstArgumentIsOnlinePlayer;
import static onl.devin.mc_particles.command.ParticleCommandComponent.actionIsStop;

public class ParticleCommand implements CommandExecutor {

    private JavaPlugin plugin;
    private Map<Player, List<ParticleRunner>> playerParticleMap;

    public ParticleCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerParticleMap = new HashMap<>();
    }

    public String getName() {
        return "particles";
    }

    private void addToMap(Player player, ParticleRunner runner) {
        List<ParticleRunner> list = playerParticleMap.get(player);
        if (list == null) {
            list = new ArrayList<>();
            playerParticleMap.put(player, list);
        }
        list.add(runner);
    }

    private void spawnParticles(Player player,
                                Particle particle,
                                ParticleEffectType particleEffectType,
                                TrajectoryType trajectoryType) {
        Trajectory trajectory = new Trajectory(player, trajectoryType);
        ParticleEffect particleEffect = new ParticleEffect(particleEffectType, trajectory, 5, particle);
        ParticleRunner particleRunner = new ParticleRunner(plugin, particleEffect);
        particleRunner.start();
        addToMap(player, particleRunner);
    }

    private void stopParticles(Player player) {
        List<ParticleRunner> currentParticles = playerParticleMap.get(player);
        if (currentParticles != null) {
            for (ParticleRunner particleRunner : currentParticles) {
                particleRunner.stop();
            }
        }
        playerParticleMap.remove(player);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,
                             @NotNull Command command,
                             @NotNull String s,
                             @NotNull String[] strings) {
        if (!checkForInvalidOptions(commandSender, strings)) {
            return true;
        }
        Player player;
        int offset = 0;
        if (firstArgumentIsOnlinePlayer(strings)) {
            offset += 1;
            player = Bukkit.getPlayer(strings[0]);
        } else {
            if (commandSender instanceof Player) {
                player = (Player) commandSender;
            } else {
                commandSender.sendMessage("If you see this message, you broke the game :(");
                return true;
            }
        }
        if (actionIsStop(strings)) {
            stopParticles(player);
            return true;
        }
        Particle particle = Particle.valueOf(strings[offset + 1].toUpperCase());
        ParticleEffectType particleEffectType = ParticleEffectEnum.valueOf(strings[offset + 2].toUpperCase()).getNewInstance();
        TrajectoryType trajectoryType = TrajectoryEnum.valueOf(strings[offset + 3].toUpperCase()).getNewInstance();
        spawnParticles(player, particle, particleEffectType, trajectoryType);
        return true;
    }

}
