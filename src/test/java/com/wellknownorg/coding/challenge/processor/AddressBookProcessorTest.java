package com.wellknownorg.coding.challenge.processor;

import com.wellknownorg.coding.challenge.model.Gender;
import com.wellknownorg.coding.challenge.model.Person;
import com.wellknownorg.coding.challenge.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static com.wellknownorg.coding.challenge.model.Gender.*;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookProcessorTest {

    @Mock
    private PersonRepository personRepository;

    private AddressBookProcessor unitUnderTest;

    @BeforeEach
    public void setUp() {
        unitUnderTest = new AddressBookProcessor(personRepository);
    }

    @Test
    public void shouldReturnZeroForMalesCount_givenNoPersonRecords() {
        when(personRepository.getPersons()).thenReturn(null);

        int result = unitUnderTest.countMales();

        assertThat(result, is(0));
    }

    @Test
    public void shouldReturnMalesCount_givenPersonRecordsExist() {
        when(personRepository.getPersons()).thenReturn(List.of(
                newPerson("John", MALE, LocalDate.now().minusYears(20)),
                newPerson("Smith", MALE, LocalDate.now().minusYears(30)),
                newPerson("Darren", MALE, LocalDate.now().minusYears(35)),
                newPerson("Samantha", FEMALE, LocalDate.now().minusYears(40)),
                newPerson("Lucy", FEMALE, LocalDate.now().minusYears(25)),
                newPerson("Tom", OTHER, LocalDate.now().minusYears(45))
        ));

        int result = unitUnderTest.countMales();

        assertThat(result, is(3));
    }

    @Test
    public void shouldReturnZero_givenNoPersonRecords() {
        when(personRepository.getPersons()).thenReturn(emptyList());

        int result = unitUnderTest.countMales();

        assertThat(result, is(0));
    }

    @Test
    public void shouldCalculateOldestPerson_givenPersonRecordsExist() {
        Person oldestPerson = newPerson("Tom", OTHER, LocalDate.now().minusYears(45));
        when(personRepository.getPersons()).thenReturn(List.of(
                newPerson("John", MALE, LocalDate.now().minusYears(20)),
                newPerson("Smith", MALE, LocalDate.now().minusYears(30)),
                newPerson("Darren", MALE, LocalDate.now().minusYears(35)),
                newPerson("Samantha", FEMALE, LocalDate.now().minusYears(40)),
                newPerson("Lucy", FEMALE, LocalDate.now().minusYears(25)),
                oldestPerson
        ));

        Person result = unitUnderTest.findOldestPerson();

        assertThat(result, is(oldestPerson));
    }

    @Test
    public void shouldThrowExceptionWhileCalculatingAgeDifference_givenNoPersonRecords() {
        when(personRepository.getPersons()).thenReturn(emptyList());

        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.calculateAgeDiff("Tom", "John"));
    }

    @Test
    public void shouldThrowExceptionWhileCalculatingAgeDifference_givenOnePersonDoesNotExist() {
        when(personRepository.getPersons()).thenReturn(List.of(
                newPerson("John", MALE, LocalDate.now().minusYears(20))
        ));
        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.calculateAgeDiff("Tom", "John"));
    }

    @Test
    public void shouldThrowExceptionWhileCalculatingAgeDifference_givenOnePersonDobDoesNotExist() {
        when(personRepository.getPersons()).thenReturn(List.of(
                newPerson("John Smith", MALE, LocalDate.now().minusYears(20)),
                newPerson("Barry Michaels", MALE, null)
        ));
        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.calculateAgeDiff("John Smith", "Barry Michaels"));
    }

    @Test
    public void shouldThrowExceptionWhileCalculatingAgeDifference_givenPerson1IsYoungerThanPerson2() {
        when(personRepository.getPersons()).thenReturn(List.of(
                newPerson("John Smith", MALE, LocalDate.now().minusYears(10)),
                newPerson("Barry Michaels", MALE, LocalDate.now().minusYears(20))
        ));
        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.calculateAgeDiff("John Smith", "Barry Michaels"));
    }

    @Test
    public void shouldCalculateAgeDifference_givenBothPersonExists() {
        when(personRepository.getPersons()).thenReturn(List.of(
                newPerson("John Smith", MALE, LocalDate.now().minusYears(20)),
                newPerson("Barry Michaels", MALE, LocalDate.now().minusYears(30)),
                newPerson("Darren Stones", MALE, LocalDate.now().minusYears(35)),
                newPerson("Samantha Brown", FEMALE, LocalDate.now().minusYears(40)),
                newPerson("Lucy Green", FEMALE, LocalDate.now().minusYears(25))
        ));

        long result = unitUnderTest.calculateAgeDiff("Barry Michaels", "John Smith");

        assertThat(result, is(3653L));
    }

    private Person newPerson(String name, Gender gender, LocalDate dateOfBirth) {
        return Person.builder()
                .name(name)
                .gender(gender)
                .dateOfBirth(dateOfBirth)
                .build();
    }
}