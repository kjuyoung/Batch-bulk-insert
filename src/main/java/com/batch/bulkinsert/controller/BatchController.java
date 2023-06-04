package com.batch.bulkinsert.controller;

import com.batch.bulkinsert.service.BulkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BatchController {

    private final BulkService bulkService;

    @PostMapping("/insert")
    public void batchInsert() {

        bulkService.updateData();
    }

    @PostMapping("/bulkinsert")
    public void batchBulkInsert() {

        bulkService.updateJdbcData();
    }
}
