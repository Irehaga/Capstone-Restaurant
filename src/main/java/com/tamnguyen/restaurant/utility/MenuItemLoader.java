package com.tamnguyen.restaurant.utility;

import com.tamnguyen.restaurant.dto.MenuItemDTO;
import com.tamnguyen.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Tam Nguyen
 */

@Component
public class MenuItemLoader  implements CommandLineRunner {

    @Autowired
    private MenuItemService menuItemService;

    @Override
    public void run(String... args) throws Exception {
        loadMenuItems();
    }

    private void loadMenuItems(){
        menuItemService.createMenuItem(new MenuItemDTO("Pho", "Aromatic Vietnamese noodle soup with beef", 9.99, "pho.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Banh Mi", "Vietnamese sandwich with various fillings", 5.99, "banh-mi.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Goi Cuon", "Fresh spring rolls with shrimp and herbs", 6.99, "goi-cuon.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Bun Cha", "Grilled pork served over rice vermicelli", 8.99, "bun-cha.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Banh Xeo", "Sizzling Vietnamese crepes filled with pork and shrimp", 7.99, "banh-xeo.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Ca Phe Sua Da", "Vietnamese iced coffee with condensed milk", 3.99, "ca-phe-sua-da.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Banh Cuon", "Steamed rice rolls filled with minced pork and mushrooms", 7.99, "banh-cuon.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Mi Quang", "Turmeric noodles with shrimp, pork, and quail eggs", 8.99, "mi-quang.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Cha Gio", "Crispy Vietnamese fried spring rolls", 6.99, "cha-gio.jpg"));
        menuItemService.createMenuItem(new MenuItemDTO("Che", "Vietnamese sweet dessert soup with various ingredients", 4.99, "che.jpg"));
    }

}
