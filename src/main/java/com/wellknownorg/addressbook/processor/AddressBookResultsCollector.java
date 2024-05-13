package com.wellknownorg.addressbook.processor;

import com.wellknownorg.addressbook.exception.CannotDetermineAgeDifferenceException;
import com.wellknownorg.addressbook.exception.CannotDetermineOldestPersonException;
import com.wellknownorg.addressbook.model.Person;
import com.wellknownorg.addressbook.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

import static com.wellknownorg.addressbook.model.Gender.MALE;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public record AddressBookResultsCollector(PersonRepository personRepository) {

    public int countMales() {
        return initializeIfNull(personRepository.getPersons())
                .stream()
                .filter(person -> person.gender() != null && person.gender() == MALE)
                .toList()
                .size();
    }

    public Person findOldestPerson() {
        return initializeIfNull(personRepository.getPersons())
                .stream()
                .min(Comparator.comparing(Person::dateOfBirth))
                .orElseThrow(() -> new CannotDetermineOldestPersonException("Cannot determine oldest person, please check person exists with valid date of birth"));
    }

    public Long calculateAgeDiff(String nameOfOlderPerson, String nameOfYoungerPerson) {
        Map<String, Person> personsOfInterest = initializeIfNull(personRepository.getPersons())
                .stream()
                .filter(person -> person.name().equals(nameOfOlderPerson) || person.name().equals(nameOfYoungerPerson))
                .collect(toMap(Person::name, Function.identity()));

        Person person1 = personsOfInterest.get(nameOfOlderPerson);
        Person person2 = personsOfInterest.get(nameOfYoungerPerson);

        if (personOrDobIsNull(person1) || personOrDobIsNull(person2)
                || person1.dateOfBirth().isAfter(person2.dateOfBirth())) {
            throw new CannotDetermineAgeDifferenceException("Cannot determine age difference, " +
                    "check both person exists with valid date of birth");
        }
        return DAYS.between(person1.dateOfBirth(), person2.dateOfBirth());
    }

    private boolean personOrDobIsNull(Person person) {
        return person == null || person.dateOfBirth() == null;
    }

    private Collection<Person> initializeIfNull(Collection<Person> persons) {
        return Optional.ofNullable(persons)
                .orElseGet(Collections::emptyList);
    }
}
