package com.jacr.api_rest.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roles_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    public RolesEntity(Long id, RoleEnum roleEnum) {
        this.id = id;
        this.roleEnum = roleEnum;
    }
    public RolesEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
