package org.jsp.cda.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.entity.User;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.FacultyService;
import org.jsp.cda.utility.UserRole;
import org.jsp.cda.utility.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {
	@Autowired
	private FacultyDao facultydao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public ResponseEntity<?> saveFaculty(Faculty faculty, int uid) {
		Optional<User> optional1 = userDao.findUserById(uid);
		if(optional1.isEmpty()) {
			throw new RuntimeException("User not found");
		}
		User user = optional1.get();
		if(user.getRole() != UserRole.FACULTY) {
			throw new RuntimeException("User role is not same");
		}
		faculty.setUser(user);
		Faculty saveFaculty = facultydao.saveFaculty(faculty);
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty saved successfully").body(saveFaculty).build());
		
	}
	@Override
	public ResponseEntity<?> findFacultyById(int id) {
		Optional<Faculty> optional = facultydao.findFacultyByFid(id);
		if(optional.isEmpty()) {
			throw new RuntimeException("Faculty not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Fetched successfully").body(optional.get()).build());
	}
	@Override
	public ResponseEntity<?> findAllFaculty() {
		List<Faculty> f = facultydao.findAllfaculty();
		if(f.isEmpty()) {
			throw new RuntimeException("Faculty not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Faculty Fetched successfully").body(f).build());
	}
	@Override
	public ResponseEntity<?> assignDepartmentToFaculty(int fid, int did) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
//---------------------------------------------------------------------------------------------
//	@Override
//	public ResponseEntity<?> saveFaculty(int uid) {
//		Optional<User> optional1 = userDao.findUserById(uid);
//		String photo = null;
//		if (optional1.isEmpty()) {
//			throw new RuntimeException("Invalid User ID");
//		}
//		Faculty f = Faculty.builder().photo(photo).id(uid).user(optional1.get()).build();
//
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
//				.message("Faculty saved successfully").body(f).build());
//	}
//
//	@Override
//	public ResponseEntity<?> findAll() {
//		List<Faculty> f = facultydao.findAllfaculty();
//		if(f.isEmpty()) {
//			throw new RuntimeException("Faculty not found");
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
//				.message("Faculty Fetched successfully").body(f).build());
//	}
//
//	@Override
//	public ResponseEntity<?> findFacultyById(int id) {
//		Optional<Faculty> optional = facultydao.findFacultyByFid(id);
//		if(optional.isEmpty()) {
//			throw new RuntimeException("Faculty not found");
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
//				.message("Faculty Fetched successfully").body(optional.get()).build());
//	}
	
}
