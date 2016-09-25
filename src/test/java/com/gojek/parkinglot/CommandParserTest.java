package com.gojek.parkinglot;

import org.junit.Before;
import org.junit.Test;

public class CommandParserTest {

    private CommandParser commandParser;

    @Before
    public void setup() {
        commandParser = new CommandParser();
    }

    @Test
    public void shouldParseValidInputCommandToStandardType() {
        CommandParser.CommandType commandType = commandParser.parse("create_parking_lot");
        assert(commandType).equals(CommandParser.CommandType.create_parking_lot);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionInvalidInputCommand() {
        commandParser.parse("blah");
    }
}
