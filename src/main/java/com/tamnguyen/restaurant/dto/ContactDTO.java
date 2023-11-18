package com.tamnguyen.restaurant.dto;

import lombok.Data;

/**
 * @author Tam Nguyen
 */

@Data
public class ContactDTO {

    private String name;
    private String email;
    private String subject;
    private String message;
}
