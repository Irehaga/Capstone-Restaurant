package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.Customer;
import com.tamnguyen.restaurant.entity.MenuItem;
import com.tamnguyen.restaurant.entity.Order;
import com.tamnguyen.restaurant.repository.CustomerRepository;
import com.tamnguyen.restaurant.repository.MenuItemRepository;
import com.tamnguyen.restaurant.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import com.tamnguyen.restaurant.enums.OrderStatus;


/**
 * @author Tam Nguyen
 */


@SpringBootTest
 class OrderRepositoryTest {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    private Order order;

    @BeforeEach
     void init() {
        Customer customer = new Customer();
        customerRepository.save(customer);

        MenuItem menuItem = new MenuItem();
        menuItemRepository.save(menuItem);

        order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderNumber("12345");
        order.setItems(Collections.singletonList(menuItem));
        order.setTotalAmount(100.0);

        orderRepository.save(order);
    }

    @Test
    @Transactional
    void testFindOrderById() {
        Order foundOrder = orderRepository.findById(order.getId()).orElse(null);

        assertThat(foundOrder).isNotNull();
        assertThat(foundOrder.getOrderNumber()).isEqualTo(order.getOrderNumber());
    }
}
