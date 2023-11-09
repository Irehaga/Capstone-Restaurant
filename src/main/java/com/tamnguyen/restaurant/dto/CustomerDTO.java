package com.tamnguyen.restaurant.dto;

import com.tamnguyen.restaurant.enums.MembershipType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tam Nguyen
 */
@Data
@NoArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "Can't be empty")
    @Size(max = 50)
    private String firstName;

    @NotEmpty(message = "Can't be empty")
    @Size(max = 50)
    private String lastName;

    @Email(message = "please provide email")
    private String email;

    @NotEmpty(message = "Please provide phone number")
    private String phoneNumber;

    @NotEmpty(message = "Please provide password")
    @Size(min = 8, max = 30)
    private String password;
    private String matchingPassword;

    private String address;
    private LocalDate startDate;
    private LocalDateTime registrationDate;

    private MembershipType membershipType;
    private List<OrderDTO> orders;

}
