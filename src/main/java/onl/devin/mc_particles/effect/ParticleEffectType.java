package onl.devin.mc_particles.effect;

public interface ParticleEffectType {

    void updateRelativeParticleLocation(ParticleEffect effect);

    void spawnParticle(ParticleEffect effect);

}
