package org.jsp.cda.daoimpl;

import java.util.List;
import java.util.Optional;

import org.jsp.cda.dao.UserDao;
import org.jsp.cda.entity.User;
import org.jsp.cda.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserRepository repo;

	@Override
	public User saveUser(User user) {
		return repo.save(user);
	}

	@Override
	public Optional<User> findUserById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<User> findAllUser() {
		return repo.findAll();
	}

	@Override
	public Optional<User> findUserByUsernameAndPassword(String username, String password) {
		return repo.findByUsernameAndPassword(username,password);
	}
}
