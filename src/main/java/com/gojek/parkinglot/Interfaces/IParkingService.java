package com.gojek.parkinglot.Interfaces;

import com.gojek.parkinglot.dto.Car;

public interface IParkingService {

    public void park_vehicle(Car car);
    public void remove_vehicle(Integer slotNumber);
    public void getStatus();
    public void getRegistrationNumbersFromColor(String color);
    public void getSlotNumbersFromColor(String color);
    public void getSlotNumberFromRegistrationNumber(String registrationNumber);
    public Integer getCapacity();
}
