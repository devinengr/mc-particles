package onl.devin.mc_particles.trajectory;

import org.bukkit.util.Vector;

import static java.lang.Math.sin;

public class TrajectoryTypeOrbitPlayerAndBounce implements TrajectoryType {

    private TrajectoryTypeOrbitPlayer trajectoryTypeOrbitPlayer;

    public TrajectoryTypeOrbitPlayerAndBounce() {
        this.trajectoryTypeOrbitPlayer = new TrajectoryTypeOrbitPlayer();
    }

    @Override
    public void update(Trajectory trajectory) {
        trajectoryTypeOrbitPlayer.update(trajectory);
        trajectory.setLocation(trajectory.getLocation()
                .add(new Vector(0, Math.abs(sin(trajectory.getBlocksTraveled())), 0)));
    }

}
