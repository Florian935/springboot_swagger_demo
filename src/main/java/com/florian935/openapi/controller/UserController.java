package com.florian935.openapi.controller;

import com.florian935.openapi.domain.User;
import com.florian935.openapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1.0/users")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    User getUserById(@PathVariable String id) {

        return userService.getById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    User saveOneUser(@RequestBody User user) {

        return userService.save(user);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    void deleteUserById(@PathVariable String id) {

        userService.deleteUserById(id);
    }

    @PutMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    User updateUser(@PathVariable String id, @RequestBody User user) {

        return userService.updateUser(id, user);
    }
}
