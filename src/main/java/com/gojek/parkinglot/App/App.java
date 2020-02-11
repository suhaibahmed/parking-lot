package com.gojek.parkinglot.App;


import com.gojek.parkinglot.Executor.CommandProcessor;
import com.gojek.parkinglot.Executor.FileProcessor;
import com.gojek.parkinglot.Executor.SingleCommandProcessor;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;


public class App {
    public static void main(String[] args) throws URISyntaxException {
        CommandProcessor processor = null;
        if(args.length>=1){
            processor = new FileProcessor(args[0]);
        }
        else{
            processor = new SingleCommandProcessor();
        }

        try {
            processor.process();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
