package com.acarain.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String content;
    private Long eventId; 
    private Long userId;  
}

