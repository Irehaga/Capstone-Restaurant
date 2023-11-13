package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
public class UserRepositoryTest {

    User user;



    @BeforeEach
    public void init(){

        user = new User();
        user.setEmail("");
    }
}
