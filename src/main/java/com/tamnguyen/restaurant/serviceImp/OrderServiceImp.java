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

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, MenuItemRepository menuItemRepository, GenerateOrderNumber generateOrderNumber, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.generateOrderNumber = generateOrderNumber;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        return null;
    }

    @Override
    @Transactional
    public String createOrder(OrderDTO orderDTO, List<MenuItemDTO> selectMenuItems) {

        String orderNumber = generateOrderNumber.generateOrderNumber();
        Order order = buildOrder(orderDTO, selectMenuItems, orderNumber);
        orderRepository.save(order);
        return orderNumber;
    }



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

    private List<MenuItem> mapMenuItemDTOS(List<MenuItemDTO> menuItemDTOS){
        return menuItemDTOS.stream()
                .map(menuItemDTO -> modelMapper.map(menuItemDTO, MenuItem.class))
                .collect(Collectors.toList());
    }


    private double calculateTotalAmount(List<MenuItem> menuItems){
        return menuItems.stream().mapToDouble(MenuItem::getPrice).sum();
    }

}//end of class
