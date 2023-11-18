//package com.tamnguyen.restaurant.serviceTest;
//
//import com.tamnguyen.restaurant.dto.MenuItemDTO;
//import com.tamnguyen.restaurant.dto.OrderDTO;
//import com.tamnguyen.restaurant.entity.Order;
//import com.tamnguyen.restaurant.repository.MenuItemRepository;
//import com.tamnguyen.restaurant.repository.OrderRepository;
//import com.tamnguyen.restaurant.serviceImp.OrderServiceImp;
//import com.tamnguyen.restaurant.utility.GenerateOrderNumber;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
///**
// * @author Tam Nguyen
// */
//
//@SpringBootTest
//public class OrderServiceTest {
//
//    @Mock
//    private OrderRepository orderRepository;
//    @Mock
//    private MenuItemRepository menuItemRepository;
//    @Mock
//    private GenerateOrderNumber generateOrderNumber;
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private OrderServiceImp orderService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        // Mock setup goes here
//        when(modelMapper.map(any(), any())).thenReturn(new Order());
//        when(generateOrderNumber.generateOrderNumber()).thenReturn("12345");
//    }
//
//
//
//    @Test
//    void testCreatOrder(){
//        OrderDTO orderDTO = new OrderDTO();
//
//
//        List<MenuItemDTO> menuItemDTOs = Collections.singletonList(new MenuItemDTO());
//
//        when(generateOrderNumber.generateOrderNumber()).thenReturn("12345");
//
//        String orderNumber = orderService.createOrder(orderDTO, menuItemDTOs);
//
//        assertThat(orderNumber).isEqualTo("12345");
//        verify(orderRepository, times(1)).save(any(Order.class));
//    }
//}
