package com.wellknownorg.coding.challenge.processor;

import com.wellknownorg.coding.challenge.model.Person;
import com.wellknownorg.coding.challenge.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

import static com.wellknownorg.coding.challenge.model.Gender.MALE;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public record AddressBookProcessor(PersonRepository personRepository) {

    public int countMales() {
        int numberOfMales = initializeIfNull(personRepository.getPersons())
                .stream()
                .filter(person -> person.gender() != null && person.gender() == MALE)
                .toList()
                .size();

        log.info("=======Number of Males in address book=======> {}", numberOfMales);
        return numberOfMales;
    }

    public Person findOldestPerson() {
        Person oldestPerson = initializeIfNull(personRepository.getPersons())
                .stream()
                .min(Comparator.comparing(Person::dateOfBirth))
                .orElseThrow(() -> new IllegalArgumentException("Cannot determine oldest person as there might be more than one person with same age"));

        log.info("=======Oldest person in address book=======> {}", oldestPerson);

        return oldestPerson;
    }

    public long calculateAgeDiff(String nameOfOlderPerson, String nameOfYoungerPerson) {
        Map<String, Person> personsOfInterest = initializeIfNull(personRepository.getPersons())
                .stream()
                .filter(person -> person.name().equals(nameOfOlderPerson) || person.name().equals(nameOfYoungerPerson))
                .collect(toMap(Person::name, Function.identity()));

        Person person1 = personsOfInterest.get(nameOfOlderPerson);
        Person person2 = personsOfInterest.get(nameOfYoungerPerson);

        if (personOrDobIsNull(person1) || personOrDobIsNull(person2)
                || person1.dateOfBirth().isAfter(person2.dateOfBirth())) {
            throw new IllegalArgumentException("Cannot determine age difference");
        }
        long ageDifferenceInDays = DAYS.between(person1.dateOfBirth(), person2.dateOfBirth());

        log.info("=======Age difference in days between {} and {} =======> {}", nameOfOlderPerson, nameOfYoungerPerson, ageDifferenceInDays);
        return ageDifferenceInDays;
    }

    private boolean personOrDobIsNull(Person person) {
        return person == null || person.dateOfBirth() == null;
    }

    private Collection<Person> initializeIfNull(Collection<Person> persons) {
        return Optional.ofNullable(persons)
                .orElseGet(Collections::emptyList);
    }
}
