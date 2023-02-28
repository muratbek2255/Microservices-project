package com.example.payment_service.repository;

import com.example.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "SELECT * FROM payment.payments WHERE payment.payments.id = ?1", nativeQuery = true)
    Payment getById(@Param("id") int id);

    @Query(value = "SELECT * FROM payment.payments WHERE payment.payments.status = ?1", nativeQuery = true)
    List<Payment> getByStatus(@Param("status") String status);
}
