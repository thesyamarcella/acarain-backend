package com.acarain.service;

import com.acarain.dto.TicketDTO;
import com.acarain.model.Event;
import com.acarain.model.Ticket;
import com.acarain.repository.EventRepository;
import com.acarain.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    // Get all tickets
    public List<TicketDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get ticket by ID
    public TicketDTO getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        return convertToDTO(ticket);
    }

    // Create a new ticket
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketType(ticketDTO.getTicketType());
        ticket.setPrice(ticketDTO.getPrice());

        // Find the associated event by ID
        Event event = eventRepository.findById(ticketDTO.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));
        ticket.setEvent(event);

        Ticket savedTicket = ticketRepository.save(ticket);
        return convertToDTO(savedTicket);
    }

    // Update an existing ticket
    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setTicketType(ticketDTO.getTicketType());
        ticket.setPrice(ticketDTO.getPrice());

        // Update the event if needed
        if (ticketDTO.getEventId() != null) {
            Event event = eventRepository.findById(ticketDTO.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            ticket.setEvent(event);
        }

        Ticket updatedTicket = ticketRepository.save(ticket);
        return convertToDTO(updatedTicket);
    }

    // Delete a ticket
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }

    // Convert Ticket to TicketDTO
    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setTicketType(ticket.getTicketType());
        ticketDTO.setPrice(ticket.getPrice());
        ticketDTO.setEventId(ticket.getEvent().getId()); // Only get the event ID
        return ticketDTO;
    }
}
