package com.castlelecs.shorter.models;

public class ShortURI {

    private String shortURI;
    private String longURI;
    private int useLimit;
    private int timeLimit;

    public ShortURI(String shortURI, String longURI, int useLimit, int timeLimit) {
        this.shortURI = shortURI;
        this.longURI = longURI;
        this.useLimit = useLimit;
        this.timeLimit = timeLimit;
    }

    public String getShortURI() {
        return shortURI;
    }

    public String getLongURI() {
        return longURI;
    }

    public int getUriLimit() {
        return useLimit;
    }

    public int getLifeTime() {
        return timeLimit;
    }
}
