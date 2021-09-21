package com.example.Managment.Managment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserRegistrationDto(String firstname, String lastname, String email, String password) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
    }

    public UserRegistrationDto(){

    }
}
