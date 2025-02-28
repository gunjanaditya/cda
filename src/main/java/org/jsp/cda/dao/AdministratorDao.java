package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.Administrator;

public interface AdministratorDao {

	Administrator saveAdministrator(Administrator administrator);

	List<Administrator> findAllAdministrator();

	Optional<Administrator> findAdministratorById(int id);

}
