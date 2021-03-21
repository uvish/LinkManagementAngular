package sp_db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import sp_db.model.*;
import sp_db.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody; 

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void save(User user){
     userRepository.save(user);
    }


    public User updateUserLoginStatus(Long id, Boolean isLoggedIn) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setLoggedIn(isLoggedIn);
            userRepository.save(user.get());
        }
        return user.get();
    }

    public boolean findAllUsers(User newUser){
        List<User> users = userRepository.findAll();
        for (User user : users) {
        if (user.equals(newUser)) {
        return false;
        }
    }
    return true;
     }
}
