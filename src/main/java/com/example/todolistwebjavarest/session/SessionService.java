package com.example.todolistwebjavarest.session;

import com.example.todolistwebjavarest.auth.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {

    SessionRepository sessionDB;

    UserRepository userDB;

    SessionService(SessionRepository sessionDB, UserRepository userDB){
        this.sessionDB = sessionDB;
        this.userDB = userDB;
    }

    public UUID createSession(String username){

        int userId = userDB.findByUsername(username).get().getId();

        Session newSession = new Session(userId);
        sessionDB.save(newSession);
        return newSession.getId();
    }

}
