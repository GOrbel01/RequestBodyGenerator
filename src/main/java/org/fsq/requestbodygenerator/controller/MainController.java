package org.fsq.requestbodygenerator.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.fsq.requestbodygenerator.service.InputProcessingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/requestgen")
public class MainController {

    private InputProcessingService inputProcessingService;

    public MainController(InputProcessingService inputProcessingService) {
        this.inputProcessingService = inputProcessingService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode buildRequest(@RequestBody String fields) {
       return inputProcessingService.processInput(fields);
    }
}
