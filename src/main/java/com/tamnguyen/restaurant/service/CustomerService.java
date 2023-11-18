package com.tamnguyen.restaurant.service;

import com.tamnguyen.restaurant.dto.CustomerDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;
import com.tamnguyen.restaurant.exceptionhandle.UserExistException;

import java.util.List;

/**
 * @author Tam Nguyen
 */
public interface CustomerService {


    void createCustomer(CustomerDTO customerDTO) throws UserExistException;

    CustomerDTO findCustomerByEmail(String email);

    List<CustomerDTO> getAllCustomers();


}
