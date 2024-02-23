package com.practice.SpringSecurity.Repository;

import com.practice.SpringSecurity.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmail(String email);
}
