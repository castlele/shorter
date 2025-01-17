package com.castlelecs.consoleApp.parser;

import com.castlelecs.shorter.interactors.AuthInteractor;
import com.castlelecs.shorter.models.User;

public class AuthCommand implements Command {

    private String uuid;
    private String name;
    private AuthInteractor interactor;

    public AuthCommand(String uuid, String name, AuthInteractor interactor) {
        this.uuid = uuid;
        this.name = name;
        this.interactor = interactor;
    }

    public String getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void execute() throws Exception {
        User user = interactor.createUser(name, uuid);
        interactor.saveUser(user);
    }
}
