package com.fooddelivery.dao;


import java.util.List;

import com.fooddelivery.model.User;

public interface UserDAO {
    
    // Existing methods
    int addUser(User user);
    List<User> getAllUsers();
    void deleteUser(int userId);
    User getUser(String email, String password);
    void updateUser(User user);
    
    // New methods for authentication
    User validateUser(String email, String password);
    int createUser(User user);
    boolean emailExists(String email);
}