package com.castlelecs.shorter.models;

public class User {

    private String shortName;
    private String id;

    public User(String shortName, String id) {
        this.shortName = shortName;
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User)obj;

        return shortName == other.shortName && id == other.id;
    }
}
