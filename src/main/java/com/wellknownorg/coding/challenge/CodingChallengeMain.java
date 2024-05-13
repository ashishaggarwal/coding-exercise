package com.wellknownorg.coding.challenge;

import com.wellknownorg.coding.challenge.file.read.FileReader;
import com.wellknownorg.coding.challenge.processor.PersonProcessor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CodingChallengeMain {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private PersonProcessor personProcessor;

    @PostConstruct
    public void run() {
        fileReader.readFile("input.csv");
        System.out.println(personProcessor.countMales());
        System.out.println(personProcessor.findOldestPerson());
        System.out.println(personProcessor.calculateAgeDiff("Bill McKnight", "Paul Robinson"));
    }

    public static void main (String [] args) {
        SpringApplication.run(CodingChallengeMain.class, args);
    }
}
