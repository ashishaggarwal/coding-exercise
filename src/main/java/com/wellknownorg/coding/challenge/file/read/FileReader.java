package com.wellknownorg.coding.challenge.file.read;

import com.wellknownorg.coding.challenge.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@Component
public record FileReader(PersonService personService) {

    public static final String COMMA_DELIMITER = ",";

    public void readFile(String fileName) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        if (resourceAsStream != null) {
            read(resourceAsStream);
        } else {
            log.warn("Not able to read file as it might not exist");
        }
    }

    private void read(InputStream resourceAsStream) {
        try (BufferedReader buffer = new BufferedReader(
                new InputStreamReader(resourceAsStream))) {

            String line;
            while ((line = buffer.readLine()) != null) {
                personService.add(line.split(COMMA_DELIMITER));
            }
        } catch (IOException e) {
            log.error("Error while reading file", e);
        }
    }
}
