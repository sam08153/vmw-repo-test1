package com.task1.task1API.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class GeneratorService {

    @Value("${fileDir}")
    private String fileDir;

    @Async("threadPoolTaskExecutor")
    public void generateToFile(long goal, long step, UUID id) {
        String InProgress = fileDir + id+ Constant.FILE_IN_PROGRESS;
        File inProgressFile = new File(InProgress);
        String Complete = fileDir + id+ Constant.FILE_OUTPUT;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(InProgress));
            for (long i=goal;i>=0;i-=step) {
                out.write(String.valueOf(i));
                if (i > 0) {
                    String delimiter = ",";
                    out.write(delimiter);
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            String errorName = fileDir + id+ Constant.ERROR_FILE;
            File errorFile = new File(errorName);
            inProgressFile.renameTo(errorFile);
        } finally {
            File outputFile = new File(Complete);
            inProgressFile.renameTo(outputFile);
        }
    }


    public String readFromFile( UUID id) {
        String outputPath = fileDir + id+ Constant.FILE_OUTPUT;
        String content= "";
        try {
            content = Files.readString(Paths.get(outputPath));
        } catch (IOException e) {

        }
        return content;
    }

}
