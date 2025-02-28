package org.jsp.cda.service;

import org.jsp.cda.entity.Administrator;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {

	ResponseEntity<?> saveAdmistrator(Administrator administrator, int uid);

	ResponseEntity<?> findAllAdministrator();

	ResponseEntity<?> findAdministratorById(int id);

	ResponseEntity<?> assignDeprtmentToAdministrator(int aid, int did);

}
