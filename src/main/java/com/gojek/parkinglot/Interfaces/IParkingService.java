package com.gojek.parkinglot.Interfaces;

import com.gojek.parkinglot.dto.Car;

import java.util.HashSet;
import java.util.Map;

public interface IParkingService {

    public Integer park_vehicle(Car car);
    public Integer remove_vehicle(Integer slotNumber);
    public Map<Integer, Car> getStatus();
    public HashSet<String> getRegistrationNumbersFromColor(String color);
    public HashSet<Integer> getSlotNumbersFromColor(String color);
    public Integer getSlotNumberFromRegistrationNumber(String registrationNumber);
    public Integer getCapacity();
}
