package com.castlelecs.shorter.interactors;

public class URIShortenerException extends Exception {

    public static class NoUserForURI extends URIShortenerException {
        public NoUserForURI() {
            super();
        }
    }

    public static class NoMatchingNameWithId extends URIShortenerException {
        public NoMatchingNameWithId() {
            super();
        }
    }
}
