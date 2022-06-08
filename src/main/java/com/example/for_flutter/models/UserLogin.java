package com.example.for_flutter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
public class UserLogin {
    @Id
    private String id;
    private String email;
    private String password;


    public UserLogin( String id,String email,String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
    public UserLogin(String email, String password){
        this.email = email;
        this.password = password;
    }
}
