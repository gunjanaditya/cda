package org.jsp.cda.service;

import org.jsp.cda.entity.User;
import org.jsp.cda.utility.AuthUser;
import org.springframework.http.ResponseEntity;

public interface UserService {

	ResponseEntity<?> saveUser(User u);

	ResponseEntity<?> findUserById(int id);

	ResponseEntity<?> findAllUser();

	ResponseEntity<?> findUserByUsernameAndPassword(AuthUser au);

	ResponseEntity<?> verifyOTP(int otp, int uid);

}
