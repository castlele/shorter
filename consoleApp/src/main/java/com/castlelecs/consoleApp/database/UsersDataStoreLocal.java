package com.castlelecs.consoleApp.database;

import java.util.ArrayList;
import java.util.List;

import com.castlelecs.shorter.interactors.UsersDataStore;
import com.castlelecs.shorter.models.User;

public class UsersDataStoreLocal implements UsersDataStore {

    private List<User> storage = new ArrayList<>();

    public void saveUser(User user) {
        storage.add(user);
    }

    @Override
    public User getUserByName(String name) {
        for (User user : storage) {
            if (user.getShortName() == name) {
                return user;
            }
        }

        return null;
    }

    @Override
    public User getUserById(String id) {
        for (User user : storage) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }
}
