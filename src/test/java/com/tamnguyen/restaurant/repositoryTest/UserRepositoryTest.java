package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
public class UserRepositoryTest {






    @Autowired
    private UserRepository userRepository;

    @BeforeEach
     void init() {

        User user = new User("test@example.com", "password", "true");
        userRepository.save(user);
    }

    @Test
    @Transactional
    void testFindUserByEmail() {

        Optional<User> foundUser = userRepository.findUserByEmail("test@example.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("test@example.com");
    }
}
