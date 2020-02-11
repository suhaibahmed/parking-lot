package com.gojek.parkinglot.Executor;

import com.gojek.parkinglot.constants.ErrorCodes;
import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.service.ParkingService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestCommandProcessor {

    static ParkingService parkingService = null;

    @Before
    public void init(){
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



}
