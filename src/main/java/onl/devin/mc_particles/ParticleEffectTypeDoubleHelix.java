package onl.devin.mc_particles;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static java.lang.Math.toRadians;

public class ParticleEffectTypeDoubleHelix implements ParticleEffectType {

    private static final double RADIUS_SAT_PART = 0.3;

    private Vector relativeParticleLocation;

    @Override
    public void updateRelativeParticleLocation(ParticleEffect particleEffect) {
        Vector dir = particleEffect.getTrajectory().getDirection();
        relativeParticleLocation = dir.clone()
                .crossProduct(new Vector(0, 1, 0))
                .normalize()
                .multiply(RADIUS_SAT_PART)
                .rotateAroundAxis(dir, particleEffect.getMovementElapsed());
    }

    @Override
    public void spawnParticle(ParticleEffect particleEffect) {
        Trajectory trajectory = particleEffect.getTrajectory();
        Player player = trajectory.getPlayer();
        Location l1 = trajectory.getLocation().add(relativeParticleLocation);
        Location l2 = trajectory.getLocation().subtract(relativeParticleLocation);
        player.getWorld().spawnParticle(particleEffect.getParticle(), l1, 5);
        player.getWorld().spawnParticle(particleEffect.getParticle(), l2, 5);
    }

}
