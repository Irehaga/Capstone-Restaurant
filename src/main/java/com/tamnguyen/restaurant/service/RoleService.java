package com.tamnguyen.restaurant.service;

import com.tamnguyen.restaurant.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tam Nguyen
 */
@Service
public interface RoleService {


void saveRoles(Role role);
Role findRoleByRoleName(String name);
List<Role> getRolesByUserID(int id);


}
