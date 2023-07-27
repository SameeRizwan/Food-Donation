package com.food.clients.notification;

public record NotificationRequest(Integer toCustomerId, String toCustomerName, String message) { }
