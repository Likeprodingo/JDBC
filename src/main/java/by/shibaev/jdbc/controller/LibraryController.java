package by.shibaev.jdbc.controller;


import by.shibaev.jdbc.controller.command.CommandProcessor;
import by.shibaev.jdbc.controller.command.provider.CommandProvider;
import by.shibaev.jdbc.model.entity.Book;

import java.util.List;
import java.util.Map;

public class LibraryController {
    private static  LibraryController instance;

    public static LibraryController getInstance(){
        if(instance==null){
            instance = new LibraryController();
        }
        return instance;
    }
    public Map<String, List<Book>> requestProcess(String command, Map<String,String> data){
        CommandProcessor commandProcessor = CommandProvider.provide(command);
        return commandProcessor.process(data);
    }
}
