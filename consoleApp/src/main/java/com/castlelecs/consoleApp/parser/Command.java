package com.castlelecs.consoleApp.parser;

public interface Command {

    int getId();
    void execute() throws Exception;
}
