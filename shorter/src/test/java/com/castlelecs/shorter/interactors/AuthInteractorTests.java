package com.castlelecs.shorter.interactors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.castlelecs.shorter.models.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class AuthInteractorTests {

    @Test
    public void userCanBeCreatedWithRandomUUID() {
        AuthInteractor sut = new AuthInteractor();

        User result = sut.createUser();

        assertNotNull(result);
        assertNull(result.getShortName());
        assertNotNull(result.getId());
    }

    @Test
    public void userCanBeCreatedWithCustomUUID() {
        String id = UUID.randomUUID().toString();
        AuthInteractor sut = new AuthInteractor();

        assertNotThrows(() -> {
            User result = sut.createUser(id);

            assertEquals(id, result.getId());
        });
    }

    @Test
    public void userCantBeCreatedFromInvalidUUID() {
        String id = "yooo, I'm invalid UUID";
        AuthInteractor sut = new AuthInteractor();

        assertThrows(AuthException.InvalidUUID.class, () -> {
            sut.createUser(id);
        });
    }

    @Test
    public void userCanBeCreatedWithShortNameAndRandomUUID() {
        String name = "Javie";
        AuthInteractor sut = new AuthInteractor();

        assertNotThrows(() -> {
            User result = sut.createUser(name, null);

            assertEquals(name, result.getShortName());
        });
    }

    @Test
    public void userCanBeCreatedWithShortNameAndCustomUUID() {
        String name = "Javie";
        String id = UUID.randomUUID().toString();
        AuthInteractor sut = new AuthInteractor();

        assertNotThrows(() -> {
            User result = sut.createUser(name, id);

            assertEquals(name, result.getShortName());
            assertEquals(id, result.getId());
        });
    }

    @Test
    public void userCantBeCreatedWithShortNameAndInvalidUUID() {
        String name = "Javie";
        String id = "yooo, I'm invalid UUID";
        AuthInteractor sut = new AuthInteractor();

        assertThrows(AuthException.InvalidUUID.class, () -> {
            sut.createUser(name, id);
        });
    }

    private void assertNotThrows(Executable executable) {
        try {
            executable.execute();
        } catch (Throwable e) {
            fail("No Error is expected, but got one with message: " + e.getMessage());
        }
    }

}
