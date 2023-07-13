package com.example.Assignment.test;

import com.example.Assignment.models.User;
import com.example.Assignment.repository.UserRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class UserRepoTest {
    //findbyID
    //findall
    //save
    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Mocking the UserRepository behavior
        User user = new User(1, "John Doe","IT");
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // Calling the actual method
        Optional<User> result = userRepository.findById(1);

        // Verifying the result
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(user, result.get());

        // Verifying that the UserRepository method was called
        Mockito.verify(userRepository, Mockito.times(1)).findById(1);
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testFindAll() {
        // Mocking the UserRepository behavior
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe","IT"));
        users.add(new User(2, "Jane Smith","Finance"));
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Calling the actual method
        List<User> result = userRepository.findAll();

        // Verifying the result
        Assert.assertEquals(users.size(), result.size());
        Assert.assertEquals(users, result);

        // Verifying that the UserRepository method was called
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void testSave() {
        // Mocking the UserRepository behavior
        User user = new User(1, "John Doe","IT");
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Calling the actual method
        User result = userRepository.save(user);

        // Verifying the result
        Assert.assertEquals(user, result);

        // Verifying that the UserRepository method was called
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verifyNoMoreInteractions(userRepository);
    }
}
