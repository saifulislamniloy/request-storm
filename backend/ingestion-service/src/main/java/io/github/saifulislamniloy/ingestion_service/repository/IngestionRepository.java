package io.github.saifulislamniloy.ingestion_service.repository;

import io.github.saifulislamniloy.ingestion_service.entity.IngestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngestionRepository extends JpaRepository<IngestionRecord, Long> {
}
