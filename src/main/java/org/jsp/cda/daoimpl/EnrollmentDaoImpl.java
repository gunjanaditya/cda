package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.EnrollmentDao;
import org.jsp.cda.entity.Enrollment;
import org.jsp.cda.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnrollmentDaoImpl implements EnrollmentDao{

	@Autowired
	private EnrollmentRepository repo;

	@Override
	public Enrollment saveEnrollment(Enrollment e) {
		return repo.save(e);
	}

	@Override
	public List<Enrollment> findAllEnrollments() {
		return repo.findAll();
	}

	@Override
	public List<Enrollment> findEnrollmentsByCourseId(int cid) {
		return repo.findByCourse(cid);
	}

	@Override
	public Optional<Enrollment> findEnrollmentById(int id) {
		return repo.findById(id);
	}

	@Override
	public void deleteEnrollmentById(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Enrollment> findAllEnrollmentsByStudentId(int sid) {
		return repo.findAllEnrollmentsByStudentId(sid);
	}
}
