package onl.devin.mc_particles.trajectory;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import static java.lang.Math.*;

public class TrajectoryTypeOrbitPlayer implements TrajectoryType {

    private static final double RADIUS_PL_SAT = 3;

    private Location getCircularLocation(Player player, double blocksTraveled) {
        double x = sin(blocksTraveled / RADIUS_PL_SAT) * RADIUS_PL_SAT;
        double z = cos(blocksTraveled / RADIUS_PL_SAT) * RADIUS_PL_SAT;
        return player.getLocation().clone().add(x, 0, z);
    }

    @Override
    public void update(Trajectory trajectory) {
        trajectory.setLocation(getCircularLocation(trajectory.getPlayer(), trajectory.getBlocksTraveled()));
        Vector dir = trajectory.getLocation()
                .subtract(trajectory.getPlayer().getLocation())
                .toVector()
                .normalize()
                .rotateAroundY(toRadians(90));
        trajectory.setDirection(dir);
    }

}
