package com.uvish.springApi.controller;

import java.util.List;
import javax.validation.Valid;
import com.uvish.springApi.model.*;
import com.uvish.springApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin()    //By default allows access to request from all origin . To restrict set: @CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


        /*
         Register User with Request Body Ex:
           {
             "username": "xxxx@yyyy.com",
             "password": "123"
           }
        */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestModel newUser) {
        if(!userService.findExistingUser(newUser)){
            userService.save(newUser);
            return new ResponseEntity<>("Registered",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Already Exist",HttpStatus.CONFLICT);
       }
   
    @PutMapping("/login/{id}")    // login with 'id' as path variable 'http://localhost:8080/users/login/5' 
    public ResponseEntity<String> loginUser(@PathVariable("id") Long id) {
        if(id == null){
            return new ResponseEntity<>("Invalid Id",HttpStatus.BAD_REQUEST);
        }else{
        if(userService.updateUserLoginStatus(id, true))return new ResponseEntity<>("Logged in",HttpStatus.OK);
        else return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<UserResponseModel> getAllUsers(){
      return userService.allUsers();
    }

    @PutMapping("/logout/{id}")   // logout with 'id' as path variable 'http://localhost:8080/users/logout/5' 
    public ResponseEntity<String> logUserOut(@PathVariable("id") Long id) {
        if (userService.updateUserLoginStatus(id, false)) {
            return new ResponseEntity<>("Logged Out", HttpStatus.OK);
        } else
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }


}