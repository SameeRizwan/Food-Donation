package com.food.donor;

import com.food.amqp.RabbitMQMessageProducer;
import com.food.clients.notification.DonorNotificationRequest;
import com.food.clients.notification.NotificationRequest;
import com.food.donor.annotation.EmailNotValidException;
import com.food.donor.dto.DonorRegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;

@Service
@AllArgsConstructor
@Slf4j
public class DonorService {

    private final DonorRepository donorRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public Donor registerDonor(DonorRegistrationRequest request) throws Exception {
        try {
            Donor donor = Donor.builder()
                    .fullName(request.fullName())
                    .organizationName(request.organizationName())
                    .email(request.email())
                    .phoneNumber(request.phoneNumber())
                    .physicalAddress(request.physicalAddress())
                    .city(request.email())
                    .type(request.type())
                    .descriptionOfDonations(request.descriptionOfDonations())
                    .quantity(request.quantity())
                    .foodHandlingInstructions(request.foodHandlingInstructions())
                    .pickupTime(request.pickupTime())
                    .pickupLocation(request.pickupLocation())
                    .specificNote(request.specificNote())
                    .build();

            donorRepository.saveAndFlush(donor);

            DonorNotificationRequest notificationRequest = new DonorNotificationRequest(
                    donor.getId(),
                    donor.getFullName(),
                    String.format("New Donor Request %s Received",
                            donor.getFullName())
            );

            rabbitMQMessageProducer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );

            return donor;
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
