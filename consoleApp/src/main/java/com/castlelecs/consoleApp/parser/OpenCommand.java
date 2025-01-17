package com.castlelecs.consoleApp.parser;

import com.castlelecs.shorter.interactors.URIShortenerException;
import com.castlelecs.shorter.interactors.URIShortenerInteractor;
import com.castlelecs.shorter.models.ShortURI;

public class OpenCommand implements Command {

    private String shortURI;
    private int timeLimit;
    private int useLimit;
    private URIShortenerInteractor interactor;

    public OpenCommand(
        String shortURI,
        int timeLimit,
        int useLimit,
        URIShortenerInteractor interactor
    ) {
        this.longURI = longURI;
        this.name = name;
        this.id = id;
        this.timeLimit = timeLimit;
        this.useLimit = useLimit;
        this.interactor = interactor;
    }

    public String getShortURI() {
        return shortURI;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getUseLimit() {
        return useLimit;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public void execute() throws URIShortenerException {
        ShortURI uri = interactor.getShortURI(getShortURI());

        uri.getLongURI();
    }
}
