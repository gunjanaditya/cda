package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdministratorDaoImpl implements AdministratorDao{
	@Autowired
	private AdministratorRepository repo;

	@Override
	public Administrator saveAdministrator(Administrator administrator) {
		return repo.save(administrator);
	}

	@Override
	public List<Administrator> findAllAdministrator() {
		return repo.findAll();
	}

	@Override
	public Optional<Administrator> findAdministratorById(int id) {
		return repo.findById(id);
	}
}
