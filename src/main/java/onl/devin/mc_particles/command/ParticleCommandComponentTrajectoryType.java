package onl.devin.mc_particles.command;

import onl.devin.mc_particles.trajectory.TrajectoryEnum;

import java.util.Arrays;

public class ParticleCommandComponentTrajectoryType extends ParticleCommandComponent {

    public ParticleCommandComponentTrajectoryType(ParticleCommandComponent previousComponent) {
        super(previousComponent);
    }

    @Override
    void initializePossibleValues() {
        super.possibleValues = Arrays
                .stream(TrajectoryEnum.values())
                .map(trajectory -> trajectory.toString().toLowerCase())
                .toList();
    }

    @Override
    boolean componentIsValid() {
        return false;
    }

    @Override
    Class<?> nextComponentExpected() {
        return null;
    }

    @Override
    boolean nextComponentRequired() {
        return false;
    }

}
