package io.github.saifulislamniloy.ingestion_service.service;

import io.github.saifulislamniloy.ingestion_service.dto.request.IngestionRequestDTO;
import io.github.saifulislamniloy.ingestion_service.dto.response.IngestionResponseDTO;

import java.util.List;

public interface IngestionService {

    void create(IngestionRequestDTO ingestionRequestDTO);

    List<IngestionResponseDTO> getList();
}
