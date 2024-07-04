package onl.devin.mc_particles;

import org.bukkit.Particle;

public class ParticleEffect {

    private ParticleEffectType particleEffectType;
    private Trajectory trajectory;
    private double movementElapsed = 0;
    private double relativeMovementPerTick;
    private Particle particle;
    private int particleCount;

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory) {
        this.particleEffectType = particleEffectType;
        this.trajectory = trajectory;
        this.relativeMovementPerTick = 0.3;
        this.particle = Particle.HAPPY_VILLAGER;
        this.particleCount = 5;
    }

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory,
                          int particleCount) {
        this(particleEffectType, trajectory);
        this.particleCount = particleCount;
    }

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory,
                          int particleCount,
                          Particle particle) {
        this(particleEffectType, trajectory, particleCount);
        this.particle = particle;
    }

    public ParticleEffect(ParticleEffectType particleEffectType,
                          Trajectory trajectory,
                          int particleCount,
                          Particle particle,
                          double relativeMovementPerTick) {
        this(particleEffectType, trajectory, particleCount, particle);
        this.relativeMovementPerTick = relativeMovementPerTick;
    }

    public int getParticleCount() {
        return particleCount;
    }

    public void setParticleCount(int particleCount) {
        this.particleCount = particleCount;
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

    public void setMovementElapsed(double movementElapsed) {
        this.movementElapsed = movementElapsed;
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
