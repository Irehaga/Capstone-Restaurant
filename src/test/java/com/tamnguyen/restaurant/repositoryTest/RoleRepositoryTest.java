package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.Role;
import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.repository.RoleRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleRepositoryTest {

    @Autowired
    RoleRepository roleRepository;

   Role role;

    @BeforeEach
    public void init(){

        role = new Role();
        role.setRoleName("CUSTOMER");
    }

    @Test
    @Order(1)
    void testSaveRole(){
        roleRepository.save(role);
        Assertions.assertTrue(roleRepository.count() > 0);
    }

    @Test
    @Order(2)
    void testFindRoleByName(){

        String expectedNameofRole = "CUSTOMER";
       Role actualRole =  roleRepository.findRoleByRoleName(expectedNameofRole);
        Assertions.assertEquals(expectedNameofRole, actualRole.getRoleName());
    }

}
