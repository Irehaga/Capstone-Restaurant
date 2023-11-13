package com.tamnguyen.restaurant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Tam Nguyen
 */

@Data
public class MenuItemDTO {

    private String name;
    private String description;
    private Double price;

    private Boolean isAvailable;
    private List<OrderDTO> orders;
}
