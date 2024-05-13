package com.wellknownorg.coding.challenge.mapper;

import com.wellknownorg.coding.challenge.date.DateFormatter;
import com.wellknownorg.coding.challenge.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.wellknownorg.coding.challenge.model.Gender.MALE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonMapperTest {

    @Mock
    private DateFormatter dateFormatter;

    private PersonMapper unitUnderTest;

    @BeforeEach
    public void setUp() {
        unitUnderTest = new PersonMapper(dateFormatter);
    }

    @Test
    public void shouldMapToPerson() {
        String [] data = new String[] {"John Smith", "Male", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John Smith"));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }

    @Test
    public void shouldMapToPerson_givenNameIsBlank() {
        String [] data = new String[] {"", "Male", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is(nullValue()));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }

    @Test
    public void shouldMapToPerson_givenNameHasOnlySpaces() {
        String [] data = new String[] {" ", "Male", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is(nullValue()));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }
}