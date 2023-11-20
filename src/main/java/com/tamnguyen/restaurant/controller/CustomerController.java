package com.tamnguyen.restaurant.controller;

import com.tamnguyen.restaurant.dto.CustomerDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;
import com.tamnguyen.restaurant.dto.UserDTO;
import com.tamnguyen.restaurant.entity.Customer;
import com.tamnguyen.restaurant.entity.Role;
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

import java.util.List;
import java.util.stream.Collectors;

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


    /**
     *       initialize the controller with the params below
     * @param customerService
     * @param userService
     * @param orderService
     */
    @Autowired
    public CustomerController(CustomerService customerService, UserService userService, OrderService orderService) {
        this.customerService = customerService;
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     *  displays the registration form
     *  add empty CustomerDtO and userDTO to the model
     * @param model
     * @return
     */

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("customer", new CustomerDTO());
        model.addAttribute("user", new UserDTO());
        return CUSTOMER_SIGN_UP;
    }


    /**
     *    Handle the registration process, validates input data and checks for password match
     *    Create customer and user if validation passes or return to registration form with errors.
     *
     *
     * @param customerDTO
     * @param bindingResult
     * @param userDTO
     * @param userBidingResult
     * @param model
     * @return return either error messages or success page upon successful registration.
     */
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
            userDTO.setRole("ROLE_CUSTOMER");
            userService.saveUser(userDTO);
        } catch (UserExistException e){
            model.addAttribute("message", "User already exist");
            return CUSTOMER_SIGN_UP;
        }
        return SUCCESSFUL_SIGN_UP_PAGE;
}


    /**
     *  Display the user dashboard for customer after successful login
     *  Checks if the logged in user is customer and retrieves their data
     *  adds customer details and order to the model for display
     *
     * @param userDetails
     * @param model
     * @return return to index if there is an error ele go to customer account page
     */
    @GetMapping("/account")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDTO userDTO = userService.findUserByEmail(userDetails.getUsername());
        List<Role> roleList = userDTO.getRoles().stream().collect(Collectors.toList());
        boolean isCustomer = roleList.stream().anyMatch(role -> role.getRoleName().equals("ROLE_CUSTOMER"));
        if(isCustomer){
            logger.warn("something is wrong");
            CustomerDTO customerDTO = customerService.findCustomerByEmail(userDetails.getUsername());
            model.addAttribute("customer", customerDTO);
            model.addAttribute("order", customerDTO.getOrders());
        }else{
            logger.warn("something is wrong");
            return "index";
        }
        return "customer-account-dashboard";
    }






}// end of class
