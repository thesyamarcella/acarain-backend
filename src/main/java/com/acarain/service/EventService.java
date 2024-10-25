package com.acarain.service;

import com.acarain.dto.EventDTO;
import com.acarain.model.Event;
import com.acarain.model.User;
import com.acarain.repository.EventRepository;
import com.acarain.repository.UserRepository; // If needed for getting organizer details
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository; // If you need to get organizer info

    // Get all events
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get event by ID
    public EventDTO getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return convertToDTO(event);
    }

    // Create a new event
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDateTime(eventDTO.getDateTime());
        event.setLocation(eventDTO.getLocation());
        event.setDescription(eventDTO.getDescription());
        event.setTicketPrice(eventDTO.getTicketPrice());
        event.setTicketQuantity(eventDTO.getTicketQuantity());

        // Assuming you have an organizer ID passed from the eventDTO
        User organizer = userRepository.findById(eventDTO.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));
        event.setOrganizer(organizer);

        Event savedEvent = eventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    // Update an existing event
    public EventDTO updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        event.setName(eventDTO.getName());
        event.setDateTime(eventDTO.getDateTime());
        event.setLocation(eventDTO.getLocation());
        event.setDescription(eventDTO.getDescription());
        event.setTicketPrice(eventDTO.getTicketPrice());
        event.setTicketQuantity(eventDTO.getTicketQuantity());

        // Update the organizer if needed
        if (eventDTO.getOrganizerId() != null) {
            User organizer = userRepository.findById(eventDTO.getOrganizerId())
                    .orElseThrow(() -> new RuntimeException("Organizer not found"));
            event.setOrganizer(organizer);
        }

        Event updatedEvent = eventRepository.save(event);
        return convertToDTO(updatedEvent);
    }

    // Delete an event
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found");
        }
        eventRepository.deleteById(id);
    }

    // Convert Event to EventDTO
    private EventDTO convertToDTO(Event event) {
        EventDTO dto = new EventDTO();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDateTime(event.getDateTime());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());
        dto.setTicketPrice(event.getTicketPrice());
        dto.setTicketQuantity(event.getTicketQuantity());
        dto.setOrganizerId(event.getOrganizer().getId()); // Only get ID

        return dto;
    }
}
