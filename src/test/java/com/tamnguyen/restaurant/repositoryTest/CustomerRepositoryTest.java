package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.Customer;
import com.tamnguyen.restaurant.repository.CustomerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
 class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer customer;

    @BeforeEach
    public void init() {

        customer = new Customer();
        customer.setEmail("helloWorld@hello.com");
        customer.setAddress("23432432432");
        customer.setFirstName("Mongo");
        customer.setLastName("nguyen");
        customer.setPhoneNumber("23425465646");

        customerRepository.save(customer);
    }

    @Test
    @Transactional
    void testFindCustomerByEmail() {
        Optional<Customer> foundCustomer = customerRepository.findCustomerByEmail("helloWorld@hello.com");
        assertThat(foundCustomer).isPresent();
        assertThat(foundCustomer.get().getEmail()).isEqualTo("helloWorld@hello.com");
    }

}