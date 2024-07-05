package onl.devin.mc_particles.trajectory;

public class TrajectoryTypeStraight implements TrajectoryType {

    @Override
    public void update(Trajectory trajectory) {
        trajectory.setDirection(trajectory.getDirPlayerInitial());
        trajectory.setLocation(trajectory.getLocation()
                .add(trajectory.getDirection().multiply(trajectory.getBlocksPerTick())));
    }

}
