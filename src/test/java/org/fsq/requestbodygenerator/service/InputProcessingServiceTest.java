package org.fsq.requestbodygenerator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InputProcessingServiceTest {
    @Autowired
    private InputProcessingService inputProcessingService;

    @Test
    public void testGivenValidInputProducesExpectedOutput() {
        String in =
                "private int a;" + System.lineSeparator() +
                "private int b;" + System.lineSeparator() +
                "private String testStr;" + System.lineSeparator() +
                "private List<String> testStrList;" + System.lineSeparator() +
                "private SomeClass someClass;";
    }
}
