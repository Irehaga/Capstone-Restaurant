package com.tamnguyen.restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Tam Nguyen
 */

@Entity
@Data
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roleName;

}
