package com.practice.SpringSecurity.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.practice.SpringSecurity.Repository.CustomerRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="customer")
@Data
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private int id;

    @NotBlank(message ="Provide a valid email address.")
    private String email;

    @NotBlank(message = "Provide a valid mobile number.")
    private String mobileNumber;

    @NotBlank(message = "Provide a password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String created_date;

//    @JsonIgnore
//    @OneToMany(mappedBy="customer",fetch = FetchType.EAGER)
//    private Set<Authority> authorities;

}
