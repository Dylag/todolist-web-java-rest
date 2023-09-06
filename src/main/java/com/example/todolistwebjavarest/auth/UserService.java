package com.example.todolistwebjavarest.auth;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userDB;

    public UserService(UserRepository db){
        this.userDB = db;
    }

    public String register(User newUser){
        if(userDB.findByName(newUser.getUsername()).isPresent())
            return "Nickname is already taken";
        userDB.save(newUser);
        return "ok";
    }

    public String login(User user){
        Optional<User> possibleUser = userDB.findByName(user.getUsername());
        if(userDB.findByName(user.getUsername()).isEmpty())
            return "No user with this nickname";

        if(!possibleUser.get().getPassword().equals(user.getPassword()))
            return "Wrong password";

        return "ok";
    }

}
