package com.task1.task1API.generator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/")
public class taskGenerationController {

    @Autowired
    private taskGenerationDAOService service;

    @PostMapping(value = "generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTask(@RequestBody taskGenerationModel newTask) {
       return service.generateTask(newTask);
    }

    @GetMapping(value = "tasks/{UUID}/status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTask( @PathVariable(value = "UUID", required = true) Integer UUID,
                                      @RequestParam(value = "action", required = false) String action){
        return service.getTask(UUID, action);
    }

}
