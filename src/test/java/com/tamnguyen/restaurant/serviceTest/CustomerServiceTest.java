package com.tamnguyen.restaurant.serviceTest;

import com.tamnguyen.restaurant.dto.CustomerDTO;
import com.tamnguyen.restaurant.entity.Customer;
import com.tamnguyen.restaurant.exceptionhandle.UserExistException;
import com.tamnguyen.restaurant.repository.CustomerRepository;
import com.tamnguyen.restaurant.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
public class CustomerServiceTest {


    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        when(customerRepository.findCustomerByEmail("existing@example.com"))
                .thenReturn(Optional.of(new Customer()));
        when(customerRepository.findCustomerByEmail("new@example.com"))
                .thenReturn(Optional.empty());

        doAnswer(invocation -> null).when(customerRepository).save(any());
    }

    @Test
    void testCreateCustomer() throws UserExistException {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setAddress("234234 45th ave");
        customerDTO.setEmail("hello@hello.com");
        customerDTO.setFirstName("Pico");
        customerDTO.setLastName("Loco");
        customerDTO.setPhoneNumber("345345435");
        customerService.createCustomer(customerDTO);
        verify(customerRepository, times(1)).save(any());
    }


}
