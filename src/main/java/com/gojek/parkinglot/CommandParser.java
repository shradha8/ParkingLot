package com.gojek.parkinglot;

public class CommandParser {

    public enum CommandType {
        create_parking_lot,
        park,
        leave,
        status,
        registration_numbers_for_cars_with_colour,
        slot_numbers_for_cars_with_colour,
        slot_number_for_registration_number
    }

    public CommandType parse(String input) {
        return CommandType.valueOf(input);
    }
}
