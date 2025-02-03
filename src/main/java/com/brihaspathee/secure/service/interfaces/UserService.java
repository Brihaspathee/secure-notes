package com.brihaspathee.secure.service.interfaces;

import com.brihaspathee.secure.domain.entity.User;
import com.brihaspathee.secure.models.UserDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, February 2025
 * Time: 5:18 PM
 * Project: secure-notes
 * Package Name: com.brihaspathee.secure.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserService {

    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDto getUserById(Long id);
}
