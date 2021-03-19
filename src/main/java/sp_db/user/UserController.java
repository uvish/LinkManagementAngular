package sp_db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @CrossOrigin()
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return Status.SUCCESS;
    }

    @GetMapping("/users/all")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @CrossOrigin()
    @PutMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
         List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @CrossOrigin()
    @PutMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userRepository.save(user);
                return Status.SUCCESS;
            }
        }

        return Status.FAILURE;
    }

    @CrossOrigin()
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.SUCCESS;
    }

}