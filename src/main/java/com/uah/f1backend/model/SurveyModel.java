package com.uah.f1backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.uah.f1backend.configuration.common.TableNameConstants.SURVEY_TABLE;
import static com.uah.f1backend.configuration.common.TableNameConstants.SURVEY_DRIVER_TABLE;
import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static jakarta.persistence.GenerationType.IDENTITY;
@Entity(name = SURVEY_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class SurveyModel {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = SURVEY_ID)
    private Integer id;

    @Column(name = SURVEY_PERMALINK)
    private String permalink;

    @Column(name = SURVEY_TITLE)
    private String title;

    @Column(name = SURVEY_DESCRIPTION)
    private String description;

    @Column(name = SURVEY_LIMIT_DATE)
    private Date limitDate;

    @ManyToMany
    @JoinTable(
            name = SURVEY_DRIVER_TABLE,
            joinColumns = @JoinColumn(name = SURVEY_DRIVER_SURVEY_ID),
            inverseJoinColumns = @JoinColumn(name = SURVEY_DRIVER_DRIVER_ID)
    )
    private List<DriverModel> drivers;

    @OneToMany(mappedBy = SURVEY_VOTES_MAPPING)
    private List<VoteModel> votes;
}
