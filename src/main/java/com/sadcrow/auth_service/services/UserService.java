package com.sadcrow.auth_service.services;

import com.sadcrow.auth_service.configuration.JwtUtil;
import com.sadcrow.auth_service.error.UserNotFoundException;
import com.sadcrow.auth_service.repositories.UserRepository;
import com.sadcrow.auth_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getUserInfoByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    public User getUserInfoByUserName(String userName){
       Optional<User> user = userRepository.findByUserName(userName);
        if(!user.isPresent()){
            throw new UserNotFoundException("user not found");
        }
        return user.get();
    }

    //cache put
    public User createUser(User user){
        return userRepository.save(user);
    }

    //cache put
    public User updateUser(User user){
       Optional<User> userToUpdate = userRepository.findById(user.getId());
       if(!userToUpdate.isPresent())
           throw new UserNotFoundException("User Not Found.");

       userToUpdate.get().setEmail(user.getEmail());
       userToUpdate.get().setFirstName(user.getFirstName());
       userToUpdate.get().setLastName(user.getLastName());

       userRepository.flush();

       return userToUpdate.get();
    }

    public User getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("user not found");
        }
        return user.get();
    }

    public String getUserNameFromToken(String token){
        return jwtUtil.extractUsername(token);
    }

    public User getUserInfoFromToken(String token){
        String userName = jwtUtil.extractUsername(token);
        return this.getUserInfoByUserName(userName);
    }
}
