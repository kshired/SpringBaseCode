package com.kshired.springbase.user.entity;

import com.kshired.springbase.user.dto.UserResponseDto;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserResponseDto toUserResponseDto() {
        return new UserResponseDto(this.id, this.email);
    }
}
