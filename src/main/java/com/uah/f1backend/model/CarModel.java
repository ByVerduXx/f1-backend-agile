package com.uah.f1backend.model;

import com.uah.f1backend.configuration.common.ColumnNameConstants;
import com.uah.f1backend.configuration.common.TableNameConstants;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = TableNameConstants.CAR_TABLE)
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = ColumnNameConstants.CAR_ID)
    private Integer id;

    @Column(name = ColumnNameConstants.CAR_NAME)
    private String name;

    @Column(name = ColumnNameConstants.CAR_CODE)
    private String code;

    @Column(name = ColumnNameConstants.CAR_ERS_GAIN_SLOW)
    private BigDecimal ersGainSlow;

    @Column(name = ColumnNameConstants.CAR_RS_GAIN_MEDIUM)
    private BigDecimal ersGainMedium;

    @Column(name = ColumnNameConstants.CAR_ERS_GAIN_FAST)
    private BigDecimal ersGainFast;

    @Column(name = ColumnNameConstants.CAR_CONSUMPTION)
    private BigDecimal consumption;

    @ManyToOne
    @JoinColumn(name = ColumnNameConstants.CAR_TEAM_ID)
    private TeamModel team;
}
