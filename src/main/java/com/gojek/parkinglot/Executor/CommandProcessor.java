package com.gojek.parkinglot.Executor;

import com.gojek.parkinglot.constants.Command;
import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.service.ParkingService;

public abstract class CommandProcessor {

    ParkingService parkingService = null;

    public void processInput(String inputString){
        String[] inputStringArray = inputString.split(" ");
        if(inputStringArray[0].equals("exit")){
            System.out.println("Exiting ...");
            System.exit(0);
        }
        Command command = Command.getCommandByString(inputStringArray[0]);

        if(command==null){
            System.out.println("Invalid command");
            return;
        }
        else if(!command.isValidArgumentLength(inputStringArray)){
            System.out.println("Invalid no. of arguments for command : " + command);
        }

        switch(command){
            case EXIT:
                System.exit(0);
                break;
            case CREATE_PARKING_LOT:
                parkingService = ParkingService.createParkingService(inputStringArray[0]);
                break;
            case PARK_VEHICLE:
                parkingService.park_vehicle(new Car(inputStringArray[1], inputStringArray[2]));
                break;
            case LEAVE:
                Integer slotNumber = Integer.parseInt(inputStringArray[1]);
                parkingService.remove_vehicle(slotNumber);
                break;
            case STATUS:
                parkingService.getStatus();
                break;
            case GET_REGISTRATION_NUMBER_BY_COLOR:
                parkingService.getRegistrationNumbersFromColor(inputStringArray[1]);
                break;
            case GET_SLOT_BY_REGISTRATION_NUMBER:
                parkingService.getSlotNumberFromRegistrationNumber(inputStringArray[1]);
                break;
            case GET_SLOTS_BY_COLOR:
                parkingService.getSlotNumbersFromColor(inputStringArray[1]);
                break;

        }
    }

    private Integer parseCapacity(String input){
        try{
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            return -1;
        }
    }

}
