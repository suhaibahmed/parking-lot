package com.gojek.parkinglot.Executor;

import com.gojek.parkinglot.constants.Command;
import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.service.ParkingService;
import com.gojek.parkinglot.service.PrettyPrintService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class CommandProcessor {

    ParkingService parkingService = null;
    PrettyPrintService printService = null;

    public void processInput(String inputString){

        String[] inputStringArray = inputString.split(" ");
        if(inputStringArray[0].equals("exit")){
            System.out.println("Exiting ...");
            System.exit(0);
        }
        Command command = Command.getCommandByString(inputStringArray[0]);

        printService = new PrettyPrintService();

        if(command==null){
            System.out.println("Invalid command");
            return;
        }
        else if(!command.isValidArgumentLength(inputStringArray)){
            System.out.println("Invalid no. of arguments for command : " + command);
        }

        if(command!=Command.EXIT && command!=Command.CREATE_PARKING_LOT && parkingService==null){
            printService.printParkingLotNotCreated();
            return;
        }

        switch(command){
            case EXIT:
                System.exit(0);
                break;
            case CREATE_PARKING_LOT:
                parkingService = ParkingService.createParkingService(inputStringArray[1]);
                printService.printParkingLotCreation(parkingService);
                break;
            case PARK_VEHICLE:
                Integer parkedSlot = parkingService.park_vehicle(new Car(inputStringArray[1], inputStringArray[2]));
                printService.printParkVehicle(parkedSlot);
                break;
            case LEAVE:
                Integer slotNumber = parkingService.parseCapacity(inputStringArray[1]);
                Integer freeSlot = parkingService.remove_vehicle(slotNumber);
                printService.printRemoveVehicle(freeSlot);
                break;
            case STATUS:
                Map<Integer, Car> allcars = parkingService.getStatus();
                printService.printStatus(allcars);
                break;
            case GET_REGISTRATION_NUMBER_BY_COLOR:
                HashSet<String> regNumbers = parkingService.getRegistrationNumbersFromColor(inputStringArray[1]);
                printService.printCSV(regNumbers);
                break;
            case GET_SLOT_BY_REGISTRATION_NUMBER:
                Integer slotNumberByRegistrationNumber = parkingService.getSlotNumberFromRegistrationNumber(inputStringArray[1]);
                printService.printSlotNumber(slotNumberByRegistrationNumber);
                break;
            case GET_SLOTS_BY_COLOR:
                HashSet<Integer> slotNumbers = parkingService.getSlotNumbersFromColor(inputStringArray[1]);
                printService.printCSV(slotNumbers);
                break;

        }
    }

    public abstract void process() throws IOException;


}
