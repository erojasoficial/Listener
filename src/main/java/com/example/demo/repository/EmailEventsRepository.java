package com.example.demo.repository;

import com.example.demo.domain.entity.EmailEvents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailEventsRepository extends JpaRepository<EmailEvents, Integer> {
}
