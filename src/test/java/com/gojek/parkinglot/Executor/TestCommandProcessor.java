package com.gojek.parkinglot.Executor;

import com.gojek.parkinglot.constants.ErrorCodes;
import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.service.ParkingService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCommandProcessor {

    static ParkingService parkingService = null;

    @Before
    public  void init(){

        ParkingService.clear();
        parkingService = ParkingService.createParkingService("5");

    }

    @Test
    public void testNegativeCapacity(){
        assertTrue(ParkingService.createParkingService("-1")==null);
    }

    @Test
    public void testInvalidCapacity(){
        assertTrue(ParkingService.createParkingService("garblegarble")==null);
    }

    @Test
    public void testCapacity(){
        assertTrue(parkingService.getCapacity()==5);
    }

    @Test
    public void testParkVehicleValid(){
        Integer slotNumber = parkingService.park_vehicle(new Car("testRegistrationNumber","white"));
        assertTrue(slotNumber==1);

    }

    @Test
    public void testParkVehicleExisting(){
        parkingService.park_vehicle(new Car("testRegistrationNumber","white"));
        assertTrue(parkingService.park_vehicle(new Car("testRegistrationNumber","white"))== ErrorCodes.CAR_ALREADY_PARKED);
    }

    @Test
    public void testParkingFull(){
        parkingService.park_vehicle(new Car("testRegistrationNumber1","white"));
        parkingService.park_vehicle(new Car("testRegistrationNumber2","white"));
        parkingService.park_vehicle(new Car("testRegistrationNumber3","white"));
        parkingService.park_vehicle(new Car("testRegistrationNumber4","white"));
        parkingService.park_vehicle(new Car("testRegistrationNumber5","white"));
        assertTrue(parkingService.park_vehicle(new Car("testRegistrationNumber6","white"))== ErrorCodes.PARKING_FULL);
    }

    @Test
    public void testRemoveCar(){
        Integer slot1 = parkingService.park_vehicle(new Car("testRegistrationNumber1","white"));
        Integer slot2 = parkingService.park_vehicle(new Car("testRegistrationNumber2","red"));
        Integer slot3 = parkingService.park_vehicle(new Car("testRegistrationNumber3","blue"));
        Integer slot4 = parkingService.park_vehicle(new Car("testRegistrationNumber4","green"));
        Integer slot5 = parkingService.park_vehicle(new Car("testRegistrationNumber5","purple"));

        assertTrue(parkingService.remove_vehicle(3)==3);

    }

    @Test
    public void testRemoveCarSlotEmpty(){
        Integer slot1 = parkingService.park_vehicle(new Car("testRegistrationNumber1","white"));
        assertTrue(parkingService.remove_vehicle(3) == ErrorCodes.SLOT_EMPTY);
    }

    @Test
    public void testRemoveCarOutOfRangeSlotNumber(){
        assertTrue(parkingService.remove_vehicle(100)==ErrorCodes.INVALID_SLOT_NUMBER);
        assertTrue(parkingService.remove_vehicle(-1)==ErrorCodes.INVALID_SLOT_NUMBER);
    }

    @Test
    public void testGetStatus(){
        Car car = new Car("testRegistrationNumber1","white");
        parkingService.park_vehicle(car);
        assertTrue(parkingService.getStatus().containsValue(car));
        assertTrue(parkingService.getStatus().containsKey(1));
    }

    @Test
    public void testGetRegistrationNumbersFromColor(){
        parkingService.park_vehicle(new Car("testRegistrationNumber1","white"));
        assertTrue(parkingService.getRegistrationNumbersFromColor("white").contains("testRegistrationNumber1"));
    }

    @Test
    public void testGetSlotNumbersFromColor(){
        parkingService.park_vehicle(new Car("testRegistrationNumber1","white"));
        parkingService.park_vehicle(new Car("testRegistrationNumber2","red"));
        assertTrue(parkingService.getSlotNumbersFromColor("red").contains(2));
    }

    @Test
    public void testGetSlotNumberFromRegistrationNumber(){
        Integer slot1 = parkingService.park_vehicle(new Car("testRegistrationNumber1","white"));
        Integer slot2 = parkingService.park_vehicle(new Car("testRegistrationNumber2","red"));
        Integer slot3 = parkingService.park_vehicle(new Car("testRegistrationNumber3","blue"));
        Integer slot4 = parkingService.park_vehicle(new Car("testRegistrationNumber4","green"));
        Integer slot5 = parkingService.park_vehicle(new Car("testRegistrationNumber5","purple"));

        assertTrue(parkingService.getSlotNumberFromRegistrationNumber("testRegistrationNumber5")==5);
    }

    @Test
    public void checkAbsentRegistrationNumber()
    {
        assertTrue(parkingService.getSlotNumberFromRegistrationNumber("testRegistrationNumber5")==ErrorCodes.REGISTRATION_NUMBER_ABSENT);
    }




}
