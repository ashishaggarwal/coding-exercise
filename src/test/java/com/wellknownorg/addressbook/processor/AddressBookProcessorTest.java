package com.wellknownorg.addressbook.processor;

import com.wellknownorg.addressbook.file.read.FileReader;
import com.wellknownorg.addressbook.model.AddressBookResult;
import com.wellknownorg.addressbook.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookProcessorTest {

    @Mock
    private FileReader fileReader;

    @Mock
    private AddressBookResultsCollector addressBookResultsCollector;

    private AddressBookProcessor unitUnderTest;

    @BeforeEach
    public void setUp() {
        unitUnderTest = new AddressBookProcessor(fileReader, addressBookResultsCollector);
    }

    @Test
    public void shouldProcessSuccessfully() {
        String filename = "test.csv";
        doNothing().when(fileReader).readFile(filename);
        when(addressBookResultsCollector.countMales()).thenReturn(10);
        Person oldestPerson = Person.builder()
                .name("Steven")
                .build();
        when(addressBookResultsCollector.findOldestPerson()).thenReturn(oldestPerson);
        when(addressBookResultsCollector.calculateAgeDiff(anyString(), anyString())).thenReturn(30L);

        AddressBookResult result = unitUnderTest.process(filename);

        assertThat(result, is(AddressBookResult.builder()
                .numberOfMales(10)
                .oldestPerson(oldestPerson)
                .ageDifference(30L)
                .build()));
    }

    @Test
    public void shouldProcessSuccessfully_givenExceptionWhileCalculatingResult() {
        String filename = "test.csv";
        doNothing().when(fileReader).readFile(filename);
        when(addressBookResultsCollector.findOldestPerson()).thenThrow(RuntimeException.class);
        when(addressBookResultsCollector.calculateAgeDiff(anyString(), anyString())).thenThrow(RuntimeException.class);

        AddressBookResult result = unitUnderTest.process(filename);

        assertThat(result, is(AddressBookResult.builder()
                .numberOfMales(0)
                .build()));
    }
}