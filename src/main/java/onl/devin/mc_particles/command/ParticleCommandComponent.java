package onl.devin.mc_particles.command;

import onl.devin.mc_particles.effect.ParticleEffectEnum;
import onl.devin.mc_particles.trajectory.TrajectoryEnum;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.List;

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

    public static boolean firstComponentIsPlayer(String[] partialCommand) {
        if (partialCommand.length >= 1) {
            if (PLAYER.getOptions().contains(partialCommand[0])) {
                return true;
            }
        }
        return false;
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
        int indexAction = 0;
        if (firstComponentIsPlayer(partialCommand)) {
            indexAction = 1;
        }
        if (partialCommand[indexAction].equalsIgnoreCase("stop")) {
            return true;
        }
        return false;
    }

    public static ParticleCommandComponent[] getExpectedComponents(String[] partialCommand) {
        int argCount = partialCommand.length;
        if (argCount <= 1) {
            return new ParticleCommandComponent[]{ACTION, PLAYER};
        } else {
            if ((firstComponentIsPlayer(partialCommand) && argCount >= 2)
                    || (!firstComponentIsPlayer(partialCommand) && argCount >= 1)) {
                if (actionIsStop(partialCommand)) {
                    return null;
                }
            }
            return new ParticleCommandComponent[] {
                    getExpectedByArgCount(partialCommand,
                    firstComponentIsPlayer(partialCommand)) };
        }
    }

}
