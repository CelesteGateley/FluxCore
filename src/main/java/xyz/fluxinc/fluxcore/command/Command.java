package xyz.fluxinc.fluxcore.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import dev.jorel.commandapi.executors.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Command {

    private final List<Argument> arguments;
    private final String name;
    private String[] aliases;
    private CommandExecutor executor;

    public Command(String command) {
        this.name = command;
        this.arguments = new ArrayList<>();
    }

    public Command(String command, String[] aliases) {
        this(command);
        this.aliases = aliases;
    }

    public Command literal(String value) {
        arguments.add(new LiteralArgument(value));
        return this;
    }

    public Command list(String name,  String[] list) {
        ListArgument<String> argument = new ListArgumentBuilder<String>(name).withList(List.of(list)).withStringMapper().build();
        arguments.add(argument);
        return this;
    }

    public Command list(String name, List<String> list) {
        ListArgument<String> argument = new ListArgumentBuilder<String>(name).withList(list).withStringMapper().build();
        arguments.add(argument);
        return this;
    }
    
    public Command player(String name) {
        arguments.add(new EntitySelectorArgument(name, EntitySelector.ONE_PLAYER));
        return this;
    }
    
    public Command players(String name) {
        arguments.add(new EntitySelectorArgument(name, EntitySelector.MANY_PLAYERS));
        return this;
    }
    
    public Command entity(String name) {
        arguments.add(new EntitySelectorArgument(name, EntitySelector.ONE_ENTITY));
        return this;
    }
    
    public Command entities(String name) {
        arguments.add(new EntitySelectorArgument(name, EntitySelector.MANY_ENTITIES));
        return this;
    }

    public Command string(String name) {
        arguments.add(new StringArgument(name));
        return this;
    }

    public Command integer(String name) {
        arguments.add(new IntegerArgument(name));
        return this;
    }

    public Command floating(String name) {
        arguments.add(new FloatArgument(name));
        return this;
    }

    public Command numeric(String name) {
        arguments.add(new DoubleArgument(name));
        return this;
    }

    public Command bool(String name) {
        arguments.add(new BooleanArgument(name));
        return this;
    }

    public Command greedy(String name) {
        arguments.add(new GreedyStringArgument(name));
        return this;
    }

    public Command color(String name) {
        arguments.add(new ChatColorArgument(name));
        return this;
    }

    public Command executor(CommandExecutor executor) {
        this.executor = executor;
        return this;
    }

    public void register() {
        CommandAPICommand command = new CommandAPICommand(name).withAliases(aliases);
        for (Argument argument : arguments) { command.withArguments(argument); }
        command.executes(executor);
        command.register();
    }
}
