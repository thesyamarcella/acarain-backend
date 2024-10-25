package com.acarain.controller;

import com.acarain.dto.TicketDTO;
import com.acarain.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Get all tickets
    @GetMapping
    public List<TicketDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public TicketDTO getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    // Create a new ticket
    @PostMapping
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        return ticketService.createTicket(ticketDTO);
    }

    // Update an existing ticket
    @PutMapping("/{id}")
    public TicketDTO updateTicket(@PathVariable Long id, @RequestBody TicketDTO ticketDTO) {
        return ticketService.updateTicket(id, ticketDTO);
    }

    // Delete a ticket
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }
}
