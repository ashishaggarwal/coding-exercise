package com.wellknownorg.coding.challenge;

import com.wellknownorg.coding.challenge.date.MyDateFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MyDateFormatterTest {

    private MyDateFormatter unitUnderTest;

    @BeforeEach
    public void setUp() {
        unitUnderTest = new TestDateFormatter();
    }

    @Test
    public void shouldParseDateForTwentiethCentury_givenDateFormatIsCorrect() {
        assertThat(unitUnderTest.parse("01/02/90"), is(LocalDate.of(1990, 2, 1)));
        assertThat(unitUnderTest.parse("01/02/24"), is(LocalDate.of(1924, 2, 1)));
    }

    @Test
    public void shouldParseDateForTwentyFirstCentury_givenDateFormatIsCorrect() {
        assertThat(unitUnderTest.parse("01/02/23"), is(LocalDate.of(2023, 2, 1)));
        assertThat(unitUnderTest.parse("01/02/01"), is(LocalDate.of(2001, 2, 1)));
    }

    @Test
    public void shouldThrowException_givenDateFormatIsWrong() {
        assertThrows(DateTimeParseException.class,
                () -> unitUnderTest.parse("01-02-90"));
    }

    class TestDateFormatter implements MyDateFormatter {

    }

}