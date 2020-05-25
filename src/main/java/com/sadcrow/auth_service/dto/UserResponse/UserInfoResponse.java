package com.sadcrow.auth_service.dto.UserResponse;

import com.sadcrow.auth_service.entity.Role;
import com.sadcrow.auth_service.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfoResponse {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles = new ArrayList<>();

    public UserInfoResponse(User user) {
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.setRoles(user.getRoles());
    }
    public void setRoles(List<Role> roles){
        this.roles.addAll(roles);
    }
}
