package com.gojek.parkinglot.constants;

public enum Command {

    CREATE_PARKING_LOT("create_parking_lot",2),
    PARK_VEHICLE("park",3),
    LEAVE("leave",2),
    STATUS("status",1),
    GET_REGISTRATION_NUMBER_BY_COLOR("registration_numbers_for_cars_with_colour",2),
    GET_SLOTS_BY_COLOR("slot_numbers_for_cars_with_colour",2),
    GET_SLOT_BY_REGISTRATION_NUMBER("slot_number_for_registration_number",2),
    EXIT("exit",1);

    private final String commandName;
    private final Integer totalNumberOfArguments;
    Command(String commandName, Integer totalNumberOfArguments){
        this.totalNumberOfArguments = totalNumberOfArguments;
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

    public boolean isValidArgumentLength(String[] inputStringArray){
        return inputStringArray.length == totalNumberOfArguments;
    }
}
