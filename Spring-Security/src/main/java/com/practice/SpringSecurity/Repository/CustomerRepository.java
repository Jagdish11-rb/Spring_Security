package com.practice.SpringSecurity.Repository;

import com.practice.SpringSecurity.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByEmail(String email);

    Customer findByCustomerName(String name);

    boolean existsByCustomerName(String name);
}
