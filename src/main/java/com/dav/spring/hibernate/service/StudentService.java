package com.dav.spring.hibernate.service;

import org.springframework.data.domain.Page;

import com.dav.spring.hibernate.entity.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentService.
 */
public interface StudentService {

	/**
	 * Gets the student by page and keyword.
	 *
	 * @param page the page
	 * @param keyword the keyword
	 * @return the student by page and keyword
	 * @throws Exception the exception
	 */
	Page<Student> getStudentByPageAndKeyword(int page, String keyword) throws Exception;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the student
	 * @throws Exception the exception
	 */
	Student findById(Integer id) throws Exception;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @param name the name
	 * @throws Exception the exception
	 */
	void delete(Integer id, String name) throws Exception;

	/**
	 * Insert student.
	 *
	 * @param student the student
	 * @param currentUser the current user
	 * @throws Exception the exception
	 */
	void insertStudent(Student student, String currentUser) throws Exception;

	/**
	 * Update student.
	 *
	 * @param student the student
	 * @param name the name
	 * @throws Exception the exception
	 */
	void updateStudent(Student student, String name) throws Exception;

}
