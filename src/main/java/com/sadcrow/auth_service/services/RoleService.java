package com.sadcrow.auth_service.services;

import com.sadcrow.auth_service.entity.Role;
import com.sadcrow.auth_service.entity.User;
import com.sadcrow.auth_service.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private UserRepository userRepository;
    //add role
    public User addRole(User user, Role role){
        Optional<User> userToUpdate = userRepository.findByUserName(user.getUserName());
        userToUpdate.get().addRole(role);
        userRepository.flush();
        return userToUpdate.get();
    }

    //add role list
    public User addRoleList(User user, List<Role> roleList){
        Optional<User> userToUpdate = userRepository.findByUserName(user.getUserName());
        userToUpdate.get().addRoleList(roleList);
        userRepository.flush();
        return userToUpdate.get();
    }

    public User addRoleListById(Long userId, List<Role> roleList){
        Optional<User> userToUpdate = userRepository.findById(userId);
        userToUpdate.get().addRoleList(roleList);
        userRepository.flush();
        return userToUpdate.get();
    }

    //remove role
    public Boolean removeRole(Role role){
        return false;
    }
}
