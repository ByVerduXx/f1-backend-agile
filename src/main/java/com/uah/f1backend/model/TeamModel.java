package com.uah.f1backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.TEAM_TABLE;

@Entity(name = TEAM_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TeamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name=TEAM_ID)
    private Integer id;
    @Column(name=TEAM_NAME)
    private String name;
    @Column(name=TEAM_LOGO)
    private String logo;
    @Column(name=TEAM_TWITTER)
    private String twitter;
}
