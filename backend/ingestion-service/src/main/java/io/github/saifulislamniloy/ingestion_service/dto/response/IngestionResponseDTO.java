package io.github.saifulislamniloy.ingestion_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class IngestionResponseDTO {

    private UUID userId;

    private Instant clientTimestamp;

    private String data;

    private Instant createdAt;
}
