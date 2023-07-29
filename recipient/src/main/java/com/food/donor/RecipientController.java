package com.food.donor;

import com.food.donor.dto.RecipientRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/donor")
@AllArgsConstructor
public class RecipientController {

    private final RecipientService donorService;

    @PostMapping
    public ResponseEntity registerCustomer(@Valid @RequestBody RecipientRegistrationRequest customerRegistrationRequest) {
        try {
            log.info("new Donor registration {}", customerRegistrationRequest);
            return ResponseEntity.ok(donorService.registerDonor(customerRegistrationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
