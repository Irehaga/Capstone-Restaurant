package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.Role;
import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.repository.RoleRepository;
import com.tamnguyen.restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    User user;
    Role role;


    @BeforeEach
    public void init(){

        role = new Role();
        role.setRoleName("CUSTOMER");
        roleRepository.save(role);

        user = new User();
        user.setEmail("test@test.com");
        user.setPassword("1234567");
        user.setFirstName("Tam");
        user.setLastName("nguyen");
        user.setPhoneNumber("000-111-222");
        user.setRoles(Arrays.asList(new Role("Customer")));


    }










}//end of test
