package com.example.todolistwebjavarest;

import com.example.todolistwebjavarest.auth.User;
import com.example.todolistwebjavarest.auth.UserService;
import com.example.todolistwebjavarest.session.Session;
import com.example.todolistwebjavarest.session.SessionService;
import com.example.todolistwebjavarest.todo.Todo;
import com.example.todolistwebjavarest.todo.TodoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping(path = "/todo")
public class Controller {

    TodoService todoService;

    UserService userService;

    SessionService sessionService;


    Controller(TodoService todoService, UserService userService, SessionService sessionService){
        this.todoService = todoService;
        this.userService = userService;
        this.sessionService = sessionService;
    }

    //TODO add sessionId handling
    @PostMapping
    public Todo addTodo(@RequestBody Todo todo, @CookieValue(value = "sessionId",defaultValue = "0") String sessionId_string){

        return todoService.addTodo(todo,UUID.fromString(sessionId_string));

//        if(username.equals("noname")) {
//            System.out.println("no username, returning new Todo()...");
//            return new Todo();
//        }
//        return todoService.addTodo(todo,username);
    }


    @PostMapping(path = "/auth/reg")
    public JsonResponse register(HttpServletResponse servletResponse, @RequestBody User user){

        String authResponse = userService.register(user);

        if(authResponse.equals("ok")) {
            Cookie sessionIdCookie = new Cookie("sessionId",sessionService.createSession(user.getUsername()).toString());
            sessionIdCookie.setPath("/");
            servletResponse.addCookie(sessionIdCookie);
        }


        return new JsonResponse(authResponse);
    }


    @PostMapping(path = "/auth/login")
    public JsonResponse login(HttpServletResponse servletResponse,@RequestBody User user){
        String authResponse = userService.login(user);

        if(authResponse.equals("ok")) {
            Cookie sessionIdCookie = new Cookie("sessionId",sessionService.createSession(user.getUsername()).toString());
            sessionIdCookie.setPath("/");
            servletResponse.addCookie(sessionIdCookie);
        }


        return new JsonResponse(authResponse);
    }

    @DeleteMapping
    public JsonResponse deleteTodo(@CookieValue(name = "sessionId",defaultValue = "0") String sessionId,
                                   @RequestParam(name= "todoId") int todoId){
        return new JsonResponse(todoService.deleteTodo(UUID.fromString(sessionId), todoId));
    }

    @PatchMapping
    public JsonResponse shareTodo(@CookieValue(name = "sessionId", defaultValue = "0") String sessionId,
                                  @RequestParam(name = "todoId") int todoId,
                                  @RequestParam(name= "recipient") String recipientUsername){
        return new JsonResponse(todoService.shareTodo(UUID.fromString(sessionId), recipientUsername, todoId));
    }

    @GetMapping
    public ArrayList<Todo> getTodos(@CookieValue(name= "sessionId", defaultValue ="0") String sessionId){
        return todoService.getTodos(UUID.fromString(sessionId));
    }


}
