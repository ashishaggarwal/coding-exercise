package com.wellknownorg.coding.challenge.mapper;

import com.wellknownorg.coding.challenge.date.MyDateFormatter;
import com.wellknownorg.coding.challenge.model.Gender;
import com.wellknownorg.coding.challenge.model.Person;

import java.time.LocalDate;

import static org.springframework.util.StringUtils.hasLength;

public record PersonMapper(MyDateFormatter dateFormatter) {

    public Person mapToPerson(String[] data) {
        LocalDate dateOfBirth = dateFormatter.parse(data[2]);
        return Person.builder()
                .name(nullIfBlank(data[0]))
                .gender(Gender.from(data[1]))
                .dateOfBirth(dateOfBirth)
                .build();
    }

    private String nullIfBlank(String data) {
        return hasLength(data) && hasLength(data.trim()) ? data.trim() : null;
    }
}
