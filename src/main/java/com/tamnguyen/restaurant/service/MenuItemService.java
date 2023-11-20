package com.tamnguyen.restaurant.service;

import com.tamnguyen.restaurant.dto.MenuItemDTO;
import com.tamnguyen.restaurant.entity.MenuItem;
import com.tamnguyen.restaurant.exceptionhandle.MenuItemNotFoundException;

import java.util.List;


/**
 * @author Tam Nguyen
 */
public interface MenuItemService  {

  MenuItemDTO findMenuItemByName(String name) throws MenuItemNotFoundException;

  List<MenuItemDTO> getAllMenuItems();

  void createMenuItem( MenuItemDTO menuItemDTO);

  void updateMenuItem(int menuItemId, MenuItemDTO menuItemDTO) throws MenuItemNotFoundException;



}
