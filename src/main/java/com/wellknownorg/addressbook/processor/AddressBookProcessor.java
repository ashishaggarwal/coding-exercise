package com.wellknownorg.addressbook.processor;

import com.wellknownorg.addressbook.file.read.FileReader;
import com.wellknownorg.addressbook.model.AddressBookResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Slf4j
@Component
public record AddressBookProcessor(FileReader fileReader, AddressBookResultsCollector addressBookResultsCollector) {

    public AddressBookResult process(String filename) {
        fileReader.readFile(filename);

        Integer numberOfMales = getResult(addressBookResultsCollector::countMales);
        var oldestPerson = getResult(addressBookResultsCollector::findOldestPerson);
        Long ageDifference = getResult(() -> addressBookResultsCollector.calculateAgeDiff("Bill McKnight", "Paul Robinson"));

        log.info("=======Number of Males in address book=======> {}", numberOfMales);
        log.info("=======Oldest person in address book=======> {}", oldestPerson);
        log.info("=======Age difference in days is =======> {}", ageDifference);

        return AddressBookResult.builder()
                .numberOfMales(numberOfMales)
                .oldestPerson(oldestPerson)
                .ageDifference(ageDifference)
                .build();
    }

    public <T> T getResult(Supplier<T> function) {
        try {
            return function.get();
        } catch (Exception e) {
            log.error("Error occurred while processing application", e);
        }
        return null;
    }

}
