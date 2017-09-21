package com.dav.spring.hibernate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dav.spring.hibernate.entity.Student;

// TODO: Auto-generated Javadoc
/**
 * The Interface StudentRepository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	/**
	 * Gets the student by page and keyword.
	 *
	 * @param keyword the keyword
	 * @param pageable the pageable
	 * @return the student by page and keyword
	 */
	@Query
	Page<Student> getStudentByPageAndKeyword(@Param("keyword") String keyword, Pageable pageable);

	/**
	 * Delete student.
	 *
	 * @param id the id
	 * @param currentName the current name
	 */
	@Modifying
	@Query
	void deleteStudent(@Param("id") Integer id, @Param("currentName") String currentName);
}
