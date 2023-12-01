package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.User;
import com.example.td2wallet.Operation.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UserController {
    private final UserOperation userOperation;

    @Autowired
    public UserController(UserOperation userOperation) {
        this.userOperation = userOperation;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return  userOperation.getAll();
    }


    public User crupdateUser(User user){
        return userOperation.add(user);
    }
}
