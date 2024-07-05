package onl.devin.mc_particles;

import onl.devin.mc_particles.effect.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleRunner {

    private JavaPlugin plugin;
    private ParticleEffect particleEffect;
    private int taskID;

    public ParticleRunner(JavaPlugin plugin, ParticleEffect particleEffect) {
        this.plugin = plugin;
        this.particleEffect = particleEffect;
    }

    public void start() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> particleEffect.update(), 1, 1);
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
