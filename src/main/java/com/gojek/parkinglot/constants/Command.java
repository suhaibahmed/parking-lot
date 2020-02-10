package com.gojek.parkinglot.constants;

public enum Command {

    CREATE_PARKING_LOT("create_parking_lot"),
    PARK_VEHICLE("park"),
    LEAVE("leave"),
    STATUS("status"),
    GET_REGISTRATION_NUMBER_BY_COLOR("registration_numbers_for_cars_with_colour"),
    GET_SLOTS_BY_COLOR("slot_numbers_for_cars_with_colour"),
    GET_SLOT_BY_REGISTRATION_NUMBER("slot_number_for_registration_number"),
    EXIT("exit");

    private final String commandName;
    Command(String commandName){
        this.commandName = commandName;
    }

    public static Command getCommandByString(String input){
        for(Command command : values()){
            if(command.commandName.equals(input)) {
                return command;
            }
        }
        return null;
    }
}
