package com.acarain.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Long id;
    private Long eventId;
    private String ticketType;
    private Double price;
    private Integer quantity;
}
