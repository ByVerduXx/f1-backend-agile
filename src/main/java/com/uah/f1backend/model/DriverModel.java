package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.DRIVER_TABLE;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = DRIVER_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DriverModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = DRIVER_ID)
    private Integer id;

    @Column(name = DRIVER_NAME)
    private String name;

    @Column(name = DRIVER_LAST_NAME)
    private String lastName;

    @Column(name = DRIVER_INITIAL)
    private String initial;

    @Column(name = DRIVER_DORSAL)
    private Integer dorsal;

    @Column(name = DRIVER_PHOTO)
    private String photo;

    @Column(name = DRIVER_TWITTER)
    private String twitter;

    @ManyToOne
    @JoinColumn(name = DRIVER_ID_COUNTRY)
    private CountryModel country;

    @ManyToOne
    @JoinColumn(name = DRIVER_ID_TEAM)
    private TeamModel team;

    @ManyToMany(mappedBy = DRIVER_SURVEYS_MAPPING)
    private List<SurveyModel> surveys;

    @OneToMany(mappedBy = DRIVER_VOTES_MAPPING)
    private List<VoteModel> votes;
}
