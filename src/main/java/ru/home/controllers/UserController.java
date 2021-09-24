package ru.home.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.UserDTO;
import ru.home.mappers.UserMapper;
import ru.home.models.User;
import ru.home.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(userMapper.toUserDTOS(users), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        User user = userService.getById(id);

        return new ResponseEntity<>(userMapper.EntityToDto(user), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        userService.save(user);

        return new ResponseEntity<>(userMapper.EntityToDto(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User changedUser = userService.getById(id);

        changedUser.setName(user.getName());
        changedUser.setRole(user.getRole());

        userService.save(changedUser);
        return new ResponseEntity<>(userMapper.EntityToDto(changedUser), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Integer id) {
        User user = userService.getById(id);

        userService.delete(id);

        return new ResponseEntity<>(userMapper.EntityToDto(user),HttpStatus.NO_CONTENT);
    }
}
