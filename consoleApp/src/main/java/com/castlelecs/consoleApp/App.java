package com.castlelecs.consoleApp;

import java.util.List;

import com.castlelecs.consoleApp.database.UsersDataStoreLocal;
import com.castlelecs.consoleApp.parser.Command;
import com.castlelecs.consoleApp.parser.CommandParser;

public class App {
    public static void main(String[] args) {
        CommandParser parser = new CommandParser(new UsersDataStoreLocal());

        List<Command> commands = parser.parse(String.join(" ", args));

        for (Command command : commands) {
            try {
                command.execute();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }
        }
    }
}
