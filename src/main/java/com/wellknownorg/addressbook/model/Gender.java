package com.wellknownorg.addressbook.model;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Gender {

    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public static Gender from(String description) {
        return description == null ? null : Stream.of(values())
                .filter(gender -> description.trim().equalsIgnoreCase(gender.getDescription()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no gender found for string " + description));
    }

}
