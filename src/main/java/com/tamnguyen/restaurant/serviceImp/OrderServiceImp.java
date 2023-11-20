package com.tamnguyen.restaurant.serviceImp;

import com.tamnguyen.restaurant.dto.MenuItemDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;
import com.tamnguyen.restaurant.entity.MenuItem;
import com.tamnguyen.restaurant.entity.Order;
import com.tamnguyen.restaurant.enums.OrderStatus;
import com.tamnguyen.restaurant.repository.MenuItemRepository;
import com.tamnguyen.restaurant.repository.OrderRepository;
import com.tamnguyen.restaurant.service.OrderService;
import com.tamnguyen.restaurant.utility.GenerateOrderNumber;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tam Nguyen
 */
@Service
public class OrderServiceImp  implements OrderService {


    private final OrderRepository orderRepository;

    private final MenuItemRepository menuItemRepository;
    private final GenerateOrderNumber generateOrderNumber;
    ModelMapper modelMapper;


    /**
     * @param orderRepository to handle order-related database operation.
     * @param menuItemRepository to handle menu items
     * @param generateOrderNumber giving order number for each order
     * @param modelMapper  Utility to map between DTO and entity classes.
     */
    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, MenuItemRepository menuItemRepository,
                           GenerateOrderNumber generateOrderNumber, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.generateOrderNumber = generateOrderNumber;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    /**
     *
     * orderRepository.finaAll() get all the order entities from database
     * Stream() and map() used to converts each order entities to orderDTO
     *
     * @return collect(Collectors.toList()) gather the converted DTO into a list and returned.
     */

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        Iterable<Order> ordersIterable = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        ordersIterable.forEach(orders::add);
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }


    /**
     *
     * @param orderDTO  to handle the order of which customer choose to buy
     * @param selectMenuItems getting menu item
     * @return an order number that be used to find the other of a customer.
     */
    @Override
    @Transactional
    public String createOrder(OrderDTO orderDTO, List<MenuItemDTO> selectMenuItems) {

        String orderNumber = generateOrderNumber.generateOrderNumber();
        Order order = buildOrder(orderDTO, selectMenuItems, orderNumber);
        orderRepository.save(order);
        return orderNumber;
    }


    /**
     *
     * @param orderDTO getting order data and send to order entities
     * @param selectItems list of item DTO
     * @param orderNumber order number that created to keep track of
     * @return
     */
    private Order buildOrder(OrderDTO orderDTO, List<MenuItemDTO> selectItems, String orderNumber){
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setStatus(OrderStatus.NEW);
        order.setOrderDate(LocalDateTime.now());
        modelMapper.map(orderDTO, order);
        //mapped item to menuitem
        List<MenuItem> menuItems = mapMenuItemDTOS(selectItems);
        double totalAmount = calculateTotalAmount(menuItems);
        order.setTotalAmount(totalAmount);
        order.setItems(menuItems);
        return order;
    }

    /**
     *
     * @param menuItemDTOS using menudtos to map data to menuitem entity
     * @return
     */
    private List<MenuItem> mapMenuItemDTOS(List<MenuItemDTO> menuItemDTOS){
        return menuItemDTOS.stream()
                .map(menuItemDTO -> modelMapper.map(menuItemDTO, MenuItem.class))
                .collect(Collectors.toList());
    }


    /**
     *
     * @param menuItems list of item uses to find the price
     *                  of an order and sum them up.
     * @return total price for the order
     */
    private double calculateTotalAmount(List<MenuItem> menuItems){
        return menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }

}//end of class
