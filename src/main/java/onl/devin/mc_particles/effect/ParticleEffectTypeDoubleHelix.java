package onl.devin.mc_particles.effect;

import onl.devin.mc_particles.trajectory.Trajectory;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class ParticleEffectTypeDoubleHelix implements ParticleEffectType {

    private final double RADIUS_SAT_PART = 0.3;

    private Vector relativeParticleLocation;

    @Override
    public void updateRelativeParticleLocation(ParticleEffect effect) {
        Vector dir = effect.getTrajectory().getDirection();
        relativeParticleLocation = dir.clone()
                .crossProduct(new Vector(0, 1, 0))
                .normalize()
                .multiply(RADIUS_SAT_PART)
                .rotateAroundAxis(dir, effect.getMovementElapsed());
    }

    @Override
    public void spawnParticle(ParticleEffect effect) {
        Trajectory trajectory = effect.getTrajectory();
        Player player = trajectory.getPlayer();
        Location l1 = trajectory.getLocation().add(relativeParticleLocation);
        Location l2 = trajectory.getLocation().subtract(relativeParticleLocation);
        player.getWorld().spawnParticle(effect.getParticle(), l1, effect.getParticleCount());
        player.getWorld().spawnParticle(effect.getParticle(), l2, effect.getParticleCount());
    }

}
