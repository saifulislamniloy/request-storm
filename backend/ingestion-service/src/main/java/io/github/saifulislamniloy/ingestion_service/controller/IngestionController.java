package io.github.saifulislamniloy.ingestion_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.saifulislamniloy.ingestion_service.dto.IngestionRequestDTO;
import io.github.saifulislamniloy.ingestion_service.service.IngestionService;

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
}
