package io.github.saifulislamniloy.ingestion_service.controller;

import io.github.saifulislamniloy.ingestion_service.dto.response.IngestionResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.saifulislamniloy.ingestion_service.dto.request.IngestionRequestDTO;
import io.github.saifulislamniloy.ingestion_service.service.IngestionService;

import java.util.List;

import static io.github.saifulislamniloy.ingestion_service.constant.ApiEndpoints.*;
import static io.github.saifulislamniloy.ingestion_service.constant.Constant.DATA_SAVE_SUCCESSFUL;

@RequiredArgsConstructor
@RequestMapping(API_VERSION)
@RestController
public class IngestionController {

    private final IngestionService ingestionService;

    @PostMapping(CREATE_INGESTION_RECORD)
    public ResponseEntity<String> create(@RequestBody IngestionRequestDTO ingestionRequestDTO) {

        ingestionService.create(ingestionRequestDTO);
        return ResponseEntity.ok(DATA_SAVE_SUCCESSFUL);
    }

    @GetMapping(GET_LIST_OF_INGESTION_RECORD)
    public ResponseEntity<List<IngestionResponseDTO>> getList() {

        List<IngestionResponseDTO> list = ingestionService.getList();
        return ResponseEntity.ok(list);
    }
}
