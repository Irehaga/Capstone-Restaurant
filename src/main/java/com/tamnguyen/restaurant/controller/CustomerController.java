package com.tamnguyen.restaurant.controller;

import com.tamnguyen.restaurant.dto.CustomerDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;
import com.tamnguyen.restaurant.dto.UserDTO;
import com.tamnguyen.restaurant.entity.Customer;
import com.tamnguyen.restaurant.enums.RoleName;
import com.tamnguyen.restaurant.exceptionhandle.UserExistException;
import com.tamnguyen.restaurant.service.CustomerService;
import com.tamnguyen.restaurant.service.OrderService;
import com.tamnguyen.restaurant.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.tamnguyen.restaurant.utility.PageTemaplate.CUSTOMER_SIGN_UP;
import static com.tamnguyen.restaurant.utility.PageTemaplate.SUCCESSFUL_SIGN_UP_PAGE;

/**
 * @author Tam Nguyen
 */


@Controller
@RequestMapping("/customer")
public class CustomerController {


    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());
    private final CustomerService customerService;
    private final UserService userService;

    private final OrderService orderService;
    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, OrderService orderService) {
        this.customerService = customerService;
        this.userService = userService;
        this.orderService = orderService;
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("user", new UserDTO());
        return CUSTOMER_SIGN_UP;
    }


    @PostMapping("/register")
    public String registerCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO,
                               BindingResult bindingResult,
                               @Valid @ModelAttribute("user") UserDTO userDTO,
                               BindingResult userBidingResult,
                               Model model) {
        if(bindingResult.hasFieldErrors()){
            logger.warn("Invalid input during customer registration");
            return CUSTOMER_SIGN_UP;
        }
        if(userBidingResult.hasErrors())
        {
            return CUSTOMER_SIGN_UP;
        }
        try{
            if (!userDTO.getPassword().equals(userDTO.getMatchPassword())) {
                model.addAttribute("passwordMatchError", "Passwords do not match");
                return CUSTOMER_SIGN_UP;
            }
            customerService.createCustomer(customerDTO);
            userDTO.setEmail(customerDTO.getEmail());
            userDTO.setRole(RoleName.ROLE_CUSTOMER.name());
            userService.saveUser(userDTO);
        } catch (UserExistException e){
            model.addAttribute("message", "User already exist");
            return CUSTOMER_SIGN_UP;
        }
        return SUCCESSFUL_SIGN_UP_PAGE;
}






}// end of class
