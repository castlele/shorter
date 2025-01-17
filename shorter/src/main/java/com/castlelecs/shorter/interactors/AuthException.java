package com.castlelecs.shorter.interactors;

public class AuthException extends Exception {

    public static class InvalidUUID extends AuthException {
        public InvalidUUID() {
            super();
        }
    }
}
