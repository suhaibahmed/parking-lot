package com.gojek.parkinglot.Interfaces;

import com.gojek.parkinglot.dto.Car;

import java.util.List;

public interface IParkingService {

    public Integer park_vehicle(Car car);
    public Boolean remove_vehicle(Integer slotNumber);
    public String getStatus();
    public List<String> getRegistrationNumbersFromColor(String color);
    public List<Integer> getSlotNumbersFromColor(String color);
    public Integer getSlotNumberFromRegistrationNumber(String registrationNumber);
    public Integer getCapacity();
}
