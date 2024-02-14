package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.VOTE_TABLE;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = VOTE_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class VoteModel {
    @Id
    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = VOTE_ID)
    private Integer id;

    @Column(name = VOTE_VOTER_NAME)
    private String voterName;

    @Column(name = VOTE_VOTER_EMAIL)
    private String voterEmail;

    @ManyToOne
    @JoinColumn(name = VOTE_SURVEY_ID)
    private SurveyModel survey;

    @ManyToOne
    @JoinColumn(name = VOTE_DRIVER_ID)
    private DriverModel driver;
}
