package com.example.payment_service.service;

import com.example.payment_service.dto.PaymentRequest;
import com.example.payment_service.entity.Payment;
import com.example.payment_service.entity.enumClass.Status;
import com.example.payment_service.events.CreatePaymentEvents;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public String addStatus(PaymentRequest paymentRequest) {

        Payment payment = new Payment();

        payment.setStatus(Status.STATUS_CREATED);
        payment.setCreated_at(Timestamp.from(Instant.now()));
        payment.setFinished(Boolean.FALSE);
        payment.setSumOfTransaction(paymentRequest.getPrice());
        payment.setAccountCheck(paymentRequest.getAccountCheck());

        paymentRepository.save(payment);

        CreatePaymentEvents createPaymentEvents = new CreatePaymentEvents(
                this,
                "Payment created!!!"
        );
        applicationEventPublisher.publishEvent(createPaymentEvents);

        return "Payment Created";
    }

    @Override
    public String setStatus(int id) {

        Payment payment = paymentRepository.getById(id);

        payment.setFinished(Boolean.TRUE);

        if(payment.getFinished() == Boolean.TRUE) {
            payment.setStatus(Status.STATUS_SUCCESS);
        }else {
            payment.setStatus(Status.STATUS_FAIL);
        }

        payment.setUpdated_at(Timestamp.from(Instant.now()));

        paymentRepository.save(payment);

        return "Your status in payment: " + payment.getStatus();
    }

    @Override
    public String rollbackPayment(int id) {
        Payment payment = paymentRepository.getById(id);

        if(payment.getStatus() == Status.STATUS_SUCCESS) {

            long Milli = Math.abs(payment.getUpdated_at().getTime() - new Date().getTime());

            if(Milli < 1080000) {
                payment.setStatus(Status.STATUS_ROLLBACK);
            } else {
                return "3 days gone";
            }
        } else if(payment.getStatus() == Status.STATUS_CREATED) {
            payment.setStatus(Status.STATUS_ROLLBACK);
        } else {
            return "You dont have payment or your status fail";
        }

        payment.setUpdated_at(Timestamp.from(Instant.now()));

        paymentRepository.save(payment);

        return "Rollback payment";
    }

    @Override
    public String getByStatus(String status) {

        List<Payment> payment = paymentRepository.getByStatus(status);

        return "Payment with status: " + payment;

    }
}
