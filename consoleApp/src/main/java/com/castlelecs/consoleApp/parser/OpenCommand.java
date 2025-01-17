package com.castlelecs.consoleApp.parser;

import java.awt.Desktop;
import java.net.URI;

import com.castlelecs.shorter.interactors.URIShortenerInteractor;
import com.castlelecs.shorter.models.ShortURI;

public class OpenCommand implements Command {

    private String shortURI;
    private String name;
    private String id;
    private URIShortenerInteractor interactor;

    public OpenCommand(
        String shortURI,
        String name,
        String id,
        URIShortenerInteractor interactor
    ) {
        this.shortURI = shortURI;
        this.name = name;
        this.id = id;
        this.interactor = interactor;
    }

    public String getShortURI() {
        return shortURI;
    }

    @Override
    public int getId() {
        return 2;
    }

    @Override
    public void execute() throws Exception {
        ShortURI uri = interactor.getShortURI(getShortURI(), name, id);

        if (uri.isExpired()) {
            throw new OpenCommandException.Expired();
        }

        uri.decreaseUsage();

        try {
            Desktop
                .getDesktop()
                .browse(
                    new URI(uri.getLongURI())
                );
        } catch (Exception e) {}
    }
}
