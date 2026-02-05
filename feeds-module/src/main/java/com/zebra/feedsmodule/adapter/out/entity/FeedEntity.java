package com.zebra.feedsmodule.adapter.out.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "feeds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Using UUID for IDs as is common for modern APIs
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Lob // For potentially large text content
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String status; // e.g., NEW, CLAIMED, COMPLETED

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    //Deleting a FeedEntity, will automatically delete all its associated FeedNoteEntity records first, preventing the foreign key violation.
    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<FeedNoteEntity> feedNotes;
}

