package com.wellknownorg.addressbook.mapper;

import com.wellknownorg.addressbook.date.DateFormatter;
import com.wellknownorg.addressbook.model.Gender;
import com.wellknownorg.addressbook.model.Person;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static org.springframework.util.StringUtils.hasText;

@Component
public record PersonMapper(DateFormatter dateFormatter) {

    public Person mapToPerson(String[] data) {
        return Person.builder()
                .name(nullIfBlank(data[0], s -> s))
                .gender(nullIfBlank(data[1], Gender::from))
                .dateOfBirth(nullIfBlank(data[2], dateFormatter::parse))
                .build();
    }

    private <R> R nullIfBlank(String data, Function<String, R> function) {
        return hasText(data) ? function.apply(data.trim()) : null;
    }
}
