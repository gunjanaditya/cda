package org.jsp.cda.service;

import org.jsp.cda.entity.Faculty;
import org.springframework.http.ResponseEntity;

public interface FacultyService {

	ResponseEntity<?> saveFaculty(Faculty faculty, int uid);

	ResponseEntity<?> findFacultyById(int id);

	ResponseEntity<?> findAllFaculty();

	ResponseEntity<?> assignDepartmentToFaculty(int fid, int did);

	

}
