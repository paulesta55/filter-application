package com.application;

import com.application.configuration.AppConfiguration;
import com.application.service.FileFilterService;
import org.apache.commons.cli.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;


public class Main {
    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOGGER.info("FILTER APPLICATION RUNNING");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

        Options options = new Options();

        Option word = new Option("w", "word", true, "filtered word");
        word.setRequired(true);
        options.addOption(word);

        Option input = new Option("i", "input", true, "input file");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            String bannedWord = cmd.getOptionValue("word");
            String inputFilePath = cmd.getOptionValue("input");
            String outputFilePath = cmd.getOptionValue("output");
            FileFilterService fileFilterService = applicationContext.getBean(FileFilterService.class);
            fileFilterService.filterFile(inputFilePath, bannedWord, outputFilePath);
        } catch (ParseException e) {
            formatter.printHelp("filter-application", options);
            System.exit(1);
        }

    }
}