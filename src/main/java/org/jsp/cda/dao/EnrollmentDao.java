package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Enrollment;

public interface EnrollmentDao {

	Enrollment saveEnrollment(Enrollment e);

	List<Enrollment> findAllEnrollments();

	List<Enrollment> findEnrollmentsByCourseId(int cid);

	Optional<Enrollment> findEnrollmentById(int id);

	void deleteEnrollmentById(int id);

	List<Enrollment> findAllEnrollmentsByStudentId(int sid);

}
