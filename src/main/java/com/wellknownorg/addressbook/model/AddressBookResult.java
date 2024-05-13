package com.wellknownorg.addressbook.model;

import lombok.Builder;

@Builder(toBuilder = true)
public record AddressBookResult (Integer numberOfMales, Person oldestPerson, Long ageDifference){
}
