package com.batch.bulkinsert.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Entity
public class BulkEntity {

    @Id
    private Long id;

    private String name;

    private String team;

    @Builder
    public BulkEntity(Long id, String name, String team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }
}
