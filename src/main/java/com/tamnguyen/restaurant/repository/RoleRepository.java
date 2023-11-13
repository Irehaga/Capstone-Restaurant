package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tam Nguyen
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findRoleByroleName(String name);

}
