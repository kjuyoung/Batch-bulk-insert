package com.batch.bulkinsert.repository;

import com.batch.bulkinsert.model.BulkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkRepository extends JpaRepository<BulkEntity, Long> {
}
