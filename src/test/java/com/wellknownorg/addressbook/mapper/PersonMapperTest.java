package com.wellknownorg.addressbook.mapper;

import com.wellknownorg.addressbook.date.DateFormatter;
import com.wellknownorg.addressbook.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static com.wellknownorg.addressbook.model.Gender.FEMALE;
import static com.wellknownorg.addressbook.model.Gender.MALE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

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

    @Test
    public void shouldMapToPerson_givenNameIsNull() {
        String [] data = new String[] {null, "Male", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is(nullValue()));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }

    @Test
    public void shouldMapToPerson_givenGenderIsBlank() {
        String [] data = new String[] {"John", "", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John"));
        assertThat(person.gender(), is(nullValue()));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }

    @Test
    public void shouldMapToPerson_givenGenderHasOnlySpaces() {
        String [] data = new String[] {"John", "  ", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John"));
        assertThat(person.gender(), is(nullValue()));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }

    @Test
    public void shouldMapToPerson_givenGenderIsNull() {
        String [] data = new String[] {"John", "  ", "01/02/90"};
        LocalDate dateOfBirth = LocalDate.of(1990, 2, 1);
        when(dateFormatter.parse("01/02/90")).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John"));
        assertThat(person.gender(), is(nullValue()));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
    }

    @Test
    public void shouldMapToPerson_givenDateOfBirthIsBlank() {
        String [] data = new String[] {"John", "Male", ""};

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John"));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(nullValue()));
        verifyNoInteractions(dateFormatter);
    }

    @Test
    public void shouldMapToPerson_givenDateOfBirthHasOnlySpaces() {
        String [] data = new String[] {"John", "Male", "  "};

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John"));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(nullValue()));
        verifyNoInteractions(dateFormatter);
    }

    @Test
    public void shouldMapToPerson_givenDateOfBirthIsNull() {
        String [] data = new String[] {"John", "Male", null};

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("John"));
        assertThat(person.gender(), is(MALE));
        assertThat(person.dateOfBirth(), is(nullValue()));
        verifyNoInteractions(dateFormatter);
    }

    @Test
    public void shouldMapToPerson_givenFullData() {
        String dateOfBirthString = "02/04/10";
        String [] data = new String[] {"Tom", "Female", dateOfBirthString};
        LocalDate dateOfBirth = LocalDate.of(2010, 4, 2);
        when(dateFormatter.parse(dateOfBirthString)).thenReturn(dateOfBirth);

        Person person = unitUnderTest.mapToPerson(data);

        assertThat(person.name(), is("Tom"));
        assertThat(person.gender(), is(FEMALE));
        assertThat(person.dateOfBirth(), is(dateOfBirth));
        verify(dateFormatter).parse(dateOfBirthString);
    }
}