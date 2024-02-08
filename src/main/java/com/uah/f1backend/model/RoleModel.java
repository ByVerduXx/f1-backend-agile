package com.uah.f1backend.model;

import static com.uah.f1backend.configuration.common.ColumnNameConstants.ROLE_ID;
import static com.uah.f1backend.configuration.common.ColumnNameConstants.ROLE_NAME;
import static com.uah.f1backend.configuration.common.TableNameConstants.ROLE_TABLE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = ROLE_TABLE)
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ROLE_ID)
    private Integer id;

    @Column(name = ROLE_NAME)
    private String roleName;
}
