package com.gojek.parkinglot.service;

import com.gojek.parkinglot.Interfaces.IParkingService;
import com.gojek.parkinglot.constants.ErrorCodes;
import com.gojek.parkinglot.dto.Car;

import java.util.*;

public class ParkingService implements IParkingService {

    private Integer capacity;
    private Map<String, List<String>> colorToRegNo;
    private Map<String, Integer> regNoToSlot;
    private Map<String, List<Integer>> colorToSlots;
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
        System.out.println("Created a parking lot with "+capacity+" slots");
    }

    private static Integer parseCapacity(String input){
        try{
            return Integer.parseInt(input);
        }catch(NumberFormatException e){
            return -1;
        }
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
        colorToRegNo.getOrDefault(car.getColor(), new ArrayList<>()).add(car.getRegistrationNumber());
        regNoToSlot.put(car.getRegistrationNumber(), slotNumber);
        colorToSlots.getOrDefault(car.getColor(), new ArrayList<>()).add(slotNumber);
        return slotNumber;

    }

    @Override
    public Boolean remove_vehicle(Integer slotNumber) {
        return true;
    }

    @Override
    public String getStatus() {
        String status = "";
        return status;
    }

    @Override
    public List<String> getRegistrationNumbersFromColor(String color) {
        List<String> registrationNumbers = null;
        return registrationNumbers;
    }

    @Override
    public List<Integer> getSlotNumbersFromColor(String color) {
        List<Integer> slotNumbers = null;
        return slotNumbers;
    }

    @Override
    public Integer getSlotNumberFromRegistrationNumber(String registrationNumber) {
        Integer slotNumber = null;
        return slotNumber;
    }

    @Override
    public Integer getCapacity() {
        return this.capacity;
    }



}
