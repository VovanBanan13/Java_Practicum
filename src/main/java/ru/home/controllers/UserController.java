package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import ru.home.dto.UserDto;
import ru.home.mappers.UserMapper;
import ru.home.models.User;
import ru.home.services.UserService;

@RestController
@RequestMapping("/user")
@Api(value="user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "View a list of users")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAllUsers();

        return new ResponseEntity<>(userMapper.toUserDtos(users), HttpStatus.OK);
    }

    @ApiOperation(value = "View information about the user")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer id) {
        User user = userService.getById(id);

        return new ResponseEntity<>(userMapper.entityToDto(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new user")
    @PostMapping(produces = "application/json")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        userService.save(user);

        return new ResponseEntity<>(userMapper.entityToDto(user), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update user information")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        User changedUser = userService.update(id, user);

        return new ResponseEntity<>(userMapper.entityToDto(changedUser), HttpStatus.OK);
    }

    @ApiOperation(value = "Remote user")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Integer id) {
        User user = userService.getById(id);

        userService.delete(id);

        return new ResponseEntity<>(userMapper.entityToDto(user),HttpStatus.NO_CONTENT);
    }
}
