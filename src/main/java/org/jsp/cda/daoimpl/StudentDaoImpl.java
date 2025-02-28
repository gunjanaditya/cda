package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.entity.Student;
import org.jsp.cda.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao{
	@Autowired 
	private StudentRepository repo;

	@Override
	public Student saveStudent(Student student) {
		return repo.save(student);
	}

	@Override
	public Optional<Student> findStudentById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Student> findAll() {
		return repo.findAll();
	}
}
