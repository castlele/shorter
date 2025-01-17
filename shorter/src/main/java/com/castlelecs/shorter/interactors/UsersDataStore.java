package com.castlelecs.shorter.interactors;

import com.castlelecs.shorter.models.User;

public interface UsersDataStore {

    void saveUser(User user);
    User getUserByName(String name);
    User getUserById(String id);
}
