package com.example.Assignment.models;

import com.example.Assignment.mapper.UserDTO;
import com.example.Assignment.mapper.UserMapper;
import com.example.Assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserController {
    private UserService userService;
    private UserMapper userMapper;
    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        List<UserDTO> userDtos = userMapper.toDtoList(users);
//        return ResponseEntity.ok(userDtos);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
//        return userService.getUser(id)
//                .map(userMapper::mapToDto)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//    @PostMapping
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
//        User user = userMapper.mapToEntity(userDTO);
//        User createdUser = userService.createUser(user);
//        UserDTO createdUserDTO = userMapper.mapToDto(createdUser);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserDTO);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
//        User user = userMapper.mapToEntity(userDTO);
//        User updatedUser = userService.updateUser(id,user);
//        UserDTO updatedUserDTO = userMapper.mapToDto(updatedUser);
//        return ResponseEntity.ok(updatedUserDTO);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
    @GetMapping()
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return userMapper.toDtoList(users);
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        User user = userService.getUser(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.mapToDto(user);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDto) {
        User user = userMapper.mapToEntity(userDto);
        User createdUser = userService.createUser(user);
        return userMapper.mapToDto(createdUser);
    }

    @PutMapping("/{id}")
    public UserDTO updateAlldataUser(@PathVariable int id, @RequestBody UserDTO userDto) {
        User user = userMapper.mapToEntity(userDto);
        User updatedUser = userService.updateallUser(id, user);
        return userMapper.mapToDto(updatedUser);
    }
    @PatchMapping("/{id}")
    public UserDTO updatePartialdataUser(@PathVariable int id, @RequestBody UserDTO userDto) {
        User user = userMapper.mapToEntity(userDto);
        User updatedUser = userService.updatesomeUser(id, user);
        return userMapper.mapToDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
