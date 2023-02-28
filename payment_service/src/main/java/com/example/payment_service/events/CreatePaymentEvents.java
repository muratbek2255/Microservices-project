package com.example.payment_service.events;

import org.springframework.context.ApplicationEvent;

public class CreatePaymentEvents extends ApplicationEvent {
    public String payload;

    public CreatePaymentEvents(Object source, String payload) {
        super(source);
        this.payload = payload;
    }
}