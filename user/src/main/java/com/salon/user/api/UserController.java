package com.salon.user.api;
import com.salon.user.repo.model.User;
import com.salon.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public final class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<com.salon.user.repo.model.User>> index() {
        final List<com.salon.user.repo.model.User> users = userService.fetchAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.salon.user.repo.model.User> show(@PathVariable long id) {
        try {
            final com.salon.user.repo.model.User user = userService.fetchById(id);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.salon.user.api.dto.User user) {
        final String firstname = user.getFirstname();
        final String lastname = user.getLastname();
        final String email = user.getEmail();
        final String login = user.getLogin();
        final String password = user.getPassword();
        final String phoneNumber = user.getPhoneNumber();
        final long userId = userService.create(firstname, lastname, email, login,  password, phoneNumber);
        final String userUri = String.format("/users/%d", userId);

        return ResponseEntity.created(URI.create(userUri)).build();
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody  com.salon.user.api.dto.User user) {
        final String email = user.getEmail();
        final String phoneNumber = user.getPhoneNumber();
        try {
            userService.updateUser(id , email,phoneNumber);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}