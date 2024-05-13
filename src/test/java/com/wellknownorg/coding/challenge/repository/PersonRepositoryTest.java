package com.wellknownorg.coding.challenge.repository;

import com.wellknownorg.coding.challenge.model.Gender;
import com.wellknownorg.coding.challenge.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonRepositoryTest {

    private PersonRepository unitUnderTest;
    private final Collection<Person> persons = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        unitUnderTest = new PersonRepository();
        unitUnderTest.setPersons(persons);
    }

    @Test
    public void shouldAddPerson() {
        Person person = new Person("John", Gender.MALE, LocalDate.of(2000, 2, 1));

        unitUnderTest.add(person);

        assertThat(persons, is(List.of(person)));
    }
}