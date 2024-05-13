package com.wellknownorg.coding.challenge.model;

import org.junit.jupiter.api.Test;

import static com.wellknownorg.coding.challenge.model.Gender.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenderTest {

    @Test
    public void shouldMapGender() {
        assertThat(Gender.from("Male "), is(MALE));
        assertThat(Gender.from("Male"), is(MALE));
        assertThat(Gender.from("male"), is(MALE));
        assertThat(Gender.from("Female"), is(FEMALE));
        assertThat(Gender.from("female"), is(FEMALE));
        assertThat(Gender.from("Other"), is(OTHER));
        assertThat(Gender.from("other"), is(OTHER));
    }

    @Test
    public void shouldThrowExceptionForInvalidGenderDescription() {
        assertThrows(IllegalArgumentException.class,
                () -> Gender.from("some"));
    }

    @Test
    public void shouldThrowExceptionForNullDescription() {
        assertThrows(IllegalArgumentException.class,
                () -> Gender.from(null));
    }
}