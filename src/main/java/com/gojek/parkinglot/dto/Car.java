package com.gojek.parkinglot.dto;

public class Car {
    String registrationNumber;
    String color;

    public Car(String registrationNumber, String color){
        this.registrationNumber = registrationNumber;
        this.color=color;
    }
    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Car : " + registrationNumber + ", " + color;
    }
}
