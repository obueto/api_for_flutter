package com.example.for_flutter.controllers;

import com.example.for_flutter.dtos.CreateAccountRequest;
import com.example.for_flutter.dtos.UserResponse;
import com.example.for_flutter.exception.UserException;
import com.example.for_flutter.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/flutter")
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

@PostMapping("/login")
    public ResponseEntity<?> createNewUserAccount(@RequestBody CreateAccountRequest accountRequest){
    try{
        UserResponse response = UserResponse.builder()
                .payLoad(userLoginService.createAccount(accountRequest))
                .message("account created successfully")
                .isSuccessful(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    catch (UserException exception){
        UserResponse userResponse = UserResponse.builder()
                .message(exception.getMessage())
                .isSuccessful(false)
                .build();
        return new ResponseEntity<>(userResponse,HttpStatus.BAD_REQUEST);
    }
}
}
