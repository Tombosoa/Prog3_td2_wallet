package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.User;
import com.example.td2wallet.Operation.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping(path = "/user")
    public User newUser(@RequestBody User user){
        return userOperation.add(user);
    }
}
