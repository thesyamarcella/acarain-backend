package com.acarain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acarain.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
