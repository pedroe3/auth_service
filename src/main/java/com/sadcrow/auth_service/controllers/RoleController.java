package com.sadcrow.auth_service.controllers;

import com.sadcrow.auth_service.entity.Role;
import com.sadcrow.auth_service.entity.User;
import com.sadcrow.auth_service.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<User> addRole(@PathVariable Long id, @RequestBody List<Role> roleList){
        User updatedUser = roleService.addRoleListById(id, roleList);
        return ResponseEntity.ok(updatedUser);
    }
}
