package com.tamnguyen.restaurant.serviceImp;

import com.tamnguyen.restaurant.entity.Role;
import com.tamnguyen.restaurant.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tam Nguyen
 */
@Service
public class RoleServiceImp implements com.tamnguyen.restaurant.service.RoleService{


    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository repository){
        this.roleRepository = repository;
    }


    @Override
    @Transactional
    public void saveRoles(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {

        return roleRepository.findRoleByRoleName(name);
    }

    @Override
    public List<Role> getRolesByUserID(int id) {
        return  roleRepository.findRoleByUserId(id);
    }
}
