package com.example.Assignment.test;

import com.example.Assignment.models.User;
import com.example.Assignment.repository.UserRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testIsDatabaseAccessible() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        boolean isAccessible = userService.isDatabaseAccessible();

        Assert.assertTrue(isAccessible);
    }
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setU_name("John Doe");
        user.setDepartment("IT");

        Mockito.when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        Assert.assertEquals(user, createdUser);
    }
    @Test
    public void testUpdateallUser_UserExists() {
        // Prepare an existing user for testing
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setU_name("Old Name");
        existingUser.setDepartment("Old Department");

        // Prepare the updated user object for testing
        User updatedUser = new User();
        updatedUser.setU_name("New Name");
        updatedUser.setDepartment("New Department");

        // Mock the userRepository to return the existing user when findById is called
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        // Mock the userRepository to return the updated user when save is called
        Mockito.when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Call the method to test
        User result = userService.updateallUser(1, updatedUser);

        // Assert that the method returned the updated user object
        Assert.assertEquals(updatedUser, result);
        // Assert that the existing user has been updated with the new data
        Assert.assertEquals("New Name", existingUser.getU_name());
        Assert.assertEquals("New Department", existingUser.getDepartment());
    }

    @Test
    public void testUpdateallUser_UserNotExists() {
        // Prepare the updated user object for testing
        User updatedUser = new User();
        updatedUser.setU_name("New Name");
        updatedUser.setDepartment("New Department");

        // Mock the userRepository to return null when findById is called
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Call the method to test
        User result = userService.updateallUser(1, updatedUser);

        // Assert that the method returned null as the user does not exist
        Assert.assertNull(result);
    }
    @Test
    public void testUpdatesomeUser_UserExists() {
        // Prepare an existing user for testing
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setU_name("Old Name");
        existingUser.setDepartment("Old Department");

        // Prepare the updated user object for testing
        User updatedUser = new User();
        updatedUser.setU_name("New Name");

        // Mock the userRepository to return the existing user when findById is called
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        // Mock the userRepository to return the updated user when save is called
        Mockito.when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        // Call the method to test
        User result = userService.updatesomeUser(1, updatedUser);

        // Assert that the method returned the updated user object
        Assert.assertEquals(updatedUser, result);
        // Assert that the existing user has been updated with the new name
        Assert.assertEquals("New Name", existingUser.getU_name());
        // Assert that the existing user's department remains unchanged
        Assert.assertEquals("Old Department", existingUser.getDepartment());
    }

    @Test
    public void testUpdatesomeUser_UserNotExists() {
        // Prepare the updated user object for testing
        User updatedUser = new User();
        updatedUser.setU_name("New Name");

        // Mock the userRepository to return null when findById is called
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Call the method to test
        User result = userService.updatesomeUser(1, updatedUser);

        // Assert that the method returned null as the user does not exist
        Assert.assertNull(result);
    }

    @Test
    public void testGetUser_UserExists() {
        // Prepare an existing user for testing
        User existingUser = new User();
        existingUser.setId(1);
        existingUser.setU_name("John Doe");
        existingUser.setDepartment("IT");

        // Mock the userRepository to return the existing user when findById is called
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        // Call the method to test
        Optional<User> result = userService.getUser(1);

        // Assert that the method returned the optional containing the existing user
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(existingUser, result.get());
    }

    @Test
    public void testGetUser_UserNotExists() {
        // Mock the userRepository to return null when findById is called
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Call the method to test
        Optional<User> result = userService.getUser(1);

        // Assert that the method returned an empty optional as the user does not exist
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllUsers() {
        // Prepare a list of users for testing
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setU_name("John Doe");
        user1.setDepartment("IT");
        User user2 = new User();
        user2.setId(2);
        user2.setU_name("Jane Smith");
        user2.setDepartment("HR");
        userList.add(user1);
        userList.add(user2);

        // Mock the userRepository to return the list of users when findAll is called
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        // Call the method to test
        List<User> result = userService.getAllUsers();

        // Assert that the method returned the same list of users
        Assert.assertEquals(userList, result);
    }

    @Test
    public void testDeleteUser() {
        // Mock the userRepository to perform the deletion when deleteById is called
        Mockito.doNothing().when(userRepository).deleteById(1);

        // Call the method to test
        userService.deleteUser(1);

        // Verify that the userRepository's deleteById method was called with the correct ID
        Mockito.verify(userRepository).deleteById(1);
    }
}
