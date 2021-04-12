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

    public String findUsername(String email){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ( user.getEmail().equals(email) ) {
               return user.getUsername();
            }
        }
        return "NA";
    }
    public long findId(String email){
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ( user.getEmail().equals(email) ) {
                return user.getId();
            }
        }
        return 0;
    }

    public void save(UserRequestModel user) {
        User newUser = new User(user.getUsername(),user.getEmail(), user.getPassword(),user.getSalutation());
        userRepository.save(newUser);
    }


    public boolean updateUserLoginStatus(UserRequestModel user) {
        User id_to_update=findExistingUser(user);
        if(id_to_update!=null){
            id_to_update.setLoggedIn(true);
           userRepository.save(id_to_update);
           return true;
        }
        else return false;
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

    public User findExistingUser(UserRequestModel newUser) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ( user.getEmail().equals(newUser.getEmail()) && user.getPassword().equals(newUser.getPassword()) ) {
               return user;
            }
        }
        return null;
    }
    public boolean findExisting(UserRequestModel newUser) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if ( user.getEmail().equals(newUser.getEmail()) ) {
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
            response.setSalutation(temp.getSalutation());
            response.setEmail(temp.getEmail());
            responseList.add(response);
        });
        return responseList;
    }
}
