package com.food.donor;

import com.food.donor.annotation.EmailValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fullName;
    private String organizationName;
    @EmailValidation()
    private String email;
    private long phoneNumber;
    private String physicalAddress;
    private String city;
    private RecipientType type;
    private String descriptionOfNeeds;
    private String dietaryPreferences;
    private int quantityAccepted;
    private String pickupTime;
    private String pickupLocation;
    private String specificNote;
}
