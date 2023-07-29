package com.food.donor.dto;

import com.food.donor.RecipientType;

public record RecipientRegistrationRequest(
        Integer id,
        String fullName,
        String organizationName,
        String email,
        long phoneNumber,
        String physicalAddress,
        String city,
        RecipientType type,
        String descriptionOfDonations,
        String dietaryPreferences,
        int quantityAccepted,
        String pickupTime,
        String pickupLocation,
        String specificNote) {
}
