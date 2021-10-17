package com.dimasguilherme.client;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

@Entity
@UserDefinition
@Table(name = "clients")
public class Client extends PanacheEntityBase {
    @Id
    @Username
    private String email;
    private String name;
    @Password
    private String password;
    @Roles
    private String role;

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    @JsonbTransient
    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public static void insert(Client client) {
        client.password = BcryptUtil.bcryptHash(client.password);
        client.role = client.email.contains("admin") ? "admin" : "client";
        client.persist();
    }
}
