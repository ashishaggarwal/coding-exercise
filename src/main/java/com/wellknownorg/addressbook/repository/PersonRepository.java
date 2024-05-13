package com.wellknownorg.addressbook.repository;

import com.wellknownorg.addressbook.model.Person;
import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;

@Getter
@Builder
@Repository
public class PersonRepository {

    private Collection<Person> persons;

    public void add(Person person) {
        if (persons == null) {
            persons = new HashSet<>();
        }
        persons.add(person);
    }

    public void clear() {
        persons.clear();
    }
}
