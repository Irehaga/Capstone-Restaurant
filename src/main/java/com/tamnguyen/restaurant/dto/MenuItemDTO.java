package com.tamnguyen.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * @author Tam Nguyen
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDTO {

    public MenuItemDTO(String name, String description, Double price, String imageFileName) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageFileName = imageFileName;
    }

    private String name;
    private String description;
    private Double price;
    private String imageFileName;
    private Boolean isAvailable;
    private Collection<OrderDTO> orders;

}
