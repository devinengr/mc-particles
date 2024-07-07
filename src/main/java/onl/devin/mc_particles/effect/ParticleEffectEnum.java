package onl.devin.mc_particles.effect;

public enum ParticleEffectEnum {

    BASIC,
    DOUBLE_HELIX,
    ;

    public ParticleEffectType getParticleEffectType() {
        switch (this) {
            case BASIC: return new ParticleEffectTypeBasic();
            case DOUBLE_HELIX: return new ParticleEffectTypeDoubleHelix();
            default: throw new RuntimeException("Handle new particle effect types here");
        }
    }

}
