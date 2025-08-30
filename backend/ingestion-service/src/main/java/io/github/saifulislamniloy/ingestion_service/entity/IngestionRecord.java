package io.github.saifulislamniloy.ingestion_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingestion_records")
@Entity
public class IngestionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;

    private Instant createdAt;

    private Instant clientTimestamp;

    private String data;
}

