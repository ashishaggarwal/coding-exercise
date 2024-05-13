package com.wellknownorg.coding.challenge.listener;

import com.wellknownorg.coding.challenge.file.read.FileReader;
import com.wellknownorg.coding.challenge.processor.AddressBookProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public record AddressBookListener(FileReader fileReader,
                                 AddressBookProcessor addressBookProcessor,
                                 @Value("${addressbook.filename}") String filename) {

    @EventListener(ApplicationReadyEvent.class)
    public void kickOffProcessing() {
        fileReader.readFile(filename);

        addressBookProcessor.countMales();
        addressBookProcessor.findOldestPerson();
        addressBookProcessor.calculateAgeDiff("Bill McKnight", "Paul Robinson");

    }

}
