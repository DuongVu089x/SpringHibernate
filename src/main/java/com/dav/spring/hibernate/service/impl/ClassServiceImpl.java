package com.dav.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.entity.Clazz;
import com.dav.spring.hibernate.repository.ClassRepository;
import com.dav.spring.hibernate.service.ClassService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassServiceImpl.
 */
@Service
public class ClassServiceImpl implements ClassService {

	/** The class repository. */
	@Autowired
	private ClassRepository classRepository;

	/* (non-Javadoc)
	 *
	 * @see com.dav.spring.hibernate.service.ClassService#getAllClass()
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Clazz> getAllClass(int page, String keyword) throws Exception {
		PageRequest request = new PageRequest(page - 1, Constants.PAGE_SIZE, Sort.Direction.ASC, Constants.STR_ID);
		return classRepository.getClassByPageAndKeyword(Constants.STR_PERCENT + keyword + Constants.STR_PERCENT, request);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.ClassService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Clazz findById(Integer id) throws Exception {
		return classRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.ClassService#updateStudent(com.dav.spring.hibernate.entity.Clazz, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateStudent(Clazz clazz, String name) throws Exception {
		clazz.setCreateName(classRepository.findOne(clazz.getId()).getCreateName());
		clazz.setUpdateName(name);
		clazz.setIsActive(1);
		classRepository.save(clazz);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.ClassService#delete(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Integer id, String currentUser) throws Exception {
		classRepository.deleteClass(id, currentUser);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.ClassService#insertClass(com.dav.spring.hibernate.entity.Clazz, java.lang.String)
	 */
	@Override
	public void insertClass(Clazz clazz, String currentUser) throws Exception {
		clazz.setCreateName(currentUser);
		clazz.setUpdateName(currentUser);
		clazz.setIsActive(1);
		classRepository.save(clazz);
	}
}
