package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Faculty;
import org.springframework.http.ResponseEntity;

public interface FacultyDao {

	Optional<Faculty> findFacultyByFid(int id);

	Faculty saveFaculty(Faculty f);

	List<Faculty> findAllfaculty();

}
