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
    private String id_country;
    @Column(name = CIRCUIT_IMAGE)
    private String image;
    @Column(name = CIRCUIT_LAPS)
    private String laps;
    @Column(name = CIRCUIT_LENGTH)
    private String length;
    @Column(name = CIRCUIT_SLOW_TURNS)
    private String slow_turns;
    @Column(name = CIRCUIT_MEDIUM_TURNS)
    private String medium_turns;
    @Column(name = CIRCUIT_FAST_TURNS)
    private String fast_turns;

}


