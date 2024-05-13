package com.wellknownorg.coding.challenge.service;

import com.wellknownorg.coding.challenge.mapper.PersonMapper;
import com.wellknownorg.coding.challenge.model.Person;
import com.wellknownorg.coding.challenge.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void shouldAddPerson() {
        String[] data = {"John", "Male", "01/02/20"};
        Person person = Person.builder().build();
        when(personMapper.mapToPerson(data)).thenReturn(person);

        unitUnderTest.add(data);

        verify(personMapper).mapToPerson(data);
        verify(personRepository).add(person);
    }
}