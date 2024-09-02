package com.practice.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_entity")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Column(name = "customer_name")
    private String customerName;
    private String email;
    private String password;
    private String role;

}
