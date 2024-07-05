package onl.devin.mc_particles.command;

import java.util.List;

public class ParticleCommandComponentAction extends ParticleCommandComponent {

    public ParticleCommandComponentAction(ParticleCommandComponent previousComponent) {
        super(previousComponent);
    }

    @Override
    void initializePossibleValues() {
        super.possibleValues = List.of("start", "stop");
    }

    @Override
    boolean componentIsValid() {
        return false;
    }

    @Override
    Class<?> nextComponentExpected() {
        return ParticleCommandComponentParticleEffect.class;
    }

    @Override
    boolean nextComponentRequired() {
        return super.actualValue.equalsIgnoreCase("start");
    }

}
