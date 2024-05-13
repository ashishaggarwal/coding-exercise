package com.wellknownorg.addressbook;

import com.wellknownorg.addressbook.model.AddressBookResult;
import com.wellknownorg.addressbook.processor.AddressBookProcessor;
import com.wellknownorg.addressbook.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("intTest")
@TestPropertySource(properties = "addressbook.filename=blank.csv")
public class AddressBookIntegrationTest {

    @Autowired
    private AddressBookProcessor addressBookProcessor;

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void cleanUp() {
        personRepository.clear();
    }

    @Test
    public void shouldRunSuccessfully_givenBlankFile() {
        addressBookProcessor.process("blank.csv");
    }

    @Test
    public void shouldThrowValidationError_givenMoreThanThreeColumnsInFile() {
        assertThrows(IllegalArgumentException.class,
                () -> addressBookProcessor.process("fileWithMoreColumns.csv"));
    }

    @Test
    public void shouldThrowValidationError_givenLessThanThreeColumnsInFile() {
        assertThrows(IllegalArgumentException.class,
                () -> addressBookProcessor.process("fileWithLessColumns.csv"));
    }

    @Test
    public void shouldRunSuccessfully_givenNoGenderInfoInFile() {
        AddressBookResult result = addressBookProcessor.process("fileWithoutGender.csv");

        assertThat(result.numberOfMales(), is(0));
        assertThat(result.oldestPerson().dateOfBirth(), is(LocalDate.of(2000, 2, 4)));
        assertThat(result.ageDifference(), is(nullValue()));
    }

    @Test
    public void shouldRunSuccessfully_givenNoDateOfBirth() {
        AddressBookResult result = addressBookProcessor.process("fileWithoutDateOfBirth.csv");

        assertThat(result.numberOfMales(), is(1));
        assertThat(result.oldestPerson().dateOfBirth(), is(nullValue()));
        assertThat(result.ageDifference(), is(nullValue()));
    }

    @Test
    public void shouldRunSuccessfully_givenNoName() {
        AddressBookResult result = addressBookProcessor.process("fileWithoutName.csv");

        assertThat(result.numberOfMales(), is(1));
        assertThat(result.oldestPerson().dateOfBirth(), is(LocalDate.of(1990, 2, 1)));
        assertThat(result.ageDifference(), is(nullValue()));
    }

    @Test
    public void shouldRunSuccessfully_givenFileWithValidData() {
        AddressBookResult result = addressBookProcessor.process("fileWithValidData.csv");

        assertThat(result.numberOfMales(), is(3));
        assertThat(result.oldestPerson().dateOfBirth(), is(LocalDate.of(1970, 8, 14)));
        assertThat(result.ageDifference(), is(2862L));
    }
}
