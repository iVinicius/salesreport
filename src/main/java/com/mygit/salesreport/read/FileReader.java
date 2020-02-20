package com.mygit.salesreport.read;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class FileReader {

    public String readFile(File inputFile){
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            return readFromInputStream(inputStream);
        } catch (IOException e) {
            log.error("Failed to read file.", e);
        }
        return null;
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = br.readLine()) != null){
                resultStringBuilder.append(line).append("\n");
            }
        }
        inputStream.close();
        return resultStringBuilder.toString();
    }
}
