package com.jwt.First.Controller;

import com.jwt.First.model.User;
import com.jwt.First.model.JwtRequest;
import com.jwt.First.model.JwtResponse;
import com.jwt.First.repository.UserRepository;
import com.jwt.First.security.JwtHelper;
import com.jwt.First.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.filter.OncePerRequestFilter;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper helper;

    private Logger logger= LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        this.doAuthenticate(request.getEmail(),request.getPassword());
        System.out.println("hiii");
        UserDetails userDetails= userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder().jwtToken(token).username(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password){
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);
        //System.out.println(authentication);
        try{
            manager.authenticate(authentication);
        }
        catch(BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password !!");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler(){
        return "Credentials Invalid!!";
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
    return userService.createUser(user);
    }
}
