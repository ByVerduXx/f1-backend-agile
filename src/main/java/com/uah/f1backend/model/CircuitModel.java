package com.uah.f1backend.model;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;



@Entity(name="circuit")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CircuitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = CIRCUIT_ID)
    private Integer id;
    @Column(name = CIRCUIT_NAME)
    private String name;
    @Column(name = CIRCUIT_CITY)
    private String city;
    @Column(name = CIRCUIT_IDCOUNTRY)
    private Integer id_country;
    @Column(name = CIRCUIT_IMAGE)
    private String image;
    @Column(name = CIRCUIT_LAPS)
    private Integer laps;
    @Column(name = CIRCUIT_LENGTH)
    private Integer length;
    @Column(name = CIRCUIT_SLOW_TURNS)
    private Integer slow_turns;
    @Column(name = CIRCUIT_MEDIUM_TURNS)
    private Integer medium_turns;
    @Column(name = CIRCUIT_FAST_TURNS)
    private Integer fast_turns;

}


