package com.gojek.parkinglot.Executor;

import java.io.*;

public class FileProcessor extends CommandProcessor {
    String path;
    public FileProcessor(String filePath){

        this.path = filePath;
    }

    public void process() throws IOException {
        File inputFile = new File(path);
        BufferedReader bufferedReader= new BufferedReader(new FileReader(path));
        String line;

        while((line=bufferedReader.readLine())!=null)
        {
            processInput(line);
        }

    }
}
