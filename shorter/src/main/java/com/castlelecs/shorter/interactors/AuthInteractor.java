package com.castlelecs.shorter.interactors;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.castlelecs.shorter.models.User;

public class AuthInteractor {

    private UsersDataStore usersDataStore;

    public AuthInteractor() {
        this(new UsersDataStore() {
            private List<User> storage = new ArrayList<>();

            @Override
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
        });
    }

    public AuthInteractor(UsersDataStore dataStore) {
        usersDataStore = dataStore;
    }

    public void saveUser(User user) {
        usersDataStore.saveUser(user);
    }

    public User createUser() {
        return new User(null, UUID.randomUUID().toString());
    }

    public User createUser(String id) throws AuthException.InvalidUUID {
        return createUser(null, id);
    }

    public User createUser(
        String name,
        String id
    ) throws AuthException.InvalidUUID {
        String finalId = id != null ? id : UUID.randomUUID().toString();

        if (!isUUIDValid(finalId)) {
            throw new AuthException.InvalidUUID();
        }

        return new User(name, finalId);
    }

    private boolean isUUIDValid(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
