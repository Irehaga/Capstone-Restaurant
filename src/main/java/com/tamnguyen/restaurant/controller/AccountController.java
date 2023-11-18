package com.tamnguyen.restaurant.controller;


import com.tamnguyen.restaurant.dto.CustomerDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;
import com.tamnguyen.restaurant.dto.UserDTO;

import com.tamnguyen.restaurant.entity.Role;
import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.enums.RoleName;
import com.tamnguyen.restaurant.service.CustomerService;
import com.tamnguyen.restaurant.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tam Nguyen
 */

@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());

    private CustomerService customerService;
    private UserService userService;

    public AccountController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }


//    @GetMapping("/account")
//    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        UserDTO userDTO = userService.findUserByEmail(userDetails.getUsername());
//        List<Role> roleList = userDTO.getRoles().stream().collect(Collectors.toList());
//        boolean isCustomer = roleList.stream().anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_CUSTOMER.name()));
//        if(isCustomer){
//            CustomerDTO customerDTO = customerService.findCustomerByEmail(userDetails.getUsername());
//            model.addAttribute("customer", customerDTO);
//            model.addAttribute("order", customerDTO.getOrders());
//        }else{
//            return "/login";
//        }
//        return "customer-account-dashboard";
//    }
}
