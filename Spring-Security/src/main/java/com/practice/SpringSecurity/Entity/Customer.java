package com.practice.SpringSecurity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Column(name = "customer_name")
    @NotNull(message = "Customer name can't be blank.")
    private String customerName;
    private String email;
    @JsonIgnore
    private String password;
    private String role;

}
