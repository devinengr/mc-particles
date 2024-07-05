package onl.devin.mc_particles.command;

import org.bukkit.Particle;

import java.util.Arrays;

public class ParticleCommandComponentParticleEffect extends ParticleCommandComponent {

    public ParticleCommandComponentParticleEffect(ParticleCommandComponent previousComponent) {
        super(previousComponent);
    }

    @Override
    void initializePossibleValues() {
        super.possibleValues = Arrays
                .stream(Particle.values())
                .map(particle -> particle.toString().toLowerCase())
                .toList();
    }

    @Override
    boolean componentIsValid() {
        return false;
    }

    @Override
    Class<?> nextComponentExpected() {
        return ParticleCommandComponentParticleType.class;
    }

    @Override
    boolean nextComponentRequired() {
        return true;
    }

}
