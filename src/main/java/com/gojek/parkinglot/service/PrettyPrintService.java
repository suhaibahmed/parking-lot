package com.gojek.parkinglot.service;

import com.gojek.parkinglot.constants.ErrorCodes;
import com.gojek.parkinglot.dto.Car;

import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class PrettyPrintService {
    public  void printParkingLotNotCreated() {
        System.out.println("Parking lot not created yet");
    }

    public void printParkingLotCreation(ParkingService parkingService) {
        System.out.println("Created a parking lot with "+parkingService.getCapacity()+" slots");
    }

    public void printParkVehicle(Integer parkedSlot) {
        if(parkedSlot<0){
            if(parkedSlot.equals(ErrorCodes.PARKING_FULL))
                System.out.println("Sorry, parking lot is full");
            else if(parkedSlot.equals(ErrorCodes.CAR_ALREADY_PARKED))
                System.out.println("Car already parked");
        }else{
            System.out.println("Allocated slot number: " + parkedSlot);
        }
    }

    public void printRemoveVehicle(Integer freeSlot) {
        if(freeSlot<0){
            if(freeSlot.equals(ErrorCodes.INVALID_SLOT_NUMBER)){
                System.out.println("Invalid slot number");
            }
            else if(freeSlot.equals(ErrorCodes.SLOT_EMPTY)){
                System.out.println("Slot already empty");
            }
        }
        else{
            System.out.println("Slot number "+freeSlot+" is free ");
        }

    }

    public void printStatus(Map<Integer, Car> allcars) {
        System.out.println("Slot No.\tRegistration No.\tColor");
        for(Map.Entry<Integer, Car> entry: allcars.entrySet()){
            System.out.println(entry.getKey() + "\t\t\t" + entry.getValue().getRegistrationNumber() + "\t\t"+entry.getValue().getColor());
        }
    }


    public void printCSV(HashSet<?> regNumbers) {
        if(regNumbers.isEmpty()){
            System.out.println("Parking lot empty");
        }
        else{
            String regNosCommaSeparated = regNumbers.stream()
                    .map(item-> String.valueOf(item))
                    .collect(Collectors.joining(", "));
            System.out.println(regNosCommaSeparated);
        }

    }


    public void printSlotNumber(int slotNumberByRegistrationNumber) {
        if(slotNumberByRegistrationNumber<0){
            if(slotNumberByRegistrationNumber == ErrorCodes.REGISTRATION_NUMBER_ABSENT){
                System.out.println("Not found");
            }
        }
        else
            System.out.println(slotNumberByRegistrationNumber);
    }
}
