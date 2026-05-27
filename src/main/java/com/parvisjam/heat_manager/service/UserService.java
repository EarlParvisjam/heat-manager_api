package com.parvisjam.heat_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parvisjam.heat_manager.service.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void fetchUser(){
        userDao.readUsersFromDatabase();
    }
    /* private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(int id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setAge(updatedUser.getAge());
                    return userRepository.save(user);
                })
                .orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    } */
}  