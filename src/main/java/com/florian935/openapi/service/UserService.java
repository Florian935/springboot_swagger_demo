package com.florian935.openapi.service;

import com.florian935.openapi.domain.User;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE)
public class UserService {

    List<User> users = new ArrayList<>() {{
        add(new User("0", "User 1"));
        add(new User("1", "User 2"));
        add(new User("2", "User 3"));
        add(new User("3", "User 4"));
    }};

    public List<User> getAllUsers() {

        return users;
    }

    @SneakyThrows
    public User getById(String id) {

        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("No user with this id"));
    }

    public User save(User user) {

        this.users.add(user);

        return user;
    }

    public void deleteUserById(String id) {

        this.users = users.stream()
                .filter(user -> !user.getId().equals(id))
                .toList();
    }

    public User updateUser(String id, User user) {
        user.setId(id);

        users = users.stream()
                .map(u -> user.getId().equals(u.getId()) ? user : u)
                .toList();

        return user;
    }
}
