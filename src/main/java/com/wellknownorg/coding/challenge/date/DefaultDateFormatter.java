package com.wellknownorg.coding.challenge.date;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static java.time.temporal.ChronoField.YEAR;

@Component
public class DefaultDateFormatter implements DateFormatter {

    public LocalDate parse(String dateString) {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendPattern("dd/MM/")
                .optionalStart()
                .appendValueReduced(YEAR, 2, 2, LocalDate.now().getYear() - 100)
                .optionalEnd()
                .toFormatter();
        return LocalDate.from(dateTimeFormatter.parse(dateString));
    }
}
