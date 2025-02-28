package org.jsp.cda.service;

import org.jsp.cda.entity.Course;
import org.springframework.http.ResponseEntity;

public interface CourseService {

	ResponseEntity<?> saveCourse(Course c);

	ResponseEntity<?> fetchCourseById(int id);

	ResponseEntity<?> fetchAllCourse();

	ResponseEntity<?> deleteCourseById(int id);

	ResponseEntity<?> assignFacultyToCourse(int id, int fid);

	ResponseEntity<?> assignDepartmentToCourse(int id, int deptid);

}
