package com.example.Assignment.test;

import com.example.Assignment.models.User;
import com.example.Assignment.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRepoTest {
    //findbyID
    //findall
    //save
    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserRepository userRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Mocking the UserRepository behavior
        int userId = 1;
        User user = new User(1, "John Doe","IT");
        Mockito.when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));

        // Calling the actual method
        Optional<User> result = userRepository.findById(userId);

        // Verifying the result
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(user, result.get());

        // Verifying that the UserRepository method was called
        Mockito.verify(userRepositoryMock, Mockito.times(1)).findById(userId);
        Mockito.verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testFindAll() {
        // Mocking the UserRepository behavior
        List<User> users = new ArrayList<>();
        users.add(new User(1, "John Doe"));
        users.add(new User(2, "Jane Smith"));
        when(userRepositoryMock.findAll()).thenReturn(users);

        // Calling the actual method
        List<User> result = userRepository.findAll();

        // Verifying the result
        Assertions.assertEquals(users.size(), result.size());
        Assertions.assertEquals(users, result);

        // Verifying that the UserRepository method was called
        verify(userRepositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(userRepositoryMock);
    }

    @Test
    public void testSave() {
        // Mocking the UserRepository behavior
        User user = new User(1, "John Doe");
        when(userRepositoryMock.save(user)).thenReturn(user);

        // Calling the actual method
        User result = userRepository.save(user);

        // Verifying the result
        Assertions.assertEquals(user, result);

        // Verifying that the UserRepository method was called
        verify(userRepositoryMock, times(1)).save(user);
        verifyNoMoreInteractions(userRepositoryMock);
    }
}
