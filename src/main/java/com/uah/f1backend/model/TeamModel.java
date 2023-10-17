package com.uah.f1backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;

@Entity(name = "team")
@Getter
@Setter
@RequiredArgsConstructor
public class TeamModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=TEAM_ID)
    private Integer id;
    @Column(name=TEAM_NAME)
    private String name;
    @Column(name=TEAM_LOGO)
    private String logo;
    @Column(name=TEAM_TWITTER)
    private String twitter;
}
