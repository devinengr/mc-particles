package onl.devin.mc_particles.command;

import onl.devin.mc_particles.effect.ParticleEffectEnum;
import org.bukkit.Particle;

import java.util.Arrays;

public class ParticleCommandComponentParticleType extends ParticleCommandComponent {

    public ParticleCommandComponentParticleType(ParticleCommandComponent previousComponent) {
        super(previousComponent);
    }

    @Override
    void initializePossibleValues() {
        super.possibleValues = Arrays
                .stream(ParticleEffectEnum.values())
                .map(effect -> effect.toString().toLowerCase())
                .toList();
    }

    @Override
    boolean componentIsValid() {
        return false;
    }

    @Override
    Class<?> nextComponentExpected() {
        return ParticleCommandComponentTrajectoryType.class;
    }

    @Override
    boolean nextComponentRequired() {
        return true;
    }

}
