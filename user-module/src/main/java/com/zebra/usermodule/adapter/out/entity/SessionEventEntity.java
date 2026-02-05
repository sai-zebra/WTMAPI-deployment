package com.zebra.usermodule.adapter.out.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import org.springframework.modulith.NamedInterface;

import java.time.Instant;

@NamedInterface
@Entity
@Table(name = "session_events")
@Getter
@Setter
public class SessionEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType;

    @Column(nullable = false)
    private Instant timestamp;

    public enum EventType {
        LOGIN, LOGOUT
    }
}

