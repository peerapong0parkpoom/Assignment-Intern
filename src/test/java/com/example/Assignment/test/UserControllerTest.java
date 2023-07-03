package com.example.Assignment.test;

import com.example.Assignment.mapper.ResponseData;
import com.example.Assignment.mapper.UserDTO;
import com.example.Assignment.mapper.UserMapper;
import com.example.Assignment.models.User;
import com.example.Assignment.models.UserController;
import com.example.Assignment.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "uncle roger", "IT"));
        users.add(new User(2, "uncle ben","Finance"));

        Mockito.when(userService.isDatabaseAccessible()).thenReturn(true);
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity response = userController.getAllUsers();

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(users, response.getBody());
    }
    @Test
    public void testGetUserById() {
        int userId = 1;
        User user = new User(userId, "uncle roger", "IT");
        UserDTO userDto = new UserDTO(userId, "uncle roger", "IT");

        Mockito.when(userService.getUser(userId)).thenReturn(Optional.of(user));
        Mockito.when(userMapper.mapToDto(user)).thenReturn(userDto);

        UserDTO response = userController.getUserById(userId);

        Assert.assertEquals(userDto.getId(), response.getId());
        Assert.assertEquals(userDto.getU_name(), response.getU_name());
        Assert.assertEquals(userDto.getDepartment(), response.getDepartment());
    }
    @Test
    public void testCreateUser() {
        // Prepare input data
        UserDTO userDto = new UserDTO(1, "uncle roger", "IT");
        User user = new User(1, "uncle roger", "IT");
        User createdUser = new User(1, "uncle roger", "IT");
        UserDTO createdUserDto = new UserDTO(1, "uncle roger", "IT");

        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        // Configure mock behavior
        Mockito.when(userMapper.mapToEntity(userDto)).thenReturn(user);
        Mockito.when(userService.createUser(user)).thenReturn(createdUser);
        Mockito.when(userMapper.mapToDto(createdUser)).thenReturn(createdUserDto);

        // Call the API endpoint
        ResponseEntity response = userController.createUser(userDto, bindingResult);

        // Perform assertions
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody());
        Assert.assertTrue(response.getBody() instanceof ResponseData);

        ResponseData responseData = (ResponseData) response.getBody();
        Assert.assertEquals("Success", responseData.getStatus());
        Assert.assertEquals(createdUserDto, responseData.getUser());
    }
    @Test
    public void testUpdateAlldataUser() {
        int userId = 1;
        UserDTO userDto = new UserDTO(userId, "uncle roger", "IT");
        User user = new User(userId, "uncle roger", "IT");
        User updatedUser = new User(userId, "uncle John", "Sales");
        UserDTO updatedUserDto = new UserDTO(userId, "uncle John", "Sales");

        Mockito.when(userMapper.mapToEntity(userDto)).thenReturn(user);
        Mockito.when(userService.updateallUser(userId, user)).thenReturn(updatedUser);
        Mockito.when(userMapper.mapToDto(updatedUser)).thenReturn(updatedUserDto);

        UserDTO response = userController.updateAlldataUser(userId, userDto);

        Assert.assertEquals(updatedUserDto.getId(), response.getId());
        Assert.assertEquals(updatedUserDto.getU_name(), response.getU_name());
        Assert.assertEquals(updatedUserDto.getDepartment(), response.getDepartment());
    }

    @Test
    public void testUpdatePartialdataUser() {
        int userId = 1;
        UserDTO userDto = new UserDTO(userId, "uncle roger", "IT");
        User user = new User(userId, "uncle roger", "IT");
        User updatedUser = new User(userId, "uncle roger", "Sales");
        UserDTO updatedUserDto = new UserDTO(userId, "uncle roger", "Sales");

        Mockito.when(userMapper.mapToEntity(userDto)).thenReturn(user);
        Mockito.when(userService.updatesomeUser(userId, user)).thenReturn(updatedUser);
        Mockito.when(userMapper.mapToDto(updatedUser)).thenReturn(updatedUserDto);

        UserDTO response = userController.updatePartialdataUser(userId, userDto);

        Assert.assertEquals(updatedUserDto.getId(), response.getId());
        Assert.assertEquals(updatedUserDto.getU_name(), response.getU_name());
        Assert.assertEquals(updatedUserDto.getDepartment(), response.getDepartment());
    }
    @Test
    public void testDeleteUser() {
        int userId = 1;

        userController.deleteUser(userId);

        Mockito.verify(userService, Mockito.times(1)).deleteUser(userId);
    }
}
