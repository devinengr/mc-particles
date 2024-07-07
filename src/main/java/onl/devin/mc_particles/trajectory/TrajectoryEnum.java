package onl.devin.mc_particles.trajectory;

public enum TrajectoryEnum {

    ORBIT_PLAYER,
    STRAIGHT,
    ;

    public TrajectoryType getTrajectoryType() {
        switch (this) {
            case ORBIT_PLAYER: return new TrajectoryTypeOrbitPlayer();
            case STRAIGHT: return new TrajectoryTypeStraight();
            default: throw new RuntimeException("Handle new trajectory types here");
        }
    }

}
