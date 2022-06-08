package com.example.for_flutter.services;

import com.example.for_flutter.dtos.CreateAccountRequest;
import com.example.for_flutter.dtos.UserRequest;
import com.example.for_flutter.exception.UserException;
import com.example.for_flutter.models.UserLogin;
import com.example.for_flutter.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserLoginServiceImpl implements UserLoginService{
    @Autowired
    private UserRepo userRepo;
    private ModelMapper modelMapper;

    public  UserLoginServiceImpl(UserRepo userRepo){

        this.userRepo = userRepo;
        this.modelMapper =new ModelMapper();
    }

    @Override
    public UserRequest createAccount(CreateAccountRequest createAccountRequest) {
        Optional<UserLogin> optionalUserLogin = userRepo.findUserLoginByEmail(createAccountRequest.getEmail());
        if (optionalUserLogin.isPresent()){
            throw  new UserException("user is already present");
        }
        UserLogin login = new UserLogin();
        login.setEmail(createAccountRequest.getEmail());
        login.setPassword(createAccountRequest.getPassword());
        UserLogin savedLogin = userRepo.save(login);

         return modelMapper.map(savedLogin, UserRequest.class);
    }

    @Override
    public void deleteByEmail(String email) {
        UserLogin userLogin = userRepo.findUserLoginByEmail(email).orElseThrow(() -> new UserException("user not found"));
            userRepo.delete(userLogin);
    }

    @Override
    public UserRequest findUserByEmail(String email) {
        Optional<UserLogin> optionalUserLogin = userRepo.findUserLoginByEmail(email);
      //  UserLogin userLogin = userRepo.findUserLoginByEmail(email);
        return modelMapper.map(optionalUserLogin.get(), UserRequest.class);
    }

    @Override
    public String updateUser(String id, CreateAccountRequest createAccountRequest) {
UserLogin userLogin = userRepo.findById(id).orElseThrow(() -> new UserException("user account does not exist"));
boolean isUpdated = false;
if (!(createAccountRequest.getEmail()== null || createAccountRequest.getEmail().trim().equals(""))){
    userLogin.setEmail(createAccountRequest.getEmail());
    isUpdated = true;
}
        if (!(createAccountRequest.getPassword()== null || createAccountRequest.getPassword().trim().equals(""))){
            userLogin.setPassword(createAccountRequest.getPassword());
            isUpdated = true;
        }
        if (isUpdated){
            userRepo.save(userLogin);
        }


        return "user details updated successfully";
    }
}
