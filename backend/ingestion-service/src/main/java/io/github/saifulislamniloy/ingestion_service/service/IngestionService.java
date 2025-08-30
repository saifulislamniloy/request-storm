package io.github.saifulislamniloy.ingestion_service.service;

import io.github.saifulislamniloy.ingestion_service.dto.request.IngestionRequestDTO;

public interface IngestionService {

    void create(IngestionRequestDTO ingestionRequestDTO);
}
