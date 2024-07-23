package com.jwt.First.Controller;

import com.jwt.First.model.User;
import com.jwt.First.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<User> getUser(){
        System.out.println("Iam working from springboot");
        return userService.getUserStore();
    }



    @GetMapping("/current-user")
    public String getLoggedUser(Principal principal){
        return principal.getName();
    }
}
