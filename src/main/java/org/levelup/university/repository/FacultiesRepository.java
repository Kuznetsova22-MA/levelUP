package org.levelup.university.repository;

import org.levelup.university.damain.Faculties;

import java.sql.ResultSet;
import java.util.List;

public interface FacultiesRepository {
    //
    public Faculties createFaculties(String nameFacultet, Long universityId);
    public List<Faculties> facultiesList(Long universityId);


}
