package onl.devin.mc_particles.effect;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleEffectTypeBasic implements ParticleEffectType {

    private Vector relativeParticleLocation;

    @Override
    public void updateRelativeParticleLocation(ParticleEffect effect) {
        relativeParticleLocation = effect.getTrajectory().getLocation().toVector();
    }

    @Override
    public void spawnParticle(ParticleEffect effect) {
        Player player = effect.getTrajectory().getPlayer();
        Location loc = relativeParticleLocation.toLocation(player.getWorld());
        player.getWorld().spawnParticle(effect.getParticle(), loc, effect.getParticleCount());
    }

}
