package com.sadcrow.auth_service.controllers;

import com.sadcrow.auth_service.dto.UserResponse.UserInfoResponse;
import com.sadcrow.auth_service.entity.User;
import com.sadcrow.auth_service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    public UserInfoResponse getUserInfoByUserName(@PathVariable String userName){
        logger.info("username" + userName);
        User user = userService.getUserInfoByUserName(userName);
        return new UserInfoResponse(user);
    }

    @GetMapping("/me")
    public UserInfoResponse getUserInfoByToken(@RequestHeader("Authorization") String authHeader){
        logger.info("me token" + authHeader);
        String token = authHeader.substring(7);
        User user = userService.getUserInfoFromToken(token);
        return new UserInfoResponse(user);
    }



    //create user

    //update user
}
