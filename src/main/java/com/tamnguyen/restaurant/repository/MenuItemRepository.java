package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tam Nguyen
 */

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
}
