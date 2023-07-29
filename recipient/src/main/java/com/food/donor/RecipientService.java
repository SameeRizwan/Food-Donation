package com.food.donor;

import com.food.amqp.RabbitMQMessageProducer;
import com.food.clients.notification.DonorNotificationRequest;
import com.food.clients.notification.RecipientNotificationRequest;
import com.food.donor.annotation.EmailNotValidException;
import com.food.donor.dto.RecipientRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
@AllArgsConstructor
@Slf4j
public class RecipientService {

    private final RecipientRepository recipientRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Recipient registerDonor(RecipientRegistrationRequest request) throws Exception {
        try {
            Recipient recipient = Recipient.builder()
                    .fullName(request.fullName())
                    .organizationName(request.organizationName())
                    .email(request.email())
                    .phoneNumber(request.phoneNumber())
                    .physicalAddress(request.physicalAddress())
                    .city(request.email())
                    .type(request.type())
                    .dietaryPreferences(request.dietaryPreferences())
                    .quantityAccepted(request.quantityAccepted())
                    .pickupTime(request.pickupTime())
                    .pickupLocation(request.pickupLocation())
                    .specificNote(request.specificNote())
                    .build();

            recipientRepository.saveAndFlush(recipient);

            RecipientNotificationRequest notificationRequest = new RecipientNotificationRequest(
                    recipient.getId(),
                    recipient.getFullName(),
                    String.format("New Recipient Request %s Received",
                            recipient.getFullName())
            );

            rabbitMQMessageProducer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );

            return recipient;
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e.getClass().equals(ConstraintViolationException.class)) {
                throw new EmailNotValidException("Email is not Valid");
            } else {
                throw new Exception(e);
            }
        }
    }
}
