package com.tamnguyen.restaurant.repositoryTest;

import com.tamnguyen.restaurant.entity.Membership;
import com.tamnguyen.restaurant.entity.User;
import com.tamnguyen.restaurant.enums.MembershipStatus;
import com.tamnguyen.restaurant.repository.MembershipRepository;
import com.tamnguyen.restaurant.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import com.tamnguyen.restaurant.enums.MembershipType;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Tam Nguyen
 */

@SpringBootTest
public class MembershipRepositoryTest {

    @Autowired
    private MembershipRepository membershipRepository;
    @Autowired
    private UserRepository userRepository;

    private Membership membership;

    @BeforeEach
    public void init() {

        User user = new User();
        userRepository.save(user);
        membership = new Membership();
        membership.setType(MembershipType.GOLD);
        membership.setStartDate(LocalDate.now());
        membership.setEndDate(LocalDate.now().plusYears(1));
        membership.setStatus(MembershipStatus.ACTIVE);
        membership.setUser(user);

        membershipRepository.save(membership);
    }

    @Test
    @Transactional
    public void testFindMembershipById() {
        Membership foundMembership = membershipRepository.findById(membership.getId()).orElse(null);

        assertThat(foundMembership).isNotNull();
        assertThat(foundMembership.getId()).isEqualTo(membership.getId());
    }

}
