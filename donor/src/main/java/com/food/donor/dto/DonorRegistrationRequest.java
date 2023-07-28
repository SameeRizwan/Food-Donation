package com.food.donor.dto;

import com.food.donor.DonorType;

public record DonorRegistrationRequest(
        Integer id,
        String fullName,
        String organizationName,
        String email,
        long phoneNumber,
        String physicalAddress,
        String city,
        DonorType type,
        String descriptionOfDonations,
        int quantity,
        String foodHandlingInstructions,
        String pickupTime,
        String pickupLocation,
        String specificNote) {
}
