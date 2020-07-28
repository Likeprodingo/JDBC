package by.shibaev.jdbc.controller.command.type;


import by.shibaev.jdbc.controller.command.CommandProcessor;
import by.shibaev.jdbc.controller.command.impl.*;

public enum CommandType {
    ADD("add", new AddCommandProcessorImpl()),
    REMOVE("remove", new RemoveCommandProcessorImpl()),
    FIND_ID("find", new FindCommandProcessorImpl()),
    FIND_NAME("find name", new FindNameCommandProcessorImpl()),
    FIND_AUTHOR("find author", new FindAuthorCommandProcessorImpl()),
    FIND_PUBLISHER("find publisher", new FindPublisherCommandProcessorImpl()),
    FIND_YEAR("find year", new FindYearCommandProcessorImpl()),
    FIND_ALL("find all", new FindAllCommandProcessorImpl()),
    EMPTY_COMMAND("", new FindAllCommandProcessorImpl());

    String name;
    CommandProcessor command;

    CommandType(String name, CommandProcessor command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public CommandProcessor getCommand() {
        return command;
    }
}
