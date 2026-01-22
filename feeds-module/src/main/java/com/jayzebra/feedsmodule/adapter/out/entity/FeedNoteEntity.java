package com.jayzebra.feedsmodule.adapter.out.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "feed_notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedNoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false,updatable = false)
    private UUID id;

    @Lob
    @Column(nullable = false)
    private String message;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id",nullable = false)
    private FeedEntity feed;
}
