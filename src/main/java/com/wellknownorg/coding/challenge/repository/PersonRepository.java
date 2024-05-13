package com.wellknownorg.coding.challenge.repository;

import com.wellknownorg.coding.challenge.model.Person;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;

@Data
@Repository
public class PersonRepository {

    private Collection<Person> persons;

    public void add(Person person) {
        if (persons == null) {
            persons = new HashSet<>();
        }
        persons.add(person);
    }

}
