package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tam Nguyen
 */

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
