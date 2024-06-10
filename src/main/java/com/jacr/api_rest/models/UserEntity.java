package com.jacr.api_rest.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(name = "is_enabled")
    private boolean isEnable;
    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;
    @Column(name = "account_No_locked")
    private boolean accountNoLocked;
    @Column(name = "credential_No_Expired")
    private boolean credentialNoExprired;

    //Un usuario puede tener muchos roles, varios roles puede tener varios usuarios
    //relación muchos a muchos unidireccional
    //agregramos un Set() funciona igual que una lista, usamos Set() para no tener objectos repetidos
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolesEntity> rolesEntities = new HashSet<>();


    public UserEntity(Long id, String username, String password, boolean isEnable, boolean accountNoExpired, boolean accountNoLocked, boolean credentialNoExprired,Set<RolesEntity> rolesEntities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnable = isEnable;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialNoExprired = credentialNoExprired;
        this.rolesEntities = rolesEntities;
    }

    public UserEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public boolean isAccountNoExpired() {
        return accountNoExpired;
    }

    public void setAccountNoExpired(boolean accountNoExpired) {
        this.accountNoExpired = accountNoExpired;
    }

    public boolean isAccountNoLocked() {
        return accountNoLocked;
    }

    public void setAccountNoLocked(boolean accountNoLocked) {
        this.accountNoLocked = accountNoLocked;
    }

    public boolean isCredentialNoExprired() {
        return credentialNoExprired;
    }

    public void setCredentialNoExprired(boolean credentialNoExprired) {
        this.credentialNoExprired = credentialNoExprired;
    }
    public Set<RolesEntity> getRolesEntities() {
        return rolesEntities;
    }

    public void setRolesEntities(Set<RolesEntity> rolesEntities) {
        this.rolesEntities = rolesEntities;
    }
}
