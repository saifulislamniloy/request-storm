package io.github.saifulislamniloy.ingestion_service.service.impl;

import io.github.saifulislamniloy.ingestion_service.dto.IngestionRequestDTO;
import io.github.saifulislamniloy.ingestion_service.mapper.IngestionMapper;
import io.github.saifulislamniloy.ingestion_service.repository.IngestionRepository;
import io.github.saifulislamniloy.ingestion_service.service.IngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IngestionServiceImpl implements IngestionService {

    private final IngestionRepository ingestionRepository;

    @Override
    public void create(IngestionRequestDTO ingestionRequestDTO) {
        ingestionRepository.save(
                IngestionMapper.toEntity(ingestionRequestDTO)
        );
    }
}
