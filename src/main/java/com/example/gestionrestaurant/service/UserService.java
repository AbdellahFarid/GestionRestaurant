package com.example.gestionrestaurant.service;

import com.example.gestionrestaurant.entity.User;
import com.example.gestionrestaurant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    public int registerNewUserService(String address,String email,String name,String password){
        return userRepository.registerNewUser(address,email,name,password);
    }
    public List<String> checkUserEmail(String email){
        return userRepository.checkUserEmail(email);
    }
    public List<String> checkUserPassword(String password){
        return userRepository.checkUserPassword(password);
    }
    public String checkUserPaasswordByEmail(String email){
        return userRepository.checkUserPasswordByEmail(email);
    }
    public User getUserDetailByEmail(String email){
        return userRepository.getUserDetailByEmail(email);
    }
}
