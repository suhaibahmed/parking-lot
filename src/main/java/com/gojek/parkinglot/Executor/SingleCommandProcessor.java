package com.gojek.parkinglot.Executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SingleCommandProcessor extends CommandProcessor {
    public void process() throws Exception {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String inputString = bufferRead.readLine();
            processInput(inputString);
        }
    }
}
