package com.tamnguyen.restaurant.serviceImp;

import com.tamnguyen.restaurant.dto.MenuItemDTO;
import com.tamnguyen.restaurant.entity.MenuItem;
import com.tamnguyen.restaurant.exceptionhandle.MenuItemNotFoundException;
import com.tamnguyen.restaurant.repository.MenuItemRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Tam Nguyen
 */

@Service
public class MenuItemServiceImp implements com.tamnguyen.restaurant.service.MenuItemService {


    private final MenuItemRepository menuItemRepository;
    private final ModelMapper modelMapper;


    /**
     *
     * @param menuItemRepository initializes the service with menuItemRepo and modelmapper
     *
     * @param modelMapper
     */
    @Autowired
    public MenuItemServiceImp(MenuItemRepository menuItemRepository, ModelMapper modelMapper) {
        this.menuItemRepository = menuItemRepository;
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     *
     * @param name
     * @return menuItemDTO name if found
     * @throws MenuItemNotFoundException
     */

    @Override
    @Transactional
    public MenuItemDTO findMenuItemByName(String name) throws MenuItemNotFoundException {
        Optional<MenuItem>  menuItemOptional = menuItemRepository.findMenuItemByName(name);
        if(menuItemOptional.isPresent()){
            return modelMapper.map(menuItemOptional.get(), MenuItemDTO.class);
        }else {
            throw new MenuItemNotFoundException("Item not found");
        }
    }


    /**
     *
     * @return return all item in menu from entity and convert it to menuitemDTO
     */
    @Override
    @Transactional
    public List<MenuItemDTO> getAllMenuItems() {
        List<MenuItemDTO> menuItemDTOS = new ArrayList<>();
        for(MenuItem item : menuItemRepository.findAll()){
           MenuItemDTO menuItemDTO = modelMapper.map(item, MenuItemDTO.class);
           menuItemDTOS.add(menuItemDTO);
        }
        return menuItemDTOS;
    }

    /**
     *
     * @param menuItemDTO save from menuItemDTO to menu entity
     */
    @Override
    @Transactional
    public void createMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = modelMapper.map(menuItemDTO, MenuItem.class);
        menuItemRepository.save(menuItem);
    }


    /**
     *        Allow updating menyItem based on its ID. then updated it with data from  menuitemDTO
     * @param menuItemId
     * @param menuItemDTO
     * @throws MenuItemNotFoundException throw when item not found.
     */
    @Override
    @Transactional
    public void updateMenuItem(int menuItemId, MenuItemDTO menuItemDTO) throws MenuItemNotFoundException {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItemId);
        if(menuItemOptional.isPresent()){
            MenuItem menuItem = menuItemOptional.get();
            modelMapper.map(menuItemDTO, menuItem);
            menuItemRepository.save(menuItem);
        }else {
            throw new MenuItemNotFoundException("Item not found");
        }
    }
}
