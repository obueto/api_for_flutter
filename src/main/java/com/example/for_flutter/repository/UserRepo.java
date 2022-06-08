package com.example.for_flutter.repository;

import com.example.for_flutter.models.UserLogin;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.Optional;
import java.util.function.Function;

public interface UserRepo extends MongoRepository <UserLogin,String>{
    Optional<UserLogin> findUserLoginByEmail(String email);

}
