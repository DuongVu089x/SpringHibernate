package com.dav.spring.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dav.spring.hibernate.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);
}
