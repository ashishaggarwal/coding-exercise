package com.wellknownorg.addressbook.listener;

import com.wellknownorg.addressbook.processor.AddressBookProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("!intTest")
public record AddressBookListener(AddressBookProcessor addressBookProcessor,
                                  @Value("${addressbook.filename}") String filename) {

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        addressBookProcessor.process(filename);
    }

}
