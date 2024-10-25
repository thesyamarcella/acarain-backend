package com.acarain.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private String content;
    private Long reviewId; 
    private Long userId; 
}

