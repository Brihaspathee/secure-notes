package com.brihaspathee.secure.domain.repository;

import com.brihaspathee.secure.constants.AppRole;
import com.brihaspathee.secure.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 29, January 2025
 * Time: 3:32 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole appRole);
}
