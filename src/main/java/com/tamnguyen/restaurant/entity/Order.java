package com.tamnguyen.restaurant.entity;

import com.tamnguyen.restaurant.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tam Nguyen
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String specialRequests;
    private Boolean isDelivery;
    private String deliveryAddress;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_menu_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id")
    )
    private List<MenuItem> items;
    private Double totalAmount;

}
