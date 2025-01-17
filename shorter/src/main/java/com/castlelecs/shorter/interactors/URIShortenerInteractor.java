package com.castlelecs.shorter.interactors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.castlelecs.shorter.URIShortener;
import com.castlelecs.shorter.models.ShortURI;
import com.castlelecs.shorter.models.User;

public class URIShortenerInteractor {

    private static int NO_USE_LIMIT = -1;
    private static int NO_TIME_LIMIT = -1;

    private URIShortener shortener = new URIShortener();
    private URIDataStore uriDataStore;
    private UsersDataStore usersDataStore;

    public URIShortenerInteractor(UsersDataStore usersDataStore) {
        this(new URIDataStore() {
            private Map<User, List<ShortURI>> storage = new HashMap<>();

            @Override
            public void saveURI(ShortURI uri, User user) {
                List<ShortURI> uris = storage.get(user);

                if (uris == null) {
                    uris = new ArrayList<>();
                }

                uris.add(uri);

                storage.put(user, uris);
            }
        }, usersDataStore);
    }

    public URIShortenerInteractor(
        URIDataStore dataStore,
        UsersDataStore usersDataStore
    ) {
        uriDataStore = dataStore;
        this.usersDataStore = usersDataStore;
    }

    public ShortURI createURI(String longURI) {
        return createURI(longURI, NO_USE_LIMIT, NO_TIME_LIMIT);
    }

    public ShortURI createURI(String longURI, int useLimit, int timeLimit) {
        String stringURI = shortener.convert(longURI).toString();

        return new ShortURI(
            stringURI,
            longURI,
            useLimit,
            timeLimit
        );
    }

    public void saveURI(
        ShortURI uri,
        String name,
        String uuid
    ) throws URIShortenerException {
        User user = null;

        if (name != null) {
            user = usersDataStore.getUserByName(name);

            if (user.getId() != uuid) {
                throw new URIShortenerException.NoMatchingNameWithId();
            }
        } else if (uuid != null) {
            user = usersDataStore.getUserById(uuid);
        }


        if (user == null) {
            throw new URIShortenerException.NoUserForURI();
        }

        saveURI(uri, user);
    }

    public void saveURI(ShortURI uri, User user) {
        uriDataStore.saveURI(uri, user);
    }
}
