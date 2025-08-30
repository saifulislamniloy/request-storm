package io.github.saifulislamniloy.ingestion_service.service.impl;

import io.github.saifulislamniloy.ingestion_service.dto.request.IngestionRequestDTO;
import io.github.saifulislamniloy.ingestion_service.dto.response.IngestionResponseDTO;
import io.github.saifulislamniloy.ingestion_service.mapper.IngestionMapper;
import io.github.saifulislamniloy.ingestion_service.repository.IngestionRepository;
import io.github.saifulislamniloy.ingestion_service.service.IngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<IngestionResponseDTO> getList() {
        return ingestionRepository
                .findAll()
                .stream()
                .map(IngestionMapper::toResponseDTO)
                .toList();
    }
}
