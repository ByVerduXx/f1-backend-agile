package com.uah.f1backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;

@Entity(name = COUNTRY_ENTITY)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CountryModel {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude
    @Column(name = COUNTRY_ID)
    private Integer id;

    @Column(name = COUNTRY_CODE)
    private String code;

    @Column(name = COUNTRY_NAME)
    private String name;
}
