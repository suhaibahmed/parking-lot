package com.gojek.parkinglot.dto;

public class Car {
    String registrationNumber;
    String color;

    public Car(String registrationNumber, String color){
        this.registrationNumber = registrationNumber;
        this.color=color;
    }

    @Override
    public String toString() {
        return "Car : " + registrationNumber + ", " + color;
    }
}
