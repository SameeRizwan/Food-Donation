package com.food.donor;

import com.food.donor.annotation.EmailValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donor {

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
    private DonorType type;
    private String descriptionOfDonations;
    private int quantity;
    private String foodHandlingInstructions;
    private String pickupTime;
    private String pickupLocation;
    private String specificNote;
}
