package com.batch.bulkinsert.service;

import com.batch.bulkinsert.model.BulkEntity;
import com.batch.bulkinsert.repository.BulkJdbcRepository;
import com.batch.bulkinsert.repository.BulkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BulkService {

    private final BulkRepository bulkRepository;
    private final BulkJdbcRepository bulkJdbcRepository;

    @Transactional
    public void updateData() {

        AtomicLong identifier = new AtomicLong(1);

        log.info("testing start");

        long start = System.currentTimeMillis();

        for(int i=1; i<=500; i++) {
            BulkEntity entity = BulkEntity.builder()
                    .id(identifier.getAndIncrement())
                    .name("KJY")
                    .team("Naver"+i)
                    .build();

            bulkRepository.save(entity);
        }
        long end = System.currentTimeMillis();

        log.info("testing end");
        log.info("수행시간: " + (end - start) + " ms");
    }

    public void updateJdbcData() {

        AtomicLong identifier = new AtomicLong(1);
        List<BulkEntity> entityList = new ArrayList<>();

        log.info("testing Bulk insert start");

        long start = System.currentTimeMillis();

        for(int i=1; i<=500; i++) {
            BulkEntity entity = BulkEntity.builder()
                    .id(identifier.getAndIncrement())
                    .name("KJY")
                    .team("Naver"+i)
                    .build();
            entityList.add(entity);
        }

        bulkJdbcRepository.saveAll(entityList);
        long end = System.currentTimeMillis();

        log.info("testing Bulk insert end");
        log.info("수행시간: " + (end - start) + " ms");
    }
}
