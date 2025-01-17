package com.castlelecs.shorter.models;

import java.util.Calendar;
import java.util.Date;

public class ShortURI {

    private String shortURI;
    private String longURI;
    private int useLimit;
    private int timeLimit;

    private Date dateCreated;
    private int usage;
    private Calendar calendar = Calendar.getInstance();

    public ShortURI(String shortURI, String longURI, int useLimit, int timeLimit) {
        this.shortURI = shortURI;
        this.longURI = longURI;
        this.useLimit = useLimit;
        this.timeLimit = timeLimit;

        this.dateCreated = new Date();
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
        if (usage == 0) return;
        usage -= 1;
    }

    public boolean isExpired() {
        if (usage == -1 && timeLimit == -1) return false;

        calendar.setTime(dateCreated);
        calendar.add(Calendar.DATE, timeLimit);

        return usage <= 0 || calendar.getTime().before(new Date());
    }
}
