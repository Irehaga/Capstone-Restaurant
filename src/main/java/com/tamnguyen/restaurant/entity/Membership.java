package com.tamnguyen.restaurant.entity;

import com.tamnguyen.restaurant.enums.MembershipStatus;
import com.tamnguyen.restaurant.enums.MembershipType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

/**
 * @author Tam Nguyen
 */
@Entity
public class Membership {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private MembershipType type;
    private LocalDate startDate;

    private MembershipStatus status;

    @ManyToOne
    @JoinColumn(name= "customer_id")
    private Customer customer;
}
