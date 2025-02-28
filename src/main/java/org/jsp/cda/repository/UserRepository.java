package org.jsp.cda.repository;

import java.util.Optional;

import org.jsp.cda.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsernameAndPassword(String username, String password);
	
}
