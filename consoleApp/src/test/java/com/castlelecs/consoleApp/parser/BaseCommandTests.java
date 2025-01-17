package com.castlelecs.consoleApp.parser;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.function.Executable;

public class BaseCommandTests {

    protected void assertNotThrows(Executable executable) {
        try {
            executable.execute();
        } catch (Throwable e) {
            fail("No Error is expected, but got one with message: " + e.getMessage());
        }
    }
}
