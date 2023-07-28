package com.food.donor;

import com.food.donor.dto.DonorRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/donor")
@AllArgsConstructor
public class DonorController {

    private final DonorService donorService;

    @PostMapping
    public void registerCustomer(@Valid @RequestBody DonorRegistrationRequest customerRegistrationRequest) {
        log.info("new Donor registration {}", customerRegistrationRequest);
        donorService.registerDonor(customerRegistrationRequest);
    }
}
