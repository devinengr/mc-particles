package onl.devin.mc_particles.effect;

import onl.devin.mc_particles.trajectory.TrajectoryType;

public enum ParticleEffectEnum {

    BASIC(new ParticleEffectTypeBasic()),
    DOUBLE_HELIX(new ParticleEffectTypeDoubleHelix()),
    ;

    private ParticleEffectType particleEffectType;

    ParticleEffectEnum(ParticleEffectType particleEffectType) {
        this.particleEffectType = particleEffectType;
    }

    public ParticleEffectType getNewInstance() {
        ParticleEffectType newInstance;
        try {
            newInstance = particleEffectType.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newInstance;
    }

}
