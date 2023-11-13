package com.tamnguyen.restaurant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author Tam Nguyen
 */
@Entity
@Data
public class MenuItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private Double price;
    private Boolean isAvailable;

    @ManyToMany(mappedBy = "items")
    private List<Order> orders;
}
