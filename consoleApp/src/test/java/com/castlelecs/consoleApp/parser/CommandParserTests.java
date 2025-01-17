package com.castlelecs.consoleApp.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.castlelecs.consoleApp.database.UsersDataStoreLocal;

public class CommandParserTests {

    @Test
    public void initUserWithDefaultParams() {
        List<Command> commands = createCommandParser().parse("shorter init");

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
    }

    @Test
    public void initUserWithUUIDParam() {
        String uuid = UUID.randomUUID().toString();

        List<Command> commands = createCommandParser().parse(
            String.format("shorter init --uuid %s", uuid)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
        assertEquals(uuid, ((AuthCommand)commands.get(0)).getUUID());
    }

    @Test
    public void initUserWithNameParam() {
        String name = "Javie";

        List<Command> commands = createCommandParser().parse(
            String.format("shorter init --name %s", name)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
        assertEquals(name, ((AuthCommand)commands.get(0)).getName());
    }

    @Test
    public void initUserWithNameAndUUIDParam() {
        String name = "Javie";
        String uuid = UUID.randomUUID().toString();

        List<Command> commands = createCommandParser().parse(
            String.format("shorter init --name %s --uuid %s", name, uuid)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
        assertEquals(name, ((AuthCommand)commands.get(0)).getName());
        assertEquals(uuid, ((AuthCommand)commands.get(0)).getUUID());
    }

    @Test
    public void initUserWithUUIDAndNameParam() {
        String name = "Javie";
        String uuid = UUID.randomUUID().toString();

        List<Command> commands = createCommandParser().parse(
            String.format("shorter init --uuid %s --name %s", uuid, name)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
        assertEquals(name, ((AuthCommand)commands.get(0)).getName());
        assertEquals(uuid, ((AuthCommand)commands.get(0)).getUUID());
    }

    @Test
    public void convertLongURIWithoutAuth() {
        String longURI = "https://luarocks.org/modules/castlelecs";

        List<Command> commands = createCommandParser().parse(
            String.format("shorter convert %s", longURI)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ConvertCommand);
        assertEquals(longURI, ((ConvertCommand)commands.get(0)).getLongURI());
    }

    @Test
    public void convertLongURIWithNameAuth() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        String name = "Javie";

        List<Command> commands = createCommandParser().parse(
            String.format("shorter convert %s --user %s", longURI, name)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ConvertCommand);
        assertEquals(longURI, ((ConvertCommand)commands.get(0)).getLongURI());
        assertEquals(name, ((ConvertCommand)commands.get(0)).getUserName());
    }

    @Test
    public void convertLongURIWithUUIDAuth() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        String id = UUID.randomUUID().toString();

        List<Command> commands = createCommandParser().parse(
            String.format("shorter convert %s --id %s", longURI, id)
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ConvertCommand);
        assertEquals(longURI, ((ConvertCommand)commands.get(0)).getLongURI());
        assertEquals(id, ((ConvertCommand)commands.get(0)).getUserId());
    }

    @Test
    public void convertLongURIWithTimeLimit() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        String name = "Javie";
        int timeLimit = 2;

        List<Command> commands = createCommandParser().parse(
            String.format(
                "shorter convert %s --user %s --timelimit %d",
                longURI,
                name,
                timeLimit
            )
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ConvertCommand);
        assertEquals(longURI, ((ConvertCommand)commands.get(0)).getLongURI());
        assertEquals(name, ((ConvertCommand)commands.get(0)).getUserName());
        assertEquals(timeLimit, ((ConvertCommand)commands.get(0)).getTimeLimit());
    }

    @Test
    public void convertLongURIWithUseLimit() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        String name = "Javie";
        int useLimit = 10;

        List<Command> commands = createCommandParser().parse(
            String.format(
                "shorter convert %s --user %s --uselimit %d",
                longURI,
                name,
                useLimit
            )
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ConvertCommand);
        assertEquals(longURI, ((ConvertCommand)commands.get(0)).getLongURI());
        assertEquals(name, ((ConvertCommand)commands.get(0)).getUserName());
        assertEquals(useLimit, ((ConvertCommand)commands.get(0)).getUseLimit());
    }

    @Test
    public void convertLongURIWithTimeAndUseLimit() {
        String longURI = "https://luarocks.org/modules/castlelecs";
        String name = "Javie";
        int useLimit = 10;
        int timeLimit = 2;

        List<Command> commands = createCommandParser().parse(
            String.format(
                "shorter convert %s --user %s --uselimit %d --timelimit %d",
                longURI,
                name,
                useLimit,
                timeLimit
            )
        );

        assertEquals(1, commands.size());
        assertTrue(commands.get(0) instanceof ConvertCommand);
        assertEquals(longURI, ((ConvertCommand)commands.get(0)).getLongURI());
        assertEquals(name, ((ConvertCommand)commands.get(0)).getUserName());
        assertEquals(useLimit, ((ConvertCommand)commands.get(0)).getUseLimit());
        assertEquals(timeLimit, ((ConvertCommand)commands.get(0)).getTimeLimit());
    }

    @Test
    public void composableCommandsIsAllowed() {
        String name = "Javie";
        String longURI = "https://luarocks.org/modules/castlelecs";

        List<Command> commands = createCommandParser().parse(
            String.format(
                "shorter init --name %s convert %s --user %s",
                name,
                longURI,
                name
            )
        );

        assertEquals(2, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
        assertTrue(commands.get(1) instanceof ConvertCommand);
    }

    @Test
    public void composableCommandsIsAllowedInInvalidOrder() {
        String name = "Javie";
        String longURI = "https://luarocks.org/modules/castlelecs";

        List<Command> commands = createCommandParser().parse(
            String.format(
                "shorter convert %s --user %s init --name %s",
                longURI,
                name,
                name
            )
        );

        assertEquals(2, commands.size());
        assertTrue(commands.get(0) instanceof AuthCommand);
        assertTrue(commands.get(1) instanceof ConvertCommand);
    }

    private CommandParser createCommandParser() {
        return new CommandParser(new UsersDataStoreLocal());
    }
}
