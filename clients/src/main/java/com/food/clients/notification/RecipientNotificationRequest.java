package com.food.clients.notification;

public record RecipientNotificationRequest(Integer recipientId, String recipientName, String message) { }
