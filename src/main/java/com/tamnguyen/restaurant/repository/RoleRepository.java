package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tam Nguyen
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {


    Role findRoleByRoleName(String expectedNameofRole);

    @Query(value = "select * from Role where role.id = (select role_id from user_roles where user_id = :id)", nativeQuery = true)
    List<Role> findRoleByUserId(int id);
}
