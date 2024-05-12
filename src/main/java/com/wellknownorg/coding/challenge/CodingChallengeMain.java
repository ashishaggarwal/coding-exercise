package com.wellknownorg.coding.challenge;

import com.wellknownorg.coding.challenge.file.FileReader;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public record CodingChallengeMain(FileReader fileReader) {

    @PostConstruct
    public void run() {
        fileReader.readFile("input.csv");
    }

    public static void main (String [] args) {
        SpringApplication.run(CodingChallengeMain.class, args);
    }
}
