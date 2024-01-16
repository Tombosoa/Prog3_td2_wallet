package com.example.td2wallet.reff;

import com.example.td2wallet.Entity.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class UserAutoCrud<U> extends Service<User>{
    public UserAutoCrud(Class<User> entityClass) {
        super(entityClass);
    }

    public List<User> findAll(){
        Service<User> userService = Service.createService(User.class);

        List<User> users = userService.findAll();
        return users;
    }

    public static void main(String[] args) {
        Service<User> userService = Service.createService(User.class);

        List<User> users = userService.findAll();
        User toSave = new User();
        toSave.setUsername("username");
        toSave.setEmail("username@gmail.com");
        toSave.setPassword("password");

        System.out.println(userService.save(toSave));
        System.out.println(users);
    }
}
