package by.shibaev.jdbc.controller.command.provider;


import by.shibaev.jdbc.controller.command.CommandProcessor;
import by.shibaev.jdbc.controller.command.type.CommandType;

public class CommandProvider {
    public static CommandProcessor provide(String command) {
        CommandProcessor commandProcessor = CommandType.EMPTY_COMMAND.getCommand();
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getName().equals(command)) {
                commandProcessor = commandType.getCommand();
            }
        }
        return commandProcessor;
    }
}
