package com.batch.bulkinsert.repository;

import com.batch.bulkinsert.model.BulkEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BulkJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private int batchSize = 100;

    public void saveAll(List<BulkEntity> items) {
        int batchCount = 0;
        List<BulkEntity> subItems = new ArrayList<>();

        String sql = "INSERT INTO member (`id`, `name`, `team`) VALUES (?, ?, ?)";

        for (int i = 0; i < items.size(); i++) {
            subItems.add(items.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchCount, sql, subItems, makeSetter(subItems));
            }
        }

        if (!subItems.isEmpty()) {
            batchCount = batchInsert(batchCount, sql, subItems, makeSetter(subItems));
        }

        log.info("Entity total size = {}", items.size());
        log.info("batchCount: " + batchCount);
    }

    private int batchInsert(int batchCount, String sql, List<BulkEntity> subItems, BatchPreparedStatementSetter setter) {

        jdbcTemplate.batchUpdate(sql, setter);

        subItems.clear();
        batchCount++;
        return batchCount;
    }

    BatchPreparedStatementSetter makeSetter(List<BulkEntity> subItems) {
        return new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, subItems.get(i).getId());
                ps.setString(2, subItems.get(i).getName());
                ps.setString(3, subItems.get(i).getTeam());
            }

            @Override
            public int getBatchSize() {
                return subItems.size();
            }
        };
    }
}
