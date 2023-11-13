package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tam Nguyen
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {





}
