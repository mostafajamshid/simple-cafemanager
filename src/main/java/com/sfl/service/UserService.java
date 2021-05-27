package com.sfl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sfl.model.Role;
import com.sfl.model.User;
import com.sfl.repository.RoleRepository;
import com.sfl.repository.UserRepository;

@Service("userService")
public class UserService {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;
    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        users = userRepository.findAll();
        return users;
    }

    public List<User> findAllWaiters() {
        List<User> users = new ArrayList<>();
        Role role = new Role();
        role.setId(2);
        role.setRole("WAITER");
        users = userRepository.findByRole(role);
        return users;
    }

    public User findUser(int id) {
        return userRepository.findOne(id);
    }

    public void delete(int id) {
        userRepository.delete(id);

    }

    public void save(User user) {
        userRepository.save(user);
    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("Waiter");
        user.setRole(userRole);
        userRepository.save(user);
    }

    public List<User> findUserbyRole(Role role) {
        return userRepository.findByRole(role);
    }
}
