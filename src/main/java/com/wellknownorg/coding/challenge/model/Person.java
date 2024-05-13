package com.wellknownorg.coding.challenge.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder(toBuilder = true)
public record Person(String name, Gender gender, LocalDate dateOfBirth) {
}
