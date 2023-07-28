package com.food.notification;

import com.food.clients.notification.DonorNotificationRequest;
import com.food.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(DonorNotificationRequest notificationRequest) {
        notificationRepository.save(
                DonorNotification.builder()
                        .fromDonorId(notificationRequest.donorId())
                        .fromDonorName(notificationRequest.donorName())
                        .sender("DONOR")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
