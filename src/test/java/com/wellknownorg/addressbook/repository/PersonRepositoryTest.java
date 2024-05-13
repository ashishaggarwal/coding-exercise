package com.wellknownorg.addressbook.repository;

import com.wellknownorg.addressbook.model.Gender;
import com.wellknownorg.addressbook.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class PersonRepositoryTest {

    private PersonRepository unitUnderTest;
    private final Collection<Person> persons = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        unitUnderTest = PersonRepository.builder()
                .persons(persons)
                .build();
    }

    @Test
    public void shouldAddPerson() {
        Person person = new Person("John", Gender.MALE, LocalDate.of(2000, 2, 1));

        unitUnderTest.add(person);

        assertThat(persons, is(List.of(person)));
    }

    @Test
    public void shouldClearPersons() {
        unitUnderTest.clear();

        assertThat(persons, is(emptyList()));
    }
}