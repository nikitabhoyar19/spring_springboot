package com.example.userrole.controller;

import com.example.userrole.entity.User;
import com.example.userrole.service.UserRoleService;
import com.example.userrole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user/{userId}/{roleId}")
    public ResponseEntity<Void> addUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.addUserRole(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}/{roleId}")
    public ResponseEntity<Void> removeUserRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userRoleService.removeUserRole(userId, roleId);
        return ResponseEntity.ok().build();
    }
}
