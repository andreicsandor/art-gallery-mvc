package org.gallery.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="Exhibit", uniqueConstraints={@UniqueConstraint(columnNames = {"name", "artist"})})
public class Exhibit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "artist", nullable = false, length = 25)
    private String artist;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "year", nullable = false, length = 50)
    private int year;

    public Exhibit() {}

    public Exhibit(String name, String artist, String type, int year) {
        this.name = name;
        this.artist = artist;
        this.type = type;
        this.year = year;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Exhibit exhibit = (Exhibit) obj;
        return Objects.equals(artist, exhibit.artist) && Objects.equals(name, exhibit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "<<Exhibit: " + id + ", Name: " + name + ", Artist: " + artist + ", Type: " + type + ", Year: " + year + ">>";
    }
}
