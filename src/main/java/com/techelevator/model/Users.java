package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Users {

    private int id;

    private String username;

    private String password_hash;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean activated;

    private Set<Authority> authorities = new HashSet<>();

    public Users() {
    }

    public Users(int id, String username, String password, String authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        if (authorities != null) this.setAuthorities(authorities);
        this.activated = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for (String role : roles) {
            String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
            this.authorities.add(new Authority(authority));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return id == users.id &&
                activated == users.activated &&
                Objects.equals(username, users.username) &&
                Objects.equals(password, users.password) &&
                Objects.equals(authorities, users.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, activated, authorities);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", activated=" + activated +
                ", authorities=" + authorities +
                '}';
    }

}
