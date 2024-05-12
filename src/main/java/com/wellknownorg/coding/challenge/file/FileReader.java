package com.wellknownorg.coding.challenge.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Slf4j
@Component
public record FileReader(FileParser fileParser) {

    public void readFile(String fileName) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (resourceAsStream != null) {
            read(resourceAsStream);
        }
    }

    private void read(InputStream resourceAsStream) {
        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(resourceAsStream))) {

            String line;
            while ((line = buffer.readLine()) != null) {
                fileParser.parse(line);
            }
        } catch (IOException e) {
            log.error("Error while reading file", e);
        }
    }
}
