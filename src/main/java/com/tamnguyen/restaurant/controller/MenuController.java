package com.tamnguyen.restaurant.controller;

import com.tamnguyen.restaurant.dto.MenuItemDTO;
import com.tamnguyen.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Tam Nguyen
 */

@Controller
public class MenuController {

    @Autowired
    private MenuItemService menuItemService;


    @GetMapping("/menu/add")
    public String showAddMenuItemForm(Model model){
        model.addAttribute("menuItem", new MenuItemDTO());
        return "add-menu-item";
    }

    @PostMapping("/menu/add")
    public String addMenItem(@ModelAttribute("menuitem") MenuItemDTO menuItemDTO){
        menuItemService.createMenuItem(menuItemDTO);
        return "redirect:/menu/add";
    }


    @GetMapping("/menu")
    public String showMenu(Model model){
        List<MenuItemDTO> menuItemDTOS = menuItemService.getAllMenuItems();
        model.addAttribute("menuItems", menuItemDTOS);
        return "menu";
    }
}// end of menu controller
