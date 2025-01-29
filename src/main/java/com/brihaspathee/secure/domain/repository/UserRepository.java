package com.brihaspathee.secure.domain.repository;

import com.brihaspathee.secure.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, January 2025
 * Time: 3:30 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
