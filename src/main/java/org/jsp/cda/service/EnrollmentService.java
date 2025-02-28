package org.jsp.cda.service;

import org.springframework.http.ResponseEntity;

public interface EnrollmentService {

	ResponseEntity<?> saveEnrollment(int sid, int cid);

	ResponseEntity<?> findEnrollmentByCourseId(int cid);

	ResponseEntity<?> findEnrollmentsByStudentId(int sid);

	ResponseEntity<?> findAllEnrollment();

	ResponseEntity<?> findEnrollmentById(int id);

	ResponseEntity<?> deleteEnrollmentById(int id);

}
