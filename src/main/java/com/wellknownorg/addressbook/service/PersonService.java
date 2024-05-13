package com.wellknownorg.addressbook.service;

import com.wellknownorg.addressbook.mapper.PersonMapper;
import com.wellknownorg.addressbook.repository.PersonRepository;
import com.wellknownorg.addressbook.model.Person;
import org.springframework.stereotype.Component;

@Component
public record PersonService (PersonMapper personMapper,
                             PersonRepository personRepository) {

    public void add(String [] data) {
        validateData(data);

        Person person = personMapper.mapToPerson(data);
        personRepository.add(person);
    }

    private void validateData(String[] data) {
        if (data.length != 3) {
            throw new IllegalArgumentException("Cannot create person as some attributes are missing in data");
        }
    }
}
