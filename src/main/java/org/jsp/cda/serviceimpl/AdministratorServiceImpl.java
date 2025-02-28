package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.EnrollmentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.User;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.AdministratorService;
import org.jsp.cda.utility.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl implements AdministratorService {
	@Autowired
	private AdministratorDao administratorDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveAdmistrator(Administrator administrator, int uid) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty()) {
			throw new RuntimeException("User not Found..");
		}
		User user = optional.get();
		if (user.getRole() != UserRole.ADMINISTRATOR) {
			throw new RuntimeException("User role is not similer to Administrator");
		}
		administrator.setUser(user);
		administrator = administratorDao.saveAdministrator(administrator);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Administrator saved successfully").body(administrator).build());
	}

	@Override
	public ResponseEntity<?> findAllAdministrator() {
		List<Administrator> administrators = administratorDao.findAllAdministrator();
		if(administrators.isEmpty()) {
			throw new RuntimeException("No Admistrator found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Administrator fetched successfully").body(administrators).build());
	}

	@Override
	public ResponseEntity<?> findAdministratorById(int id) {
		Optional<Administrator> optional = administratorDao.findAdministratorById(id);
		if(optional.isEmpty()) {
			throw new RuntimeException("No Admistrator found with this id");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Administrator fetched successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> assignDeprtmentToAdministrator(int aid, int did) {
		Optional<Administrator> optional1 = administratorDao.findAdministratorById(aid);
		if(optional1.isEmpty()) {
			throw new RuntimeException("can not assign dept.... No Administrator Found with this id "+aid);
		}
		Optional<Department> optional2 = departmentDao.findDepartmentById(did);
		if(optional2.isEmpty()) {
			throw new RuntimeException("No department found with this id "+did);
		}
		Administrator administrator = optional1.get();
		administrator.setDepartment(optional2.get());
		
		Administrator saveAdministrator = administratorDao.saveAdministrator(administrator);
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Administrator fetched successfully").body(saveAdministrator).build());
	}

}
