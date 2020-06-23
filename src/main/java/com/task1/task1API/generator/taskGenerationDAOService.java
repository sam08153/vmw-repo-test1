package com.task1.task1API.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class taskGenerationDAOService {

    @Autowired
    generatorPOJO PojoClass;

    public ResponseEntity generateTask(taskGenerationModel newTask) {
        HashMap<String, String> map = new HashMap<>();
        if (newTask.getGoal() == 0 || newTask.getStep() == 0 ) {
            String errMsg = String.format("Goal and Step cannot be zero");
            map.put("error", errMsg);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        String taskId = PojoClass.generate(newTask.getGoal(), newTask.getStep());
        map.put("task", taskId);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    public ResponseEntity getTask(int UUID, String action) {
        try {
            if (action.equals("get_numlist")) {
                return getResults(UUID);
            }
        } catch (Exception e) {
            return getTaskByUUID(UUID);
        }
        return resultNotFound();
    }

    public ResponseEntity getTaskByUUID(int UUID) {
        HashMap<String, String> map = new HashMap<>();
        //TODO : incomplete logic
        if (UUID == 1) {
            map.put("Status", "SUCCESS");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }

        if (UUID == 2) {
            map.put("Status", "IN_PROGRESS");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }

        if (UUID == 3) {
            map.put("Status", "ERROR");
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        return resultNotFound();
    }

    public ResponseEntity getResults(int UUID) {
        HashMap<String, String> map = new HashMap<>();
        if (UUID == 0) {
            resultNotFound();
        }
        //TODO : incomplete logic
        map.put("Result", "10,8,6,4,2,0");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    public ResponseEntity resultNotFound() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Error", "No Results Found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
