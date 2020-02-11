package com.gojek.parkinglot.Executor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SingleCommandProcessor extends CommandProcessor {
    public void process() throws IOException {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String inputString = null;
            inputString = bufferRead.readLine();
            processInput(inputString);
        }
    }
}
