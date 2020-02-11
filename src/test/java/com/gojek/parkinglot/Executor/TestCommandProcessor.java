package com.gojek.parkinglot.Executor;

import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.service.ParkingService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestCommandProcessor {

    static ParkingService parkingService = null;

    @BeforeClass
    public static void init(){
        parkingService = ParkingService.createParkingService("20");
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
        assertTrue(parkingService.getCapacity()==20);
    }

    @Test
    public void parkVehicle(){
        parkingService.park_vehicle(new Car("KA20","white") );
    }



}
