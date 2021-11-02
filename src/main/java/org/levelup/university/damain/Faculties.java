package org.levelup.university.damain;

public class Faculties {
    private Integer facultiesId;
    private String name;
    private Long universityId;

    public Faculties(Integer facultiesId, String name, Long universityId) {
        this.facultiesId = facultiesId;
        this.name = name;
        this.universityId = universityId;
    }

    public Integer getFacultiesId() {
        return facultiesId;
    }

    public String getName() {
        return name;
    }

    public Long universityId() {
        return universityId;
    }

    @Override
    public String toString() {
        return "Faculties{" +
                "facultiesId=" + facultiesId +
                ", name='" + name + '\'' +
                ", universityId=" + universityId +
                '}';
    }
}
