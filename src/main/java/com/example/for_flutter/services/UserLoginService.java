package com.example.for_flutter.services;

import com.example.for_flutter.dtos.CreateAccountRequest;
import com.example.for_flutter.dtos.UserRequest;

public interface UserLoginService {
    UserRequest createAccount (CreateAccountRequest createAccountRequest);
    void deleteByEmail(String email);
    UserRequest findUserByEmail (String email);
    String updateUser(String id, CreateAccountRequest createAccountRequest);
}
