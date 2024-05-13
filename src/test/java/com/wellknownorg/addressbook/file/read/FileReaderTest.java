package com.wellknownorg.addressbook.file.read;

import com.wellknownorg.addressbook.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class FileReaderTest {

    @Mock
    private PersonService personService;

    @InjectMocks
    private FileReader unitUnderTest;

    @Test
    public void shouldNotParseFile_givenFileDoesNotExists() {
        assertThrows(IllegalArgumentException.class,
                () -> unitUnderTest.readFile("test.txt"));
    }

    @Test
    public void shouldParseFile_givenBlankFile() {
        unitUnderTest.readFile("blank.csv");

        verifyNoInteractions(personService);
    }

    @Test
    public void shouldParseFile_givenFileExists() {
        unitUnderTest.readFile("test.csv");

        verify(personService).add(new String[] {"John", " Male", " 04/02/00"});
    }
}