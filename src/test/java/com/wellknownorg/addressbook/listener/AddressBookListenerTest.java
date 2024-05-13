package com.wellknownorg.addressbook.listener;

import com.wellknownorg.addressbook.processor.AddressBookProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddressBookListenerTest {

    public static final String FILE_NAME = "test.csv";
    @Mock
    private AddressBookProcessor addressBookProcessor;

    private AddressBookListener unitUnderTest;

    @BeforeEach
    public void setUp() {
        unitUnderTest = new AddressBookListener(addressBookProcessor, FILE_NAME);
    }

    @Test
    public void shouldListenSuccessfully() {
        unitUnderTest.listen();

        verify(addressBookProcessor).process(FILE_NAME);
    }

}