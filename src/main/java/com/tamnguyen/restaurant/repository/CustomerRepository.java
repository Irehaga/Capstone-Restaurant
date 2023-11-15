package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tam Nguyen
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findCustomerByEmail(String email);


}

