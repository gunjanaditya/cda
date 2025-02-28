package org.jsp.cda.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.DepartmentDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Department;
import org.jsp.cda.entity.Student;
import org.jsp.cda.entity.User;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.StudentService;
import org.jsp.cda.utility.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public ResponseEntity<?> saveStudent(Student student, int uid) {
		Optional<User> optional1 = userDao.findUserById(uid);
		if (optional1.isEmpty()) {
			throw new RuntimeException("Invalid User Id...Unable to save");
		}
		User user = optional1.get();
		if (user.getRole() != UserRole.STUDENT) {
			throw new RuntimeException("Invalid user id...STUDENT role dosent match found " + user.getRole());
		}

		student.setUser(user);
		student = studentDao.saveStudent(student);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student Saved successfully").body(student).build());
	}

//	@Override
//	public ResponseEntity<?> saveStudent(Student student) {
//		Optional<User> optional1 = userDao.findUserById(uid);
//		if (optional1.isEmpty()) {
//			throw new RuntimeException("User not found");
//		}
//		String photo = null;
//		User user = optional1.get();
//		Student s = Student.builder().id(uid).photo(photo).user(user).build();
//		studentDao.saveStudent(s);
//
//		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
//				.message("Student Saved successfully").body(s).build());
//	}

	@Override
	public ResponseEntity<?> findStudentById(int id) {
		Optional<Student> optional = studentDao.findStudentById(id);
		if (optional.isEmpty()) {
			throw new RuntimeException("Student not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student fetched successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> findAll() {
		List<Student> s = studentDao.findAll();
		if (s.isEmpty()) {
			throw new RuntimeException("Student not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Student fetched successfully").body(s).build());
	}

	@Override
	public ResponseEntity<?> assignDepartmentToStudent(int sid, int did) {
		Optional<Student> optional1 = studentDao.findStudentById(sid);
		if (optional1.isEmpty()) {
			throw new RuntimeException("Invalid Student Id...Unable to assign department!!!");
		}
		Optional<Department> optional2 = departmentDao.findDepartmentById(did);
		if (optional2.isEmpty()) {
			throw new RuntimeException("Invalid Department Id...Uable to assign department!!!");
		}
		Student s = optional1.get();
		Department d = optional2.get();
		s.setDepartment_id(d);
		s = studentDao.saveStudent(s);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("Department Assigned to Student successfully").body(s).build());
	}

	@Override
	public ResponseEntity<?> uploadPhoto(MultipartFile file, int sid) {
		Optional<Student> optional = studentDao.findStudentById(sid);
		if (optional.isEmpty()) {
			throw new RuntimeException("Invalid Id...Unable to upload photo!!!");
		}
		Student student = optional.get();
		String folderPath = "C:\\Users\\HP\\Desktop\\Uploaded Images\\";
		String filename = file.getOriginalFilename();
		String filePath = folderPath + filename;
		File f = new File(filePath);
		try {
			file.transferTo(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		student.setPhoto(filePath);
		student = studentDao.saveStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("photo uploaded sucessfully").body(student).build());

	}
}