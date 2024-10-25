package com.acarain.repository;

import com.acarain.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // You can define custom query methods here if needed
}
