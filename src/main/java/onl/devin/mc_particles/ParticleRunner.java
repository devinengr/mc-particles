package onl.devin.mc_particles;

import org.bukkit.Bukkit;
import org.bukkit.block.data.type.Bed;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

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
