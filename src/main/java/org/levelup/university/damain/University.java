package org.levelup.university.damain;

import javax.persistence.*;

@Entity
@Table(name = "university")

public class University {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long universityID;
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "foundation_year")
    private Integer foundationYear;

    public University(Long universityID, String name, String shortName, Integer foundationYear) {
        this.universityID = universityID;
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
    }

    public University(){}

    public Long getUniversityID() {
        return universityID;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityID=" + universityID +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", foundationYear=" + foundationYear +
                '}';
    }
}
