package com.tamnguyen.restaurant.service;

import com.tamnguyen.restaurant.dto.MenuItemDTO;
import com.tamnguyen.restaurant.dto.OrderDTO;

import java.util.List;

/**
 * @author Tam Nguyen
 */
public interface OrderService {



    List<OrderDTO> getAllOrders();

    String createOrder(OrderDTO orderDTO, List<MenuItemDTO> selectMenuItems);

}
