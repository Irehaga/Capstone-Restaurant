//package com.tamnguyen.restaurant.serviceTest;
//
//import com.tamnguyen.restaurant.dto.MenuItemDTO;
//import com.tamnguyen.restaurant.entity.MenuItem;
//import com.tamnguyen.restaurant.exceptionhandle.MenuItemNotFoundException;
//import com.tamnguyen.restaurant.repository.MenuItemRepository;
//import com.tamnguyen.restaurant.service.MenuItemService;
//import com.tamnguyen.restaurant.serviceImp.MenuItemServiceImp;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//import org.modelmapper.ModelMapper;
//
//
//
//
///**
// * @author Tam Nguyen
// */
//
//@SpringBootTest
// class MenuItemServiceTest {
//
//    @Mock
//    private MenuItemRepository menuItemRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private MenuItemServiceImp menuItemService;
//
//    @BeforeEach
//    public void setup() {
//        menuItemRepository = mock(MenuItemRepository.class);
//        modelMapper = new ModelMapper();
//        menuItemService = new MenuItemServiceImp(menuItemRepository, modelMapper);
//    }
//
//    @Test
//     void testCreateMenuItemDTO() {
//        MenuItemDTO menuItemDTO = new MenuItemDTO();
//          menuItemDTO.setName("bun bo");
//          menuItemDTO.setPrice(19.99);
//          menuItemDTO.setDescription("vietnamese food");
//          menuItemDTO.setImageFileName("bunbo.png");
//
//        menuItemService.createMenuItem(menuItemDTO);
//        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
//    }
//
//}
//
//
//
//
