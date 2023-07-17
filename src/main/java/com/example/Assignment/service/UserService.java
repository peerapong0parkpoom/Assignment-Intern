package com.example.Assignment.service;

import com.example.Assignment.models.User;
import com.example.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean isDatabaseAccessible(){
        try{
            userRepository.findAll();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public User createUser(User user){return userRepository.save(user);}
    @CachePut(value = "User" , key = "#id")
    public User updateallUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setU_name(user.getU_name());
            existingUser.setDepartment(user.getDepartment());
            return userRepository.save(existingUser);
        }
        return null;
    }
    @CachePut(value = "User" , key = "#id")
    public User updatesomeUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            if (user.getU_name() != null) {
                existingUser.setU_name(user.getU_name());
            }
            if (user.getDepartment() != null) {
                existingUser.setDepartment(user.getDepartment());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }
    @Cacheable(value = "User", key = "#id" , unless = "#result == null")
    public Optional<User> getUser(int id) {
        System.out.println("calling "+id);
        return userRepository.findById(id);
    }
    @Cacheable("User")
    public List<User> getAllUsers() {
        System.out.println("calling all");
        return userRepository.findAll();
    }
    @CacheEvict(value = "User" , allEntries = true)
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
