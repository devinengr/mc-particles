package onl.devin.mc_particles;

import onl.devin.mc_particles.effect.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticleRunner {

    private static Map<Player, List<ParticleRunner>> particleMap = new HashMap<>();

    private Player player;
    private JavaPlugin plugin;
    private ParticleEffect particleEffect;
    private int taskID;

    public ParticleRunner(JavaPlugin plugin, ParticleEffect particleEffect, Player player) {
        this.plugin = plugin;
        this.particleEffect = particleEffect;
        this.player = player;
    }

    public static void stopAllParticles(Player player) {
        List<ParticleRunner> runners = particleMap.getOrDefault(player, new ArrayList<>());
        // remove items from a copy to prevent modification during iteration
        List<ParticleRunner> copy = new ArrayList<>(runners);
        for (ParticleRunner runner : copy) {
            runner.stop();
        }
    }

    private void addParticleToMap() {
        List<ParticleRunner> runners = particleMap.getOrDefault(player, new ArrayList<>());
        runners.add(this);
        particleMap.put(player, runners);
    }

    private void removeParticleFromMap() {
        List<ParticleRunner> runners = particleMap.get(player);
        runners.remove(this);
        if (runners.isEmpty()) {
            particleMap.remove(player);
        }
    }

    public void start() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> particleEffect.update(), 1, 1);
        addParticleToMap();
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskID);
        removeParticleFromMap();
    }

}
