package com.gojek.parkinglot.constants;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestCommand {

    @Test
    public void testCommandNull(){
        Command command = Command.getCommandByString("invalidinput");
        assertTrue(command==null);
    }

    @Test
    public void testCommandCorrect(){
        Command command = Command.getCommandByString("create_parking_lot");
        assertTrue(command==Command.CREATE_PARKING_LOT);
    }

    @Test
    public void testCommandArgumentCountCorrect(){
        String[] testInputArray = "create_parking_lot 7".split(" ");
        Command command = Command.getCommandByString(testInputArray[0]);
        assertTrue(command.isValidArgumentLength(testInputArray));
    }

    @Test
    public void testCommandArgumentCountIncorrect(){
        String[] testInputArray = "create_parking_lot 7 2".split(" ");
        Command command = Command.getCommandByString(testInputArray[0]);
        assertFalse(command.isValidArgumentLength(testInputArray));
    }


}
