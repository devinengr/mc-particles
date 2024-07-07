package onl.devin.mc_particles.command;

import onl.devin.mc_particles.effect.ParticleEffectEnum;
import onl.devin.mc_particles.trajectory.TrajectoryEnum;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.List;

import static onl.devin.mc_particles.command.ParticleCommandChecker.argumentIsActionStop;
import static onl.devin.mc_particles.command.ParticleCommandChecker.firstArgumentIsOnlinePlayer;

public enum ParticleCommandComponent {

    PLAYER(Bukkit.getOnlinePlayers().stream().map(Player::getName).toList()),
    ACTION(List.of("start", "stop")),
    PARTICLE_EFFECT(toLowerCaseStrings(List.of(Particle.values()))),
    PARTICLE_TYPE(toLowerCaseStrings(List.of(ParticleEffectEnum.values()))),
    TRAJECTORY_TYPE(toLowerCaseStrings(List.of(TrajectoryEnum.values()))),
    ;

    private static List<String> toLowerCaseStrings(List<?> enumValues) {
        return enumValues.stream().map(e -> e.toString().toLowerCase()).toList();
    }

    private List<String> options;

    ParticleCommandComponent(List<String> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }

    public static ParticleCommandComponent getExpectedByArgCount(String[] partialCommand, boolean firstIsPlayer) {
        int offset = partialCommand.length;
        if (firstIsPlayer) {
            offset -= 1;
        }
        switch (offset) {
            case 1: return ACTION;
            case 2: return PARTICLE_EFFECT;
            case 3: return PARTICLE_TYPE;
            case 4: return TRAJECTORY_TYPE;
            default: return null;
        }
    }

    public static boolean actionIsStop(String[] partialCommand) {
        return argumentIsActionStop(partialCommand, 0)
                || argumentIsActionStop(partialCommand, 1);
    }

    public static ParticleCommandComponent[] getExpectedComponents(String[] partialCommand) {
        int argCount = partialCommand.length;
        if (argCount <= 1) {
            return new ParticleCommandComponent[]{ACTION, PLAYER};
        } else {
            if (actionIsStop(partialCommand)) {
                return null;
            }
            return new ParticleCommandComponent[] {
                    getExpectedByArgCount(partialCommand,
                    firstArgumentIsOnlinePlayer(partialCommand)) };
        }
    }

}
