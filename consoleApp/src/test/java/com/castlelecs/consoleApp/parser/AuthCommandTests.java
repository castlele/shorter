package com.castlelecs.consoleApp.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.castlelecs.shorter.interactors.AuthInteractor;
import com.castlelecs.shorter.interactors.UsersDataStore;
import com.castlelecs.shorter.models.User;

public class AuthCommandTests extends BaseCommandTests {

    @Test
    public void authCommand() {
        List<User> storage = new ArrayList<>();
        String uuid = UUID.randomUUID().toString();
        String name = "Javie";
        AuthCommand sut = new AuthCommand(uuid, name, createInteractor(storage));

        assertNotThrows(() -> {
            sut.execute();
        });

        assertEquals(1, storage.size());
        assertEquals(new User(name, uuid), storage.get(0));
    }

    private AuthInteractor createInteractor(List<User> storage) {
        return new AuthInteractor(new UsersDataStore() {
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
}
