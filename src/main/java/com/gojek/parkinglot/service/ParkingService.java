package com.gojek.parkinglot.service;

import com.gojek.parkinglot.Interfaces.IParkingService;
import com.gojek.parkinglot.constants.ErrorCodes;
import com.gojek.parkinglot.dto.Car;

import java.util.*;

public class ParkingService implements IParkingService {

    private Integer capacity;
    private Map<String, HashSet<String>> colorToRegNo;
    private Map<String, Integer> regNoToSlot;
    private Map<String, HashSet<Integer>> colorToSlots;
    private Map<Integer, Car> slotToCar;
    private PriorityQueue<Integer> availableSlot ;
    private static volatile ParkingService parkingService = null;



    private ParkingService(Integer capacity){
        this.capacity = capacity;
        availableSlot = new PriorityQueue<Integer>();
        for(int i=1;i<=capacity;i++){
            availableSlot.add(i);
        }

        colorToRegNo = new HashMap<>();
        regNoToSlot = new HashMap<>();
        colorToSlots = new HashMap<>();
        slotToCar = new TreeMap<>();
        System.out.println("Created a parking lot with "+capacity+" slots");
    }


    public static ParkingService createParkingService(String capacityInput){

        Integer capacity = parseCapacity(capacityInput);
        if (capacity < 0) {
            System.out.println("Invalid capacity");
            return null;
        }

        if(parkingService==null)
        {
            synchronized(ParkingService.class){
                if(parkingService==null){
                    parkingService = new ParkingService(capacity);
                }
            }
            return parkingService;
        }
        else {
            System.out.println("Parking lot already created");
            return null;
        }


    }

    @Override
    public Integer park_vehicle(Car car) {
        Integer slotNumber;
        if(parkingService==null){
            return ErrorCodes.PARKING_LOT_NOT_CREATED;
        }
        else if(availableSlot.size()==0){
            return ErrorCodes.PARKING_FULL;
        }
        else if(regNoToSlot.containsKey(car.getRegistrationNumber())){
            return ErrorCodes.CAR_ALREADY_PARKED;
        }
        slotNumber = availableSlot.poll();
        slotToCar.put(slotNumber, car);


        if(colorToRegNo.containsKey(car.getColor())){
            colorToRegNo.get(car.getColor()).add(car.getRegistrationNumber());
        }
        else
        {
            colorToRegNo.put(car.getColor(), new HashSet<>(Arrays.asList(car.getRegistrationNumber())));
        }

        regNoToSlot.put(car.getRegistrationNumber(), slotNumber);
        if(colorToSlots.containsKey(car.getColor())){
            colorToSlots.get(car.getColor()).add(slotNumber);
        }
        else{
            colorToSlots.put(car.getColor(), new HashSet<>(Arrays.asList(slotNumber)));
        }
        return slotNumber;

    }

    @Override
    public Integer remove_vehicle(Integer slotNumber) {
        if(slotNumber<1 || slotNumber>capacity){
            return ErrorCodes.INVALID_SLOT_NUMBER;
        }
        else if(!slotToCar.containsKey(slotNumber)){
            return ErrorCodes.SLOT_EMPTY;
        }

        Car removedCar = slotToCar.get(slotNumber);
        slotToCar.remove(slotNumber);
        colorToRegNo.get(removedCar.getColor()).remove(removedCar.getRegistrationNumber());
        regNoToSlot.remove(removedCar.getRegistrationNumber());
        colorToSlots.get(removedCar.getColor()).remove(slotNumber);

        return 1;
    }



    @Override
    public Map<Integer, Car> getStatus() {
        return slotToCar;
    }

    @Override
    public HashSet<String> getRegistrationNumbersFromColor(String color) {
        return colorToRegNo.get(color);
    }

    @Override
    public HashSet<Integer> getSlotNumbersFromColor(String color) {
        return colorToSlots.get(color);
    }

    @Override
    public Integer getSlotNumberFromRegistrationNumber(String registrationNumber) {
        if(regNoToSlot.containsKey(registrationNumber))
        {
            return regNoToSlot.get(registrationNumber);
        }
        else
            return ErrorCodes.REGISTRATION_NUMBER_ABSENT;
    }

    @Override
    public Integer getCapacity() {
        return this.capacity;
    }

    public static Integer parseCapacity(String input){
        try{
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            return -1;
        }
    }

    public static void clear()
    {
        if(parkingService!=null)
            parkingService = null;
    }

}
