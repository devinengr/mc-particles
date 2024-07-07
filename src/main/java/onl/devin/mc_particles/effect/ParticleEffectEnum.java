package onl.devin.mc_particles.effect;

public enum ParticleEffectEnum {

    BASIC(new ParticleEffectTypeBasic()),
    DOUBLE_HELIX(new ParticleEffectTypeDoubleHelix()),
    ;

    private ParticleEffectType particleEffectType;

    ParticleEffectEnum(ParticleEffectType particleEffectType) {
        this.particleEffectType = particleEffectType;
    }

    public ParticleEffectType getParticleEffectType() {
        return particleEffectType;
    }

}
