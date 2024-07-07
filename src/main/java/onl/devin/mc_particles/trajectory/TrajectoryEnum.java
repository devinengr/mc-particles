package onl.devin.mc_particles.trajectory;

public enum TrajectoryEnum {

    ORBIT_PLAYER(new TrajectoryTypeOrbitPlayer()),
    STRAIGHT(new TrajectoryTypeStraight()),
    ;

    private TrajectoryType trajectoryType;

    TrajectoryEnum(TrajectoryType trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    public TrajectoryType getTrajectoryType() {
        return trajectoryType;
    }

}
