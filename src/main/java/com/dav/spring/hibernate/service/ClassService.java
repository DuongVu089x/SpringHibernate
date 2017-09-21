package com.dav.spring.hibernate.service;

import org.springframework.data.domain.Page;

import com.dav.spring.hibernate.entity.Clazz;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClassService.
 */
public interface ClassService {

	/**
	 * Gets the all class.
	 *
	 * @param page the page
	 * @param keyword the keyword
	 * @return the all class
	 * @throws Exception the exception
	 */
	Page<Clazz> getAllClass(int page, String keyword) throws Exception;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the clazz
	 * @throws Exception the exception
	 */
	Clazz findById(Integer id) throws Exception;

	/**
	 * Update student.
	 *
	 * @param clazz the clazz
	 * @param name the name
	 * @throws Exception the exception
	 */
	void updateStudent(Clazz clazz, String name) throws Exception;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @param name the name
	 * @throws Exception the exception
	 */
	void delete(Integer id, String name) throws Exception;

	/**
	 * Insert class.
	 *
	 * @param clazz the clazz
	 * @param name the name
	 * @throws Exception the exception
	 */
	void insertClass(Clazz clazz, String name) throws Exception;

}
