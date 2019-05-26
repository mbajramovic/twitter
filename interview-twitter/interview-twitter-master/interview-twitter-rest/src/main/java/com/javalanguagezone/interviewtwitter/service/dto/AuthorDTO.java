package com.javalanguagezone.interviewtwitter.service.dto;

import lombok.Getter;

@Getter
public class AuthorDTO {
    private String username;
    private String fullName;

    public AuthorDTO(String username, String fullName) {
        this.username = username;
        this.fullName = fullName;
    }
}