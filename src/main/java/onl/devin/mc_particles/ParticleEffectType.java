package onl.devin.mc_particles;

import org.bukkit.util.Vector;

public interface ParticleEffectType {

    void updateRelativeParticleLocation(ParticleEffect effect);

    void spawnParticle(ParticleEffect effect);

}
