package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tam Nguyen
 */

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {


    Optional<User> findUserByEmail(String email);

}
