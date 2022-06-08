package com.example.for_flutter.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserResponse {
    private Object payLoad;
    private  String message;
    private  boolean isSuccessful;
}
