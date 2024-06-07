package com.jacr.api_rest.models;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserModel {
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

    public UserModel(Long id, String username, String password, boolean isEnable, boolean accountNoExpired, boolean accountNoLocked, boolean credentialNoExprired) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isEnable = isEnable;
        this.accountNoExpired = accountNoExpired;
        this.accountNoLocked = accountNoLocked;
        this.credentialNoExprired = credentialNoExprired;
    }

    public UserModel(){}

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
}
