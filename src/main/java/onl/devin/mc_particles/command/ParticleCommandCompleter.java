package onl.devin.mc_particles.command;

import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ParticleCommandCompleter implements TabCompleter {

    private ParticleCommand particleCommand;

    public ParticleCommandCompleter(ParticleCommand particleCommand) {
        this.particleCommand = particleCommand;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender,
                                                @NotNull Command command,
                                                @NotNull String s,
                                                @NotNull String[] strings) {
        // get all possible arguments for the current argument
        PCComponent[] expected = PCComponent.getExpectedComponents(strings);
        List<String> all = new ArrayList<>();
        if (expected != null) {
            for (PCComponent component : expected) {
                if (component != null) {
                    for (String option : component.getOptions()) {
                        // match against the start of what the user typed
                        String currentArg = strings[strings.length - 1];
                        if (option.contains(currentArg)) {
                            all.add(option);
                        }
                    }
                }
            }
        }
        return all;
    }

}
