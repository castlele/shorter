package com.castlelecs.consoleApp.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.castlelecs.shorter.interactors.URIDataStore;
import com.castlelecs.shorter.interactors.URIShortenerInteractor;
import com.castlelecs.shorter.interactors.UsersDataStore;
import com.castlelecs.shorter.models.ShortURI;
import com.castlelecs.shorter.models.User;

public class ConvertCommandTests extends BaseCommandTests {

    @Test
    public void convertCommand() {
        Map<User, List<ShortURI>> uriStorage = new HashMap<>();
        List<User> userStorage = new ArrayList<>();
        URIShortenerInteractor interactor = createInteractor(uriStorage, userStorage);
        String longURI = "https://luarocks.org/modules/castlelecs";
        String name = "Javie";
        String id = UUID.randomUUID().toString();
        int timeLimit = -1;
        int useLimit = -1;
        userStorage.add(new User(name, id));
        ConvertCommand sut = new ConvertCommand(
            longURI,
            name,
            id,
            timeLimit,
            useLimit,
            interactor
        );

        assertNotThrows(() -> {
            sut.execute();
        });

        assertEquals(1, uriStorage.size());
    }

    private URIShortenerInteractor createInteractor(
        Map<User, List<ShortURI>> uriStorage,
        List<User> userStorage
    ) {
        return new URIShortenerInteractor(
            new URIDataStore() {
                @Override
                public void saveURI(ShortURI uri, User user) {
                    List<ShortURI> uris = uriStorage.get(user);

                    if (uris == null) {
                        uris = new ArrayList<>();
                    }

                    uris.add(uri);

                    uriStorage.put(user, uris);
                }

            }, new UsersDataStore() {
                @Override
                public void saveUser(User user) {
                    userStorage.add(user);
                }

                @Override
                public User getUserByName(String name) {
                    for (User user : userStorage) {
                        if (user.getShortName() == name) {
                            return user;
                        }
                    }

                    return null;
                }

                @Override
                public User getUserById(String id) {
                    for (User user : userStorage) {
                        if (user.getId() == id) {
                            return user;
                        }
                    }

                    return null;
                }

            }
        );
    }
}
