package com.example._22_Cache.user;

import com.example._22_Cache.exceptions.NullUserException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users")
    public List<User> getUsers() throws InterruptedException {
        return userService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable String id) throws InterruptedException, NullUserException {
        return userService.getUser(id);
    }
}
