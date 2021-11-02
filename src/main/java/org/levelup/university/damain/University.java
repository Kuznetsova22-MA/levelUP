package org.levelup.university.damain;

public class University {
    private Long universityID;
    private String name;
    private String shortName;
    private Integer foundationYear;

    public University(Long universityID, String name, String shortName, Integer foundationYear) {
        this.universityID = universityID;
        this.name = name;
        this.shortName = shortName;
        this.foundationYear = foundationYear;
    }

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
