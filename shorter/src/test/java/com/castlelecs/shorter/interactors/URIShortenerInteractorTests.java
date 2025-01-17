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
        URIShortenerInteractor sut = new URIShortenerInteractor(dataStore);
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
        };
    }
}
