package com.castlelecs.consoleApp.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.castlelecs.shorter.interactors.AuthInteractor;
import com.castlelecs.shorter.interactors.URIShortenerInteractor;
import com.castlelecs.shorter.interactors.UsersDataStore;

public class CommandParser {

    private static final String COMMAND = "shorter";
    private static final String AUTH = "init";
    private static final String AUTH_UUID = "--uuid";
    private static final String AUTH_NAME = "--name";
    private static final String CONVERT = "convert";
    private static final String CONVERT_TIME_LIMIT = "--timelimit";
    private static final String CONVERT_USE_LIMIT = "--uselimit";
    private static final String OPEN = "open";
    private static final String USER = "--user";
    private static final String ID = "--id";

    private UsersDataStore userStorage;

    public CommandParser(UsersDataStore userStorage) {
        this.userStorage = userStorage;
    }

    public List<Command> parse(String args) {
        List<Command> commands = new ArrayList<>();

        Scanner reader = new Scanner(args);

        while (reader.hasNext()) {
            switch (reader.next()) {
            case COMMAND: continue;
            case AUTH:
                commands.add(createAuthCommand(reader));
                break;
            case CONVERT:
                commands.add(createConvertCommand(reader));
                break;
            case OPEN:
                commands.add(createOpenCommand(reader));
                break;
            }
        }

        reader.close();

        commands.sort((lhs, rhs) -> {
            return Integer.compare(lhs.getId(), rhs.getId());
        });

        return commands;
    }

    private AuthCommand createAuthCommand(Scanner reader) {
        String uuid = null;
        String name = null;

        while (reader.hasNext(AUTH_UUID) || reader.hasNext(AUTH_NAME)) {
            switch (reader.next()) {
            case AUTH_UUID:
                uuid = reader.next();
                break;
            case AUTH_NAME:
                name = reader.next();
                break;
            }
        }

        return new AuthCommand(uuid, name, new AuthInteractor(userStorage));
    }

    private ConvertCommand createConvertCommand(Scanner reader) {
        String longURI = reader.next();
        String user = null;
        String id = null;
        int timeLimit = -1;
        int useLimit = -1;

        while (
            reader.hasNext(USER)
            || reader.hasNext(ID)
            || reader.hasNext(CONVERT_TIME_LIMIT)
            || reader.hasNext(CONVERT_USE_LIMIT)
        ) {
            try {
                switch (reader.next()) {
                case USER:
                    user = reader.next();
                    break;
                case ID:
                    id = reader.next();
                    break;
                case CONVERT_TIME_LIMIT:
                    timeLimit = reader.nextInt();
                    break;
                case CONVERT_USE_LIMIT:
                    useLimit = reader.nextInt();
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return new ConvertCommand(
            longURI,
            user,
            id,
            timeLimit,
            useLimit,
            new URIShortenerInteractor(userStorage)
        );
    }

    private OpenCommand createOpenCommand(Scanner reader) {
        String shortURI = reader.next();
        String user = null;
        String id = null;

        while (
            reader.hasNext(USER)
            || reader.hasNext(ID)
        ) {
            try {
                switch (reader.next()) {
                case USER:
                    user = reader.next();
                    break;
                case ID:
                    id = reader.next();
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }

        return new OpenCommand(
            shortURI,
            user,
            id,
            new URIShortenerInteractor(userStorage)
        );
    }
}
