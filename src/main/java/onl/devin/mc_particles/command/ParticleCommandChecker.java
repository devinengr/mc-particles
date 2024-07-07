package onl.devin.mc_particles.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParticleCommandChecker {

    public static boolean argumentExists(String[] strings, int index) {
        return strings.length > index;
    }

    public static boolean argumentIsActionStart(String[] strings, int index) {
        return argumentExists(strings, index) &&
                strings[index].equalsIgnoreCase("start");
    }

    public static boolean argumentIsActionStop(String[] strings, int index) {
        return argumentExists(strings, index) &&
                strings[index].equalsIgnoreCase("stop");
    }

    public static boolean argumentIsAction(String[] strings, int index) {
        return argumentIsActionStart(strings, index) ||
                argumentIsActionStop(strings, index);
    }

    public static boolean argumentIsParticleEffect(String[] strings, int index) {
        return argumentExists(strings, index) &&
                ParticleCommandComponent.PARTICLE_EFFECT.getOptions().contains(strings[index]);
    }

    public static boolean argumentIsParticleType(String[] strings, int index) {
        return argumentExists(strings, index) &&
                ParticleCommandComponent.PARTICLE_TYPE.getOptions().contains(strings[index]);
    }

    public static boolean argumentIsTrajectoryType(String[] strings, int index) {
        return argumentExists(strings, index) &&
                ParticleCommandComponent.TRAJECTORY_TYPE.getOptions().contains(strings[index]);
    }

    public static boolean firstArgumentIsAction(String[] strings) {
        return argumentIsAction(strings, 0);
    }

    public static boolean secondArgumentIsAction(String[] strings) {
        return argumentIsAction(strings, 1);
    }

    public static boolean firstArgumentIsAnyPlayer(String[] strings) {
        return argumentExists(strings, 0) && !firstArgumentIsAction(strings);
    }

    public static boolean firstArgumentIsOnlinePlayer(String[] strings) {
        return !firstArgumentIsAction(strings) &&
                firstArgumentIsAnyPlayer(strings) &&
                ParticleCommandComponent.PLAYER.getOptions().contains(strings[0]);
    }

    public static boolean playerSpecifiedButNotOnline(String[] strings) {
        return firstArgumentIsAnyPlayer(strings) && !firstArgumentIsOnlinePlayer(strings);
    }

    public static boolean playerNotSpecifiedButIsServer(String[] strings, CommandSender sender) {
        return firstArgumentIsAction(strings) && !(sender instanceof Player);
    }

    public static boolean actionIsValid(String[] strings) {
        return firstArgumentIsAction(strings) || secondArgumentIsAction(strings);
    }

    public static boolean particleIsValid(String[] strings) {
        return (firstArgumentIsAction(strings) && argumentIsParticleEffect(strings, 1)) ||
                (secondArgumentIsAction(strings) && argumentIsParticleEffect(strings, 2));
    }

    public static boolean particleEffectTypeIsValid(String[] strings) {
        return (firstArgumentIsAction(strings) && argumentIsParticleType(strings, 2)) ||
                (secondArgumentIsAction(strings) && argumentIsParticleType(strings, 3));
    }

    public static boolean trajectoryTypeIsValid(String[] strings) {
        return (firstArgumentIsAction(strings) && argumentIsTrajectoryType(strings, 3)) ||
                (secondArgumentIsAction(strings) && argumentIsTrajectoryType(strings, 4));
    }

    public static void sendGreenMessage(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.GREEN + msg);
    }

    public static void sendRedMessage(CommandSender sender, String msg) {
        sender.sendMessage(ChatColor.RED + msg);
    }

    public static boolean checkForInvalidOptions(CommandSender sender, String[] strings) {
        if (playerSpecifiedButNotOnline(strings)) {
            sendRedMessage(sender, String.format("Player %s is not online", strings[0]));
            return false;
        }
        if (playerNotSpecifiedButIsServer(strings, sender)) {
            sendRedMessage(sender, "Please specify a player.");
            return false;
        }
        if (!actionIsValid(strings)) {
            sendRedMessage(sender, "Please specify an action.");
            return false;
        }
        if (argumentIsActionStart(strings, 0) || argumentIsActionStart(strings, 1)) {
            if (!particleIsValid(strings)) {
                sendRedMessage(sender, "Please specify a particle effect.");
                return false;
            }
            if (!particleEffectTypeIsValid(strings)) {
                sendRedMessage(sender, "Please specify a particle type (pattern).");
                return false;
            }
            if (!trajectoryTypeIsValid(strings)) {
                sendRedMessage(sender, "Please specify a trajectory type.");
                return false;
            }
            sendGreenMessage(sender, "Spawning particles.");
        } else {
            sendGreenMessage(sender, "Stopping particles.");
        }
        return true;
    }

}
