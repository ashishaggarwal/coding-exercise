package com.wellknownorg.addressbook.service;

import com.wellknownorg.addressbook.mapper.PersonMapper;
import com.wellknownorg.addressbook.model.Person;
import com.wellknownorg.addressbook.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonMapper personMapper;

    @Mock
    private PersonRepository personRepository;

    private PersonService unitUnderTest;

    @BeforeEach
    public void setUp() {
        unitUnderTest = new PersonService(personMapper, personRepository);
    }

    @Test
    public void shouldThrowException_givenMoreThanThreeColumns() {
        String[] data = {"John", "Male", "01/02/20", "additional data"};

        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.add(data));
    }

    @Test
    public void shouldThrowException_givenLessThanThreeColumns() {
        String[] data = {"John", "Male"};

        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.add(data));
    }

    @Test
    public void shouldAddPerson() {
        String[] data = {"John", "Male", "01/02/20"};
        Person person = Person.builder().build();
        when(personMapper.mapToPerson(data)).thenReturn(person);

        unitUnderTest.add(data);

        verify(personMapper).mapToPerson(data);
        verify(personRepository).add(person);
    }
}