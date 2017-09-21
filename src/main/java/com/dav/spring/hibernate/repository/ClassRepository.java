package com.dav.spring.hibernate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dav.spring.hibernate.entity.Clazz;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClassRepository.
 */
@Repository
public interface ClassRepository extends JpaRepository<Clazz, Integer>{

	/**
	 * Gets the class by page and keyword.
	 *
	 * @param keyword the keyword
	 * @param pageable the pageable
	 * @return the class by page and keyword
	 * @throws Exception the exception
	 */
	@Query
	Page<Clazz> getClassByPageAndKeyword(@Param("keyword") String keyword, Pageable pageable) throws Exception;

	/**
	 * Delete class.
	 *
	 * @param id the id
	 * @param currentName the current name
	 * @throws Exception the exception
	 */
	@Modifying
	@Query
	void deleteClass(@Param("id") Integer id, @Param("currentName") String currentName) throws Exception;

}
