package org.jsp.cda.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.entity.User;

public interface UserDao {

	User saveUser(User user);

	Optional<User> findUserById(int id);

	List<User> findAllUser();

	Optional<User> findUserByUsernameAndPassword(String username, String password);

}
