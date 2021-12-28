package com.salon.user.api;


import com.salon.user.repo.model.User;
import com.salon.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import net.minidev.json.JSONObject;
@RestController
public class UserController {
    private final UserService userService;
    private final RestTemplate restTemplate;

    @GetMapping("/")
    public String order() throws JsonParseException
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Hello, I am user service");
        return jsonObject.toString();
    }
    @Autowired
    public UserController(UserService userService, RestTemplate restTemplate)
    {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }
    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getusers()
    {
        return ResponseEntity.ok(userService.getUsers());
    }
    @PostMapping(value = "/user")
    public ResponseEntity<User> postUsers(@Valid @RequestBody User newUser) {
        return ResponseEntity.ok(userService.saveUsers(newUser));
    }
    @GetMapping(value = "/user/{id_user}")
    public ResponseEntity<User> getUserRole(@PathVariable Long id_user) {
        return ResponseEntity.ok(userService.getUserById(id_user));
    }

    @PutMapping(value = "/user/{id_user}")
    public ResponseEntity<User> updateStatus(@PathVariable Long id_user, @Valid @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUserById(id_user, updatedUser));
    }

    @DeleteMapping(value = "/user/{id_user}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id_user) {
        return ResponseEntity.ok(userService.deleteUserById(id_user));
    }
}
