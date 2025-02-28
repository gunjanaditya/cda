package org.jsp.cda.service;

import org.jsp.cda.entity.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

	ResponseEntity<?> saveStudent(Student student, int uid);

	ResponseEntity<?> findStudentById(int id);

	ResponseEntity<?> findAll();

	ResponseEntity<?> assignDepartmentToStudent(int sid, int did);

	ResponseEntity<?> uploadPhoto(MultipartFile file, int sid);

}
