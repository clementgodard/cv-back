package fr.clems.cv.CV.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import fr.clems.cv.CV.entity.User;

public interface UserDAO extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);
}
