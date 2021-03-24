package com.uvish.spdb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import com.uvish.spdb.model.*;
import com.uvish.spdb.repository.*;
import org.springframework.web.bind.annotation.RequestBody; 

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    

    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void save(UserRequestModel user){
        User newUser=new User(user.getUsername(),user.getPassword());
        userRepository.save(newUser);
    }


    public User updateUserLoginStatus(Long id, Boolean isLoggedIn) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setLoggedIn(isLoggedIn);
            userRepository.save(user.get());
        }
        return user.get();
    }

    public boolean findAllUsers(UserRequestModel newUser){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getUsername().equals(newUser.getUsername()) && user.getPassword().equals(newUser.getPassword())){
        // if (user.equals(newUser)) {
        return true;
        }
    }
    return false;
     }
}
