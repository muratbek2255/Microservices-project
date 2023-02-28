package com.example.payment_service.listeners;

import com.example.payment_service.events.CreatePaymentEvents;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ReportingPersonListener {

    @EventListener(CreatePaymentEvents.class)
    public void reportPaymentCreation(CreatePaymentEvents event) {
        System.out.println("Increment counter as new payment was created: " + event);
    }

    @EventListener(CreatePaymentEvents.class)
    public void syncPaymentToExternalSystem(CreatePaymentEvents event) {
        System.out.println("informing other systems about payment: " + event);
    }
}