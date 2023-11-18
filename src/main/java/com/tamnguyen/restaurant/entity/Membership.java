package com.tamnguyen.restaurant.entity;

import com.tamnguyen.restaurant.enums.MembershipStatus;
import com.tamnguyen.restaurant.enums.MembershipType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author Tam Nguyen
 */
@Entity
@Data
public class Membership {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private MembershipType type;
    private LocalDate startDate;
    private LocalDate endDate;

    private MembershipStatus status;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private User user;
}
