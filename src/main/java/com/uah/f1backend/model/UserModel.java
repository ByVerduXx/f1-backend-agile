package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.*;
import static com.uah.f1backend.configuration.common.TableNameConstants.USER_TABLE;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = USER_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Column(name = USER_ID)
    private Integer id;

    @Column(name = USER_EMAIL)
    private String email;

    @Column(name = USER_PASSWORD)
    private String password;

    @Column(name = USER_NAME)
    private String name;

    @Column(name = USER_LAST_NAME)
    private String lastname;

    @Column(name = USER_USERNAME)
    private String username;

    @OneToOne
    @JoinColumn(name = USER_ROLE)
    private RoleModel role;

    @Column(name = USER_VALIDATED)
    private Boolean validated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_TEAM)
    private TeamModel team;
}
