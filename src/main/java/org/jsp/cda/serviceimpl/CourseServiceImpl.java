package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.CourseDao;
import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.entity.Course;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.exceptionclasses.CourseNotFoundException;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao dao;
	@Autowired
	private FacultyDao facultydao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveCourse(Course c) {
		c = dao.saveCourse(c);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course Saved Successfully").body(c).build());
	}

	@Override
	public ResponseEntity<?> fetchCourseById(int id) {
		Optional<Course> optional = dao.fetchCourseById(id);
		if (optional.isEmpty()) {
			throw CourseNotFoundException.builder().message("No Course Found by this id in DB").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course Fatched Successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> fetchAllCourse() {
		List<Course> c = dao.fetchAllCourse();
		if (c.isEmpty()) {
			throw CourseNotFoundException.builder().message("No Course Found in DB").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course fetched Successfully").body(c).build());
	}

	@Override
	public ResponseEntity<?> deleteCourseById(int id) {
		Optional<Course> optional = dao.fetchCourseById(id);
		if (optional.isEmpty()) {
			throw CourseNotFoundException.builder().message("No Course Found by this id...unable to delete").build();
		}
		dao.deleteCourseById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Course Deleted Successfully").body("Course Deleted").build());
	}

	@Override
	public ResponseEntity<?> assignFacultyToCourse(int id, int fid) {
		Optional<Faculty> optional1 = facultydao.findFacultyByFid(fid);
		if(optional1.isEmpty()) {
			throw new RuntimeException("Invalid faculty id"+fid);
		}
		Optional<Course> optional2 = dao.fetchCourseById(id);
		if(optional2.isEmpty()) {
			throw CourseNotFoundException.builder().message("No Course Found by this id"+id).build();
		}
		Faculty faculty = optional1.get();
		Course course = optional2.get();
		
		course.setFaculty(faculty);
		course = dao.saveCourse(course);
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Assigned to course").body(course).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToCourse(int id, int deptid) {
		Optional<Department> optional1 = departmentDao.findDepartmentById(deptid);
		if(optional1.isEmpty()) {
			throw new RuntimeException("Invalid faculty id"+deptid);
		}
		Optional<Course> optional2 = dao.fetchCourseById(id);
		if(optional2.isEmpty()) {
			throw CourseNotFoundException.builder().message("No Course Found by this id"+id).build();
		}
		Department dept = optional1.get();
		Course course = optional2.get();
		
		course.setDepartment(dept);
		course = dao.saveCourse(course);
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Deprtment Assigned to course").body(course).build());
	}

}
