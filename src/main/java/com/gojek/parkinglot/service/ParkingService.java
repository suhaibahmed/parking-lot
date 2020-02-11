package com.gojek.parkinglot.service;

import com.gojek.parkinglot.Interfaces.IParkingService;
import com.gojek.parkinglot.dto.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ParkingService implements IParkingService {

    private Integer capacity;
    private Map<String, List<Car>> colorToRegNo;
    private Map<String, Integer> regNoToSlot;
    private Map<String, List<Integer>> colorToSlots;
    private PriorityQueue<Integer> availableSlot ;
    private static volatile ParkingService parkingService = null;



    private ParkingService(Integer capacity){
        this.capacity = capacity;
        availableSlot = new PriorityQueue<Integer>();
        for(int i=0;i<capacity;i++){
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
    public void park_vehicle(Car car) {
        if(parkingService==null){
            System.out.println("Sorry, parking lot is not created");
        }
    }

    @Override
    public void remove_vehicle(Integer slotNumber) {

    }

    @Override
    public void getStatus() {

    }

    @Override
    public void getRegistrationNumbersFromColor(String color) {

    }

    @Override
    public void getSlotNumbersFromColor(String color) {

    }

    @Override
    public void getSlotNumberFromRegistrationNumber(String registrationNumber) {

    }

    @Override
    public Integer getCapacity() {
        return this.capacity;
    }



}
