package com.example.demo.user;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
public class UserDto {
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 2)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    public UserDto(String name, String username, String email, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
