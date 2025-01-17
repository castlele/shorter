package com.castlelecs.consoleApp.parser;

import com.castlelecs.shorter.interactors.URIShortenerException;
import com.castlelecs.shorter.interactors.URIShortenerInteractor;
import com.castlelecs.shorter.models.ShortURI;

public class ConvertCommand implements Command {

    private String longURI;
    private String name;
    private String id;
    private int timeLimit;
    private int useLimit;
    private URIShortenerInteractor interactor;

    public ConvertCommand(
    String longURI,
        String name,
        String id,
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

    public String getLongURI() {
        return longURI;
    }

    public String getUserName() {
        return name;
    }

    public String getUserId() {
        return id;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getUseLimit() {
        return useLimit;
    }

    @Override
    public int getId() {
        return 1;
    }

    @Override
    public void execute() throws URIShortenerException {
        ShortURI shortURI = interactor.createURI(longURI, useLimit, timeLimit);
        interactor.saveURI(shortURI, name, id);
    }
}
