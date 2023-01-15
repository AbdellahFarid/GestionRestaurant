package com.example.gestionrestaurant.controller;

import com.example.gestionrestaurant.entity.Login;
import com.example.gestionrestaurant.entity.User;
import com.example.gestionrestaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Login login) {
        List<String> userEmail = userService.checkUserEmail(login.getEmail());
        List<String> userPassword = userService.checkUserPassword(login.getPassword());
        if (userEmail.isEmpty() || userEmail == null) {
            return new ResponseEntity("Email does not exist", HttpStatus.NOT_FOUND);
        }
        if (userPassword.isEmpty() || userPassword == null) {
            return new ResponseEntity("Password does not exist", HttpStatus.NOT_FOUND);
        }
        String hashed_password = userService.checkUserPaasswordByEmail(login.getEmail());

        if (login.getPassword().equals(userPassword)) {
            return new ResponseEntity("Incorrect email or password", HttpStatus.BAD_REQUEST);
        }
        User user = userService.getUserDetailByEmail(login.getEmail());

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestParam("address") String address,
                                       @RequestParam("email") String email,
                                       @RequestParam("name") String name,
                                       @RequestParam("password") String password) {
        if (address.isEmpty() || email.isEmpty() || name.isEmpty() || password.isEmpty()) {
            return new ResponseEntity("Veuillez pas laissez les champs vides"
                    , HttpStatus.BAD_REQUEST);
        }
        if (userService.registerNewUserService(address, email, name, password) != 1) {
            return new ResponseEntity("Enregistrement echoué"
                    , HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Enregistrement avec success", HttpStatus.OK);
    }
}
