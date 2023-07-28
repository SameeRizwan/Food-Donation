package com.food.donor;

import com.food.donor.annotation.EmailNotValidException;
import com.food.donor.dto.DonorRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

@Slf4j
@RestController
@RequestMapping("api/v1/donor")
@AllArgsConstructor
public class DonorController {

    private final DonorService donorService;

    @PostMapping
    public ResponseEntity registerCustomer(@Valid @RequestBody DonorRegistrationRequest customerRegistrationRequest) {
        try {
            log.info("new Donor registration {}", customerRegistrationRequest);
            return ResponseEntity.ok(donorService.registerDonor(customerRegistrationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
