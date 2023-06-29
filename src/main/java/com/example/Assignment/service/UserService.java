package com.example.Assignment.service;

import com.example.Assignment.mapper.UserDTO;
import com.example.Assignment.mapper.UserMapper;
import com.example.Assignment.models.User;
import com.example.Assignment.repository.UserRepository;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public User updateallUser(int id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setU_name(user.getU_name());
            existingUser.setDepartment(user.getDepartment());
            return userRepository.save(existingUser);
        }
        return null;
    }
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
    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
