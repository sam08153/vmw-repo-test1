package com.task1.task1API.generator;

import com.fasterxml.uuid.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@Component
public class GeneratorFileUtil {

    @Autowired
    GeneratorService genService;

    @Value("${fileDir}")
    private String fileDir;



    public ResponseEntity generateTask(GenerationModel newTask)  {
        HashMap<String, String> map = new HashMap<>();
        if (newTask.getGoal() == Integer.MAX_VALUE) {
            map.put(Constant.ERROR_TEXT, Constant.ERROR_GOAL_LARGE);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (newTask.getGoal() == 0 || newTask.getStep() == 0) {
            map.put(Constant.ERROR_TEXT, Constant.ERROR_GOAL_STEP_ZERO);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (newTask.getStep() > newTask.getGoal()) {
            map.put(Constant.ERROR_TEXT, Constant.ERROR_GOAL_LESS_THEN_STEP);
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        UUID uuid1 = Generators.timeBasedGenerator().generate();
        String filepath = fileDir + uuid1 + Constant.FILE_IN_PROGRESS;
        File file = new File(filepath);
        try {
            boolean created = file.createNewFile();
        } catch (IOException e) {

        }
        genService.generateToFile(newTask.getGoal(), newTask.getStep(), uuid1);
        map.put(Constant.RESULT_TEXT, uuid1.toString());
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    public ResponseEntity getTask(UUID UUID, String action) {
        try {
            if (action.equals(Constant.NUM_LIST)) {
                return getResults(UUID);
            }
        } catch (Exception e) {
            return getTaskByUUID(UUID);
        }
        return resultNotFound();
    }

    public ResponseEntity getTaskByUUID(UUID UUID) {
        String filepath = fileDir + UUID;
        HashMap<String, String> map = new HashMap<>();
        String inProgressPath = filepath + Constant.FILE_IN_PROGRESS;
        String outPath = filepath + Constant.FILE_OUTPUT;
        if (new File(inProgressPath).exists()) {
            map.put(Constant.RESULT_TEXT, Constant.IN_PROGRESS_TEXT);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        } else if(new File(outPath).exists()){
            map.put(Constant.RESULT_TEXT, Constant.SUCCESS_TEXT);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        map.put(Constant.RESULT_TEXT, Constant.ERROR_TEXT);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    public ResponseEntity getResults(UUID UUID) {
        HashMap<String, String> map = new HashMap<>();
        String inProgressPath = fileDir + UUID + Constant.FILE_IN_PROGRESS;
        if (UUID == null) {
            resultNotFound();
        } else if (new File(inProgressPath).exists()) {
            map.put(Constant.RESULT_TEXT, Constant.IN_PROGRESS_TEXT);
            return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
        }
        String result = genService.readFromFile(UUID);
        map.put(Constant.RESULT_TEXT, result);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    public ResponseEntity resultNotFound() {
        HashMap<String, String> map = new HashMap<>();
        map.put(Constant.RESULT_TEXT, Constant.NOT_FOUND_RESULT);
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
