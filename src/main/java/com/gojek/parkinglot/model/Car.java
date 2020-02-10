package com.gojek.parkinglot.model;

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
