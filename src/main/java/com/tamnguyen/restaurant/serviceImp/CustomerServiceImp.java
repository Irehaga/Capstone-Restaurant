package com.tamnguyen.restaurant.serviceImp;

import com.tamnguyen.restaurant.dto.CustomerDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;
import com.tamnguyen.restaurant.entity.Customer;
import com.tamnguyen.restaurant.entity.Order;
import com.tamnguyen.restaurant.exceptionhandle.UserExistException;
import com.tamnguyen.restaurant.repository.CustomerRepository;
import com.tamnguyen.restaurant.repository.OrderRepository;
import com.tamnguyen.restaurant.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tam Nguyen
 */

@Service
public class CustomerServiceImp implements CustomerService {


    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository, ModelMapper modelMapper){

        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    @Transactional
    public void createCustomer(CustomerDTO customerDTO) throws UserExistException{
        if (customerRepository.findCustomerByEmail(customerDTO.getEmail()).isPresent()) {
            throw new UserExistException("Account is already registered");
        } else {
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            customerRepository.save(customer);
        }
    }

    @Override
    @Transactional
    public CustomerDTO findCustomerByEmail(String email) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(email);
        try{
            if(customerOptional.isPresent()){
                Customer customer = customerOptional.get();
                return modelMapper.map(customer, CustomerDTO.class);
            }
        }catch (RuntimeException exception){
            throw new RuntimeException("Customer not found");
        }
        throw new RuntimeException("Customer not found");
    }


    @Override
    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer customer : customerRepository.findAll()){
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

}
