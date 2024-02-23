package com.practice.SpringSecurity.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="authorities")
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator = "native")
    private Long id;

    private String name;

//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    private Customer customer;

    private int customer_id;

}
