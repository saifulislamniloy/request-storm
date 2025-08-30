package io.github.saifulislamniloy.ingestion_service.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class IngestionRequestDTO {

    private UUID userId;

    private Instant clientTimestamp;

    private String data;
}
