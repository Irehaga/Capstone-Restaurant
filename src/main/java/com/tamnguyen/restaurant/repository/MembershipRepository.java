package com.tamnguyen.restaurant.repository;

import com.tamnguyen.restaurant.entity.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tam Nguyen
 */

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Integer> {
}
