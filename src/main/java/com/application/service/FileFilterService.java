package com.application.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileFilterService {

    private static Pattern pattern;

    private static Matcher matcher;

    private static Logger LOGGER = Logger.getLogger(FileFilterService.class.getName());

    public void filterFile(String inputFilePath, String bannedWord, String outputFilePath)  {
        pattern = Pattern.compile(bannedWord);
        File file = new File(inputFilePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            FileWriter fw = new FileWriter(outputFilePath);
            while( (line=br.readLine())!=null) {
                matcher = pattern.matcher(line);
                if( !matcher.find()) {
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
