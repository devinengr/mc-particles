package onl.devin.mc_particles.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ParticleCommand implements CommandExecutor {

    private Command command;
    private CommandSender commandSender;
    private String commandString;
    private String[] commandComponents;

    public String getName() {
        return "particles";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender,
                             @NotNull Command command,
                             @NotNull String s,
                             @NotNull String[] strings) {
        // todo usage: /<command> [<player>] <start/stop> [<particle> <pattern> <trajectory>]



        return true;
    }

}
