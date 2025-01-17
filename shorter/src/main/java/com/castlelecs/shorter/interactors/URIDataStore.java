package com.castlelecs.shorter.interactors;

import com.castlelecs.shorter.models.ShortURI;
import com.castlelecs.shorter.models.User;

public interface URIDataStore {

    void saveURI(ShortURI uri, User user);
    ShortURI getShortURI(String shortURI, User user);
}
