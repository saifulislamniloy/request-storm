package io.github.saifulislamniloy.ingestion_service.mapper;

import io.github.saifulislamniloy.ingestion_service.dto.request.IngestionRequestDTO;
import io.github.saifulislamniloy.ingestion_service.dto.response.IngestionResponseDTO;
import io.github.saifulislamniloy.ingestion_service.entity.IngestionRecord;

import java.time.Instant;

public class IngestionMapper {

    public static IngestionRecord toEntity(IngestionRequestDTO ingestionRequestDTO) {

        return IngestionRecord.builder()
                .userId(ingestionRequestDTO.getUserId())
                .clientTimestamp(ingestionRequestDTO.getClientTimestamp())
                .data(ingestionRequestDTO.getData())
                .createdAt(Instant.now())
                .build();
    }

    public static IngestionResponseDTO toResponseDTO(IngestionRecord ingestionRecord) {

        return IngestionResponseDTO.builder()
                .userId(ingestionRecord.getUserId())
                .clientTimestamp(ingestionRecord.getClientTimestamp())
                .data(ingestionRecord.getData())
                .createdAt(ingestionRecord.getCreatedAt())
                .build();
    }
}
