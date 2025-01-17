package com.castlelecs.shorter.interactors;

import com.castlelecs.shorter.models.User;

public interface UsersDataStore {
    public void saveUser(User user);
    public User getUserByName(String name);
    public User getUserById(String id);
}
