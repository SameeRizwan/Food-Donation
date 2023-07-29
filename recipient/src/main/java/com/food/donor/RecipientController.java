package com.food.donor;

import com.food.donor.dto.RecipientRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/recipient")
@AllArgsConstructor
public class RecipientController {

    private final RecipientService recipientService;

    @PostMapping
    public ResponseEntity registerRecipient(@Valid @RequestBody RecipientRegistrationRequest recipientRegistrationRequest) {
        try {
            log.info("new Recipient registration {}", recipientRegistrationRequest);
            return ResponseEntity.ok(recipientService.registerRecipient(recipientRegistrationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
