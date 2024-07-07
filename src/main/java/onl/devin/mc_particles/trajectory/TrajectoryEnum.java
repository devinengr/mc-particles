package onl.devin.mc_particles.trajectory;

import java.lang.reflect.InvocationTargetException;

public enum TrajectoryEnum {

    ORBIT_PLAYER(new TrajectoryTypeOrbitPlayer()),
    ORBIT_PLAYER_AND_OSCILLATE_Y(new TrajectoryTypeOrbitPlayerAndOscillateY()),
    ORBIT_PLAYER_AND_BOUNCE(new TrajectoryTypeOrbitPlayerAndBounce()),
    STRAIGHT(new TrajectoryTypeStraight()),
    STRAIGHT_AND_OSCILLATE_Y(new TrajectoryTypeStraightAndOscillateY()),
    STRAIGHT_AND_BOUNCE(new TrajectoryTypeStraightAndBounce()),
    ;

    private TrajectoryType trajectoryType;

    TrajectoryEnum(TrajectoryType trajectoryType) {
        this.trajectoryType = trajectoryType;
    }

    public TrajectoryType getNewInstance() {
        TrajectoryType newInstance;
        try {
            newInstance = trajectoryType.getClass().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newInstance;
    }

}
