package org.jsp.cda.serviceimpl;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.AdministratorDao;
import org.jsp.cda.dao.FacultyDao;
import org.jsp.cda.dao.StudentDao;
import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.Administrator;
import org.jsp.cda.entity.Faculty;
import org.jsp.cda.entity.Student;
import org.jsp.cda.entity.User;
import org.jsp.cda.exceptionclasses.InvalidCredentialsFound;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.responcestructure.ResponseStructure;
import org.jsp.cda.service.UserService;
import org.jsp.cda.utility.AuthUser;
import org.jsp.cda.utility.Helper;
import org.jsp.cda.utility.UserRole;
import org.jsp.cda.utility.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private AdministratorDao administratorDao;
	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private Helper helper;

	@Override
	public ResponseEntity<?> saveUser(User user) {
		int otp = helper.generateOtp();
		user.setOtp(otp);

		helper.sendAccountCreationEmail(user);

		user.setStatus(UserStatus.iN_ACTIVE);
		String photo = null;

		user = userDao.saveUser(user);

//		if (user.getRole() == UserRole.ADMINISTRATOR) {
//			administratorDao
//					.saveAdministrator(Administrator.builder().id(user.getId()).photo(photo).user(user).build());
//		} else if (user.getRole() == UserRole.FACULTY) {
//			facultyDao.saveFaculty(Faculty.builder().id(user.getId()).photo(photo).user(user)
//					.office_hours(LocalTime.of(8, 30)).build());
//		} else {
//			studentDao.saveStudent(Student.builder().id(user.getId()).photo(photo).user(user).build());
//		}
//
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User saved successfully").body(user).build());
	}

	@Override
	public ResponseEntity<?> findUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty()) {
			throw UserNotFoundException.builder().message("No User Found By this Id...").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User found successfully").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> findAllUser() {
		List<User> users = userDao.findAllUser();
		if (users.isEmpty()) {
			throw UserNotFoundException.builder().message("No User Found In DB...").build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User found successfully").body(users).build());
	}

	@Override
	public ResponseEntity<?> findUserByUsernameAndPassword(AuthUser au) {
		Optional<User> optional = userDao.findUserByUsernameAndPassword(au.getUsername(), au.getPassword());
		if (optional.isEmpty()) {
			throw InvalidCredentialsFound.builder().message("Invalid Credentials...").build();
		}
		User u = optional.get();
		if(u.getStatus()!=UserStatus.ACTIVE)
			throw new RuntimeException("Unable to login because your account is "+u.getStatus()+" plese contatct administrator to activate your account");
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseStructure.builder().status(HttpStatus.OK.value())
				.message("User verified successfully...").body(optional.get()).build());
	}

	@Override
	public ResponseEntity<?> verifyOTP(int otp, int uid) {
		Optional<User> optional = userDao.findUserById(uid);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid User id unable to ACTIVATE user");
		User u = optional.get();
		if (u.getOtp() != otp) {
			throw new RuntimeException("Invalid OTP unable to verify and activate your account");
		}
		u.setStatus(UserStatus.ACTIVE);
		userDao.saveUser(u);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResponseStructure.builder().status(HttpStatus.OK.value()).message("Account has been activeted successfully").body(u).build());
	}
}
