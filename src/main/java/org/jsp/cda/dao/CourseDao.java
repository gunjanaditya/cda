package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Course;
import org.springframework.http.ResponseEntity;

public interface CourseDao {

	Course saveCourse(Course c);

	Optional<Course> fetchCourseById(int id);

	List<Course> fetchAllCourse();

	void deleteCourseById(int id);

}
