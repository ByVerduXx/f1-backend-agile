package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.RACE_TABLE;

import jakarta.persistence.*;
import java.util.Date;
import lombok.*;

@Entity(name = RACE_TABLE)
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = RACE_ID)
    private Integer id;

    @Column(name = RACE_NAME)
    private String name;

    @Column(name = RACE_DATE)
    private Date date;

    @Column(name = RACE_SPRINT)
    private Boolean sprint;

    @ManyToOne
    @JoinColumn(name = RACE_CIRCUIT)
    private CircuitModel circuit;
}
