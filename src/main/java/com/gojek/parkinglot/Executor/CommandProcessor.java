package com.gojek.parkinglot.Executor;

import com.gojek.parkinglot.Exeptions.InvalidCommandException;
import com.gojek.parkinglot.Interfaces.IParkingService;
import com.gojek.parkinglot.constants.Command;
import com.gojek.parkinglot.dto.Car;

public abstract class CommandProcessor {

    IParkingService parkingService = null;

    public void processInput(String inputString) throws Exception{
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


        switch(command){
            case EXIT:
                System.out.println("Exiting ...");
                System.exit(0);
                break;
            case CREATE_PARKING_LOT:
                if(inputStringArray.length!=2){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                int parkingLotCapacity = Integer.parseInt(inputStringArray[1]);
                parkingService = null;
                break;
            case PARK_VEHICLE:
                if(inputStringArray.length!=3){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                parkingService.park_vehicle(new Car(inputStringArray[1], inputStringArray[2]));
                break;
            case LEAVE:
                if(inputStringArray.length!=2){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                Integer slotNumber = Integer.parseInt(inputStringArray[1]);
                parkingService.remove_vehicle(slotNumber);
                break;
            case STATUS:
                if(inputStringArray.length!=1){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                parkingService.getStatus();
                break;
            case GET_REGISTRATION_NUMBER_BY_COLOR:
                if(inputStringArray.length!=2){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                parkingService.getRegistrationNumbersFromColor(inputStringArray[1]);
                break;
            case GET_SLOT_BY_REGISTRATION_NUMBER:
                if(inputStringArray.length!=2){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                parkingService.getSlotNumberFromRegistrationNumber(inputStringArray[1]);
                break;
            case GET_SLOTS_BY_COLOR:
                if(inputStringArray.length!=2){
                    System.out.println("Invalid no. of arguments for command : " + command);
                    return;
                }
                parkingService.getSlotNumbersFromColor(inputStringArray[1]);
                break;

        }
    }

}
