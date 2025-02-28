package org.jsp.cda.repository;

import java.util.List;

import org.jsp.cda.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{

	@Query("select e from Enrollment e where e.course.id = ?1")
	List<Enrollment> findByCourse(int cid);

	@Query("select e from Enrollment e where e.student.id = ?1")
	List<Enrollment> findAllEnrollmentsByStudentId(int sid);
	
//	@Query("select e from Enrollment e where e.student.id=:uid")
//	List<Enrollment> findEnrollmentByUserId(int uid);
//	
//	@Query("select e from Enrollment e where e.course.faculty.id=:sid")
//	List<Enrollment> findEnrollmentByFacultyId(int fid);
//	
	
}
