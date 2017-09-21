package com.dav.spring.hibernate.service;

import org.springframework.data.domain.Page;

import com.dav.spring.hibernate.entity.User;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Gets the user by username.
	 *
	 * @param username the username
	 * @return the user by username
	 * @throws Exception the exception
	 */
	User getUserByUsername(String username) throws Exception;

	/**
	 * Save.
	 *
	 * @param user the user
	 * @throws Exception the exception
	 */
	void save(User user) throws Exception;

	/**
	 * Gets the student by page and keyword.
	 *
	 * @param page the page
	 * @param keyword the keyword
	 * @return the student by page and keyword
	 * @throws Exception the exception
	 */
	Page<User> getStudentByPageAndKeyword(int page, String keyword) throws Exception;
}
