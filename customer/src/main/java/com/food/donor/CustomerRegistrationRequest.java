package com.food.donor;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {
}
