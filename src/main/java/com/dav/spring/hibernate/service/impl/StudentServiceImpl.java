package com.dav.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.entity.Student;
import com.dav.spring.hibernate.repository.StudentRepository;
import com.dav.spring.hibernate.service.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentServiceImpl.
 */
@Service
public class StudentServiceImpl implements StudentService {

	/** The student repository. */
	@Autowired
	private StudentRepository studentRepository;

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.StudentService#getStudentByPageAndKeyword(int, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<Student> getStudentByPageAndKeyword(int page, String keyword) throws Exception {
		PageRequest request = new PageRequest(page - 1, Constants.PAGE_SIZE);
		return studentRepository.getStudentByPageAndKeyword("%" + keyword + "%", request);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.StudentService#findById(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true)
	public Student findById(Integer id) throws Exception {
		return studentRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.StudentService#delete(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Integer id, String currentUser) throws Exception {
		studentRepository.deleteStudent(id, currentUser);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.StudentService#insertStudent(com.dav.spring.hibernate.entity.Student, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertStudent(Student student, String currentUser) throws Exception {
		student.setCreateName(currentUser);
		student.setUpdateName(currentUser);
		student.setIsActive(1);
		studentRepository.save(student);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.StudentService#updateStudent(com.dav.spring.hibernate.entity.Student, java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateStudent(Student student, String name) throws Exception {
		student.setCreateName(studentRepository.findOne(student.getId()).getCreateName());
		student.setUpdateName(name);
		student.setIsActive(1);
		studentRepository.save(student);
	}
}
