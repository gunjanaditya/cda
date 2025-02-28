package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyDaoImpl implements FacultyDao{
	@Autowired
	private FacultyRepository repo;

	@Override
	public Optional<Faculty> findFacultyByFid(int id) {
		return repo.findById(id);
	}

	@Override
	public Faculty saveFaculty(Faculty f) {
		return repo.save(f);
	}

	@Override
	public List<Faculty> findAllfaculty() {
		return repo.findAll();
	}
	
	
}
