package com.uvish.springApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uvish.springApi.model.*;
import com.uvish.springApi.entity.User;
import com.uvish.springApi.repository.*;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void save(UserRequestModel user) {
        User newUser = new User(user.getUsername(), user.getPassword());
        userRepository.save(newUser);
    }


    public boolean updateUserLoginStatus(UserRequestModel user) {
        long id_to_update=findExistingUserId(user);
        if(id_to_update!=0){
           User logginIn=new User(user.getUsername(), user.getPassword());
           logginIn.setId(id_to_update);
           logginIn.setLoggedIn(true);
           userRepository.save(logginIn);
           return true;
        }
        else return false;

        // Optional provides isPresent() method to check if there is a value inside
        // fn returns true if value found.

        // if (user.isPresent()) {
        //     user.get().setLoggedIn(isLoggedIn);
        //     userRepository.save(user.get());
        //     return true;
        // } else
        //     return false;

    }

    public boolean updateUserLoginStatusById(Long id, Boolean isLoggedIn) {
        Optional<User> user = userRepository.findById(id);

        // Optional provides isPresent() method to check if there is a value inside
        // fn returns true if value found.

        if (user.isPresent()) {
            user.get().setLoggedIn(isLoggedIn);
            userRepository.save(user.get());
            return true;
        } else
            return false;

    }

    public long findExistingUserId(UserRequestModel newUser) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername()) && user.getPassword().equals(newUser.getPassword())) {
               return user.getId();
                // return true;
            }
        }
        return 0;
       // return false;
    }
    public boolean findExistingUser(UserRequestModel newUser) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername()) && user.getPassword().equals(newUser.getPassword())) {
                 return true;
            }
        }
        return false;
    }

    public List<UserResponseModel> allUsers() {
        List<UserResponseModel> responseList = new ArrayList<>();
        userRepository.findAll().forEach((temp) -> {
            UserResponseModel response = new UserResponseModel();
            response.setId(temp.getId());
            response.setUsername(temp.getUsername());
            response.setLoggedIn(temp.isLoggedIn());
            responseList.add(response);
        });
        return responseList;
    }
}
