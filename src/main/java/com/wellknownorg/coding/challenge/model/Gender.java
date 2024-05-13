package com.wellknownorg.coding.challenge.model;

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
        return Stream.of(values())
                .filter(gender -> description != null && description.trim().equalsIgnoreCase(gender.getDescription()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("no gender found for string " + description));
    }

}
