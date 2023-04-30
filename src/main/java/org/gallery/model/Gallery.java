package org.gallery.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Gallery")
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gallery_id")
    private List<Exhibit> exhibits;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "gallery_id")
    private List<Account> employees;

    public Gallery() {}

    public Gallery(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Exhibit> getExhibits() {
        return exhibits;
    }

    public void addExhibit(Exhibit exhibit) {
        if (this.exhibits == null) {
            this.exhibits = new ArrayList<>();
        }
        this.exhibits.add(exhibit);
    }

    public void removeExhibit(Exhibit exhibit) {
        if (this.exhibits != null) {
            this.exhibits.remove(exhibit);
        }
    }

    public List<Account> getEmployees() {
        return employees;
    }

    public void addEmployee(Account employee) {
        if (this.employees == null) {
            this.employees = new ArrayList<>();
        }
        this.employees.add(employee);
    }

    public void removeEmployee(Account employee) {
        if (this.employees != null) {
            this.employees.remove(employee);
        }
    }

    @Override
    public String toString() {
        return "<<Gallery: " + id + ", Name: " + name + ", Location: " + location + ">>";
    }
}
