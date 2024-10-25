package com.acarain.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String role; // You may also want to convert this to a more user-friendly format
}

