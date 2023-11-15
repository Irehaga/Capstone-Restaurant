package com.tamnguyen.restaurant.dto;

import com.tamnguyen.restaurant.enums.MembershipType;
import com.tamnguyen.restaurant.validation.FieldMatch;
import com.tamnguyen.restaurant.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Tam Nguyen
 */
@Data
@FieldMatch.List({@FieldMatch(first = "password", second = "matchPassword", message = "Password fields don't match")})
public class UserDTO {

    @Email(message = "Please enter Email")
    private String email;


    @NotEmpty(message = "Password cannot be empty")
    @ValidPassword(message = "Provide valid password")
    private String password;
    private String matchPassword;

    private String role;

}
