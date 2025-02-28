package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Student;

public interface StudentDao {

	Student saveStudent(Student student);

	Optional<Student> findStudentById(int id);

	List<Student> findAll();

}
