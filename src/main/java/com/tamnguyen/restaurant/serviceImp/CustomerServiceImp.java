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


    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    public CustomerServiceImp(CustomerRepository customerRepository, OrderRepository orderRepository){

        this.customerRepository = customerRepository;
        this.orderRepository    = orderRepository;
    }

    @Override
    @Transactional
    public void createCustomer(CustomerDTO customerDTO, List<OrderDTO> orderDTOs) throws UserExistException{
        if (customerRepository.findCustomerByEmail(customerDTO.getEmail()).isPresent()) {
            throw new UserExistException("Account is already registered");
        } else {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            List<Order> orders = new ArrayList<>();
            for (OrderDTO orderDTO : orderDTOs) {
                Order order = modelMapper.map(orderDTO, Order.class);
                orders.add(order);
            }

            orderRepository.saveAll(orders);
            customer.setOrders(orders);
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
                ModelMapper modelMapper = new ModelMapper();
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            customerDTOS.add(customerDTO);
        }

        return customerDTOS;
    }

}
