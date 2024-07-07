package onl.devin.mc_particles.trajectory;

import org.bukkit.util.Vector;

import static java.lang.Math.*;

public class TrajectoryTypeOrbitPlayerAndOscillateY implements TrajectoryType {

    private TrajectoryTypeOrbitPlayer trajectoryTypeOrbitPlayer;

    public TrajectoryTypeOrbitPlayerAndOscillateY() {
        this.trajectoryTypeOrbitPlayer = new TrajectoryTypeOrbitPlayer();
    }

    @Override
    public void update(Trajectory trajectory) {
        trajectoryTypeOrbitPlayer.update(trajectory);
        trajectory.setLocation(trajectory.getLocation()
                .add(new Vector(0, sin(trajectory.getBlocksTraveled()) + 1, 0)));
    }

}
