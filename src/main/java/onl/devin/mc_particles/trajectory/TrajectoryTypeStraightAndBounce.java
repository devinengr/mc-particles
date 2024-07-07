package onl.devin.mc_particles.trajectory;

import org.bukkit.util.Vector;

import static java.lang.Math.*;

public class TrajectoryTypeStraightAndBounce extends TrajectoryTypeStraightAndOscillateY {

    @Override
    protected void addOscillationToLocation(Trajectory trajectory) {
        dirPerpendicular = trajectory.getDirection()
                .crossProduct(new Vector(0, 1, 0))
                .rotateAroundAxis(trajectory.getDirection(), toRadians(-90))
                .normalize()
                .multiply(abs(sin(trajectory.getBlocksTraveled())));
        trajectory.setLocation(trajectory.getLocation().add(dirPerpendicular));
    }

}
