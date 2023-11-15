package com.tamnguyen.restaurant.dto;

import com.tamnguyen.restaurant.entity.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @author Tam Nguyen
 */

@Data
public class CustomerDTO {


    @NotEmpty(message = "Please enter your first name")
    private String firstName;
    @NotEmpty(message = "Please enter your last name")
    private String lastName;


    @Email(message = "wrong formatting")
    @NotEmpty(message = "can't be empty")
    private String email;

    private String phoneNumber;
    @NotEmpty(message = "Please enter your address")
    private String address;
    private String password;

    private List<OrderDTO> orders;


}
