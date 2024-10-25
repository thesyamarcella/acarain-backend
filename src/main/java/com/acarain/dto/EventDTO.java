package com.acarain.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private Double ticketPrice; 
    private Integer ticketQuantity; 
    private Long organizerId; // Reference to the organizer ID
    private List<TicketDTO> tickets; // List of tickets associated with the event
}
