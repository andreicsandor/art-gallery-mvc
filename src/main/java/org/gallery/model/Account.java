package org.gallery.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Account")
public class Account extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Column(name = "username", nullable = false, unique = true, length = 25)
    private String username;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    public Account() {
        this.role = null;
        this.username = null;
        this.password = null;
    }

    public Account(String firstName, String lastName, String role, String username, String password) {
        super(firstName, lastName);
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Account account = (Account) obj;
        return Objects.equals(username, account.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getFirstName() + ", " + getLastName() + " | " + getRole();
    }
}
