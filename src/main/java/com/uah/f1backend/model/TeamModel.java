package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "team")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TeamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = TEAM_ID)
    private Integer id;

    @Column(name = TEAM_NAME)
    private String name;

    @Column(name = TEAM_LOGO)
    private String logo;

    @Column(name = TEAM_TWITTER)
    private String twitter;
}
