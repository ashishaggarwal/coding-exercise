package com.wellknownorg.coding.challenge.service;

import com.wellknownorg.coding.challenge.mapper.PersonMapper;
import com.wellknownorg.coding.challenge.model.Person;
import com.wellknownorg.coding.challenge.repository.PersonRepository;

public record PersonService (PersonMapper personMapper,
                             PersonRepository personRepository) {

    public void add(String[] data) {
        validateData(data);

        Person person = personMapper.mapToPerson(data);
        personRepository.add(person);
    }

    private void validateData(String[] data) {
        if (data.length < 3) {
            throw new IllegalArgumentException("Cannot create person as some attributes are missing in data");
        }
    }
}
