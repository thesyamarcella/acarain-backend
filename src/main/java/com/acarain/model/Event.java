package com.acarain.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime dateTime;
    private String location;
    private String description;
    private Double ticketPrice;
    private Integer ticketQuantity;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    public Long getOrganizerId() {
        return organizer != null ? organizer.getId() : null;
    }
    
    public String getOrganizerName() {
        return organizer != null ? organizer.getName() : null;
    }

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public enum TicketType {
        GENERAL,
        VIP
    }

    public enum EventStatus {
        UPCOMING,
        CANCELLED,
        COMPLETED
    }

}
