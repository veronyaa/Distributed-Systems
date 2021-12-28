package com.salon.user.service;

import com.salon.user.exception.UserNotFoundException;
import com.salon.user.repo.UserRepository;
import com.salon.user.repo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User saveUsers(User newUser) {
        return userRepository.save(newUser);
    }

    public User getUserById(Long id_user) {
        Optional<User> user = userRepository.findById(id_user);
        if (user.isPresent()) {
            log.info("user: {}", user.get());
            return user.get();
        }
        throw new UserNotFoundException();
    }

    public User updateUserById(Long id_user, User updatedUser) {
        Optional<User> user = userRepository.findById(id_user);
        if (user.isPresent()) {
            User oldUser = user.get();
            log.info("user: {}", oldUser);
            updateUser(oldUser, updatedUser);
            return userRepository.save(oldUser);
        }
        throw new UserNotFoundException();
    }

    private void updateUser(User oldUser, User updatedUser) {
        oldUser.setFirstname(updatedUser.getFirstname());
        oldUser.setLastname(updatedUser.getLastname());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setLogin(updatedUser.getLogin());
        oldUser.setPassword(updatedUser.getPassword());
        oldUser.setPhoneNumber(updatedUser.getPhoneNumber());
    }

    public String deleteUserById(Long id_user) {
        userRepository.deleteById(id_user);
        return "User was successfully deleted!";
    }

}