package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.COUNTRY_TABLE;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = COUNTRY_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CountryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = COUNTRY_ID)
    private Integer id;

    @Column(name = COUNTRY_CODE)
    private String code;

    @Column(name = COUNTRY_NAME)
    private String name;
}
