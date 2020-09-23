package com.application.service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileFilterServiceTest {

    private String result = "paul\n" +
            "is\n" +
            "here\n" +
            "hello\n" +
            "world\n" +
            "hello\n" +
            "again\n" +
            "banana\n" +
            "123";
    @Test
    public  void testFileFilter() throws IOException {
        FileFilterService fileFilterService = new FileFilterService();
        fileFilterService.filterFile("src/test/java/resources/input.txt","banned", "outputTest.txt");
        Path path = Paths.get("outputTest.txt");
        String read = Files.readAllLines(path).stream().collect(Collectors.joining("\n"));
        assertEquals(read, result);
    }
}