package org.levelup.university.repository;

import org.levelup.university.damain.University;
import java.util.List;

public interface UniversityRepository {

    List<University> findAll();
    University createUniversity(String name, String shortName, Integer foundtionYear);
    University deleteUniversity(Long universityId);
    University findONEuniversity(Long universityId);
    University updateUniversity(University university);
    University updateUniversity(Long universityId, String name, String shortName, Integer foundtionYear);
    int countFaculties(Long universityId);

}
