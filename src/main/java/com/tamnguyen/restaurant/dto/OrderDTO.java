package com.tamnguyen.restaurant.dto;

import com.tamnguyen.restaurant.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tam Nguyen
 */

@Data

public class OrderDTO {

    private UserDTO customer;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private String specialRequests;
    private Boolean isDelivery;
    private String deliveryAddress;
    private List<MenuItemDTO> items;
    private Double totalAmount;
}
