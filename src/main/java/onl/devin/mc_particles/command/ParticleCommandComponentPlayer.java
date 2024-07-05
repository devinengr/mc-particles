package onl.devin.mc_particles.command;

import org.bukkit.Bukkit;

import java.util.List;

public class ParticleCommandComponentPlayer extends ParticleCommandComponent {

    public ParticleCommandComponentPlayer(ParticleCommandComponent previousComponent) {
        super(previousComponent);
    }

    @Override
    void initializePossibleValues() {
        super.possibleValues = Bukkit.getOnlinePlayers()
                .stream()
                .map(player -> player.getName())
                .toList();
    }

    @Override
    boolean componentIsValid() {
        return false;
    }

    @Override
    Class<?> nextComponentExpected() {
        return ParticleCommandComponentAction.class;
    }

    @Override
    boolean nextComponentRequired() {
        return true;
    }

}
