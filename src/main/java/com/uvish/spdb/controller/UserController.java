package com.uvish.spdb.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uvish.spdb.model.*;
import com.uvish.spdb.model.UserRequestModel;
import com.uvish.spdb.service.UserService;



@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin()
    @PostMapping("/register")
    public Status registerUser(@Valid @RequestBody UserRequestModel newUser) {
     if(userService.findAllUsers(newUser)){
         userService.save(newUser);
         return Status.SUCCESS;
     }
     else return Status.USER_ALREADY_EXISTS;
    }

    // userRepository.save(newUser);
    // return Status.SUCCESS;
    // }

    // @GetMapping("/users/all")
    // public List<User> getAllUsers() {
    // return userRepository.findAll();
    // }

    @CrossOrigin()
    @PutMapping("/login/{id}")
    public Status loginUser(@PathVariable("id") Long id) {
        userService.updateUserLoginStatus(id, true);
        return Status.SUCCESS;
    }

    // TODO logout
    @CrossOrigin()
    @PutMapping("/logout/{id}")
    public User logUserOut(@PathVariable("id") Long id) {
       return userService.updateUserLoginStatus(id, false);
    //    Optional<User> user= userService.getUserById(id);
    //    User found=userService.find
    //       if(user==null){
    //           return Status.FAILURE;
    //       }
    //       else{
    //        user.setLoggedIn(false);
    //       }
    }

    // @CrossOrigin()
    // @DeleteMapping("/users/all")
    // public Status deleteUsers() {
    // userRepository.deleteAll();
    // return Status.SUCCESS;
    // }

}