package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.MenuItem;
import com.tamnguyen.restaurant.repository.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
 class MenuItemRepositoryTest {



    @Autowired
    private MenuItemRepository menuItemRepository;

    private MenuItem menuItem;

    @BeforeEach
    public void init() {
        // Create and save a new menu item
        menuItem = new MenuItem();
        menuItem.setName("Pho Bo");
        menuItem.setDescription("Delicious Vietnamese beef noodle soup");
        menuItem.setPrice(12.99);
        menuItem.setImageFileName("pho-bo.jpg");

        menuItemRepository.save(menuItem);
    }

    @Test
    @Transactional
    void whenFindMenuItemByName_thenReturnMenuItem() {
        Optional<MenuItem> foundMenuItem = menuItemRepository.findMenuItemByName("Pho Bo");
        assertThat(foundMenuItem).isPresent();
        assertThat(foundMenuItem.get().getName()).isEqualTo("Pho Bo");

    }

}
