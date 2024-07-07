package onl.devin.mc_particles.trajectory;

import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public class TrajectoryTypeStraightAndOscillateY implements TrajectoryType {

    protected Vector dirPerpendicular;

    protected void addOscillationToLocation(Trajectory trajectory) {
        dirPerpendicular = trajectory.getDirection()
                .crossProduct(new Vector(0, 1, 0))
                .rotateAroundAxis(trajectory.getDirection(), toRadians(-90))
                .normalize()
                .multiply(sin(trajectory.getBlocksTraveled()) + 1);
        trajectory.setLocation(trajectory.getLocation().add(dirPerpendicular));
    }

    private void removeOscillationFromLocation(Trajectory trajectory) {
        if (dirPerpendicular != null) {
            trajectory.setLocation(trajectory.getLocation().subtract(dirPerpendicular));
        }
    }

    @Override
    public void update(Trajectory trajectory) {
        trajectory.setDirection(trajectory.getDirPlayerInitial());
        removeOscillationFromLocation(trajectory);
        trajectory.setLocation(trajectory.getLocation()
                .add(trajectory.getDirection().multiply(trajectory.getBlocksPerTick())));
        addOscillationToLocation(trajectory);
    }

}
