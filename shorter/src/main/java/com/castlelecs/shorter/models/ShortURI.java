package com.castlelecs.shorter.models;

public class ShortURI {

    private String shortURI;
    private String longURI;
    private int useLimit;
    private int timeLimit;

    private dateCreated;
    private int usage;

    public ShortURI(String shortURI, String longURI, int useLimit, int timeLimit) {
        this.shortURI = shortURI;
        this.longURI = longURI;
        this.useLimit = useLimit;
        this.timeLimit = timeLimit;

        this.usage = this.useLimit;
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

    public void decreaseUsage() {
        usage -= 1;
    }

    public boolean isExpired() {
        return usage <= 0;
    }
}
