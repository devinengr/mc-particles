package onl.devin.mc_particles;

import org.bukkit.Particle;

public class ParticleEffect {

    private ParticleEffectType particleEffectType;
    private Trajectory trajectory;
    private double movementElapsed = 0;
    private double relativeMovementPerTick;
    private Particle particle;

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory) {
        this.particleEffectType = particleEffectType;
        this.trajectory = trajectory;
        this.relativeMovementPerTick = 0.3;
        this.particle = Particle.HAPPY_VILLAGER;
    }

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory,
                          double relativeMovementPerTick) {
        this(particleEffectType, trajectory);
        this.relativeMovementPerTick = relativeMovementPerTick;
    }

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory,
                          Particle particle) {
        this(particleEffectType, trajectory);
        this.particle = particle;
    }

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory,
                          double relativeMovementPerTick,
                          Particle particle) {
        this(particleEffectType, trajectory, relativeMovementPerTick);
        this.particle = particle;
    }

    public ParticleEffectType getParticleEffectType() {
        return particleEffectType;
    }

    public void setParticleEffectType(ParticleEffectType particleEffectType) {
        this.particleEffectType = particleEffectType;
    }

    public Trajectory getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public double getRelativeMovementPerTick() {
        return relativeMovementPerTick;
    }

    public void setRelativeMovementPerTick(double relativeMovementPerTick) {
        this.relativeMovementPerTick = relativeMovementPerTick;
    }

    public Particle getParticle() {
        return particle;
    }

    public void setParticle(Particle particle) {
        this.particle = particle;
    }

    public double getMovementElapsed() {
        return movementElapsed;
    }

    public void update() {
        movementElapsed += relativeMovementPerTick;
        trajectory.update();
        particleEffectType.updateRelativeParticleLocation(this);
        particleEffectType.spawnParticle(this);
    }

}
