package com.castlelecs.shorter.interactors;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.castlelecs.shorter.models.ShortURI;
import com.castlelecs.shorter.models.User;

public class URIShortenerInteractorTests {

    @Test
    public void uriCanBeCreatedForUser() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        User user = createUser();
        Map<User, List<ShortURI>> storage = new HashMap<>();
        URIDataStore dataStore = createDataStore(storage);
        URIShortenerInteractor sut = new URIShortenerInteractor(dataStore, createUsersDataStore());
        ShortURI result = sut.createURI(longURI);

        sut.saveURI(result, user);

        assertArrayEquals(
            storage.get(user).toArray(),
            List.of(result).toArray()
        );
    }

    private User createUser() {
        return new AuthInteractor().createUser();
    }

    private URIDataStore createDataStore(Map<User, List<ShortURI>> storage) {
        return new URIDataStore() {

            @Override
            public void saveURI(ShortURI uri, User user) {
                List<ShortURI> uris = storage.get(user);

                if (uris == null) {
                    uris = new ArrayList<>();
                }

                uris.add(uri);

                storage.put(user, uris);
            }

            @Override
            public ShortURI getShortURI(String shortURI, User user) {
                for (ShortURI uri : storage.get(user)) {
                    if (uri.getShortURI() == shortURI) {
                        return uri;
                    }
                }

                return null;
            }
        };
    }

    private UsersDataStore createUsersDataStore() {
        return new UsersDataStore() {
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
        };
    }
}
