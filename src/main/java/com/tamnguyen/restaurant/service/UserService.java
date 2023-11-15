package com.tamnguyen.restaurant.service;

import com.tamnguyen.restaurant.dto.UserDTO;
import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.exceptionhandle.UserExistException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Tam Nguyen
 */
public interface UserService extends UserDetailsService {

    UserDTO findUserByEmail(String email);


    void saveUser(UserDTO userDTO) throws UserExistException;


    UserDetails loadUserByUsername(String userName);

}
