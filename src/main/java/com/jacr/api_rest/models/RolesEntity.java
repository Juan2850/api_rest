package com.jacr.api_rest.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roles_name")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    //Rol puede tener muchos permisos y muchos permisos se le asignan a viarios roles
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permisions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permision_id"))
    private Set<PermissionEntity> permissionEntities = new HashSet<>();

    public RolesEntity(Long id, RoleEnum roleEnum, Set<PermissionEntity> permissionEntities) {
        this.id = id;
        this.roleEnum = roleEnum;
        this.permissionEntities = permissionEntities;
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

    public Set<PermissionEntity> getPermissionEntities() {
        return permissionEntities;
    }

    public void setPermissionEntities(Set<PermissionEntity> permissionEntities) {
        this.permissionEntities = permissionEntities;
    }
}
