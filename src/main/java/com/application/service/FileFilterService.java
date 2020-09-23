package com.application.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.logging.Logger;

@Service
public class FileFilterService {

    private static Logger LOGGER = Logger.getLogger(FileFilterService.class.getName());

    public void filterFile(String inputFilePath, String bannedWord, String outputFilePath)  {

        File file = new File(inputFilePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            FileWriter fw = new FileWriter(outputFilePath);
            while( (line=br.readLine())!=null) {
                if( !line.equals(bannedWord)) {
                    fw.append(line+'\n');
                }
            }
            br.close();
            fw.close();
        } catch (FileNotFoundException e) {
            LOGGER.severe(String.format("Input file %s not found", inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
