package com.jwt.First.services;


import com.jwt.First.model.User;
import com.jwt.First.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    public UserService(){
//        userStore.add(new User(UUID.randomUUID().toString(),"Aishu","aishwarya@gmail.com"));
//        userStore.add(new User(UUID.randomUUID().toString(),"Venky","venky@gmail.com"));
//        userStore.add(new User(UUID.randomUUID().toString(),"Mammu","mammu@gmail.com"));
//        userStore.add(new User(UUID.randomUUID().toString(),"Maamu","maamu@gmail.com"));
//    }


    public List<User> getUserStore(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
