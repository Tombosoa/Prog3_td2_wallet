package com.example.td2wallet.Controller;

import com.example.td2wallet.Entity.User;
import com.example.td2wallet.Operation.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
        return  userOperation.findAll();
    }

    @GetMapping("/user/{id}")
    public User getOneUser(@PathVariable("id") UUID id){
        return userOperation.getOne(id);
    }

    @PostMapping(path = "/user")
    public User newUser(@RequestBody User user){
        return userOperation.save(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user){
        return userOperation.update(user);
    }

    @PostMapping("/saveAll")
    public List<User> saveAllUsers(@RequestBody List<User> users) {
        return userOperation.saveAll(users);
    }

    @DeleteMapping(path = "/users/{uid}")
    public void deleteUser(@PathVariable("uid") UUID uid){
        userOperation.deleteUser(uid);
    }
}
