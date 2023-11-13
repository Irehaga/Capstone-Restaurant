package com.tamnguyen.restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tam Nguyen
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String roleName;

    public Role(String roleName){

        this.roleName = roleName;
    }

}
