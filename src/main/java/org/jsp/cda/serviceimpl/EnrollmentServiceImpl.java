package org.jsp.cda.serviceimpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.dao.EnrollmentDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.entity.Enrollment;
import org.jsp.cda.entity.Student;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.EnrollmentService;
import org.jsp.cda.utility.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Autowired
	private EnrollmentDao enrollmentDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private CourseDao courseDao;

	@Override
	public ResponseEntity<?> saveEnrollment(int sid, int cid) {
		Optional<Student> optional1 = studentDao.findStudentById(sid);
		if (optional1.isEmpty()) {
			throw new RuntimeException("Invalid Student Id...unable to enroll to course");
		}
		Student student = optional1.get();
		if (student.getUser().getRole() != UserRole.STUDENT) {
			throw new RuntimeException(
					"Invalid role Student miss match Expected student but found" + student.getUser().getRole());
		}

		Optional<Course> optional2 = courseDao.fetchCourseById(cid);
		if (optional2.isEmpty()) {
			throw new RuntimeException("Invalid Course ID...unable to enroll any student");
		}
		Course course = optional2.get();

		Enrollment e = Enrollment.builder().student(student).course(course).build();

		e = enrollmentDao.saveEnrollment(e);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Enrollment saved succwssfully").body(e).build());
	}

	@Override
	public ResponseEntity<?> findEnrollmentByCourseId(int cid) {
//		List<Enrollment> enrollments = enrollmentDao.findAllEnrollments();
//		List<Enrollment> filterEnrollments = new LinkedList<>();
//		for (Enrollment e : enrollments) {
//			if (e.getCourse().getId() == cid) {
//				filterEnrollments.add(e);
//			}
//		}

		Optional<Course> optional = courseDao.fetchCourseById(cid);
		if (optional.isEmpty()) {
			throw new RuntimeException("Invalid Course ID");
		}
		List<Enrollment> enrollments = enrollmentDao.findEnrollmentsByCourseId(cid);
		if (enrollments.isEmpty()) {
			throw new RuntimeException("No Enrollment fond in specified course with id " + cid);
		}

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("List of Enrollments in course with " + cid).body(enrollments).build());
	}

	@Override
	public ResponseEntity<?> findEnrollmentsByStudentId(int sid) {
		Optional<Student> optional = studentDao.findStudentById(sid);
		if (optional.isEmpty()) {
			throw new RuntimeException("Invalid Student Id");
		}
		List<Enrollment> enrollments = enrollmentDao.findAllEnrollmentsByStudentId(sid);
		if (enrollments.isEmpty()) {
			throw new RuntimeException("No enrollment found of any Student by id " + sid);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("List of Enrollments of student with " + sid).body(enrollments).build());
	}

	@Override
	public ResponseEntity<?> findAllEnrollment() {
		List<Enrollment> enrollments = enrollmentDao.findAllEnrollments();
		if (enrollments.isEmpty()) {
			throw new RuntimeException("No Enrollment found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("List of Enrollment fetched successfully...").body(enrollments).build());
	}

	@Override
	public ResponseEntity<?> findEnrollmentById(int id) {
		Optional<Enrollment> optional = enrollmentDao.findEnrollmentById(id);
		if (optional.isEmpty()) {
			throw new RuntimeException("No enrollment found by this id " + id);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Enrollemt with this id " + id + " found successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> deleteEnrollmentById(int id) {
		Optional<Enrollment> optional = enrollmentDao.findEnrollmentById(id);
		if (optional.isEmpty())
			throw new RuntimeException("No enrollment found by this id " + id);
		enrollmentDao.deleteEnrollmentById(id);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value())
						.message("Enrollemt with this id " + id + " Deleted successfully...")
						.body("Deleted Successfully").build());
	}
}
