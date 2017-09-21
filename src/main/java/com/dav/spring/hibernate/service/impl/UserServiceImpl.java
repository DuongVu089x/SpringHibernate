package com.dav.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.entity.User;
import com.dav.spring.hibernate.repository.UserRepository;
import com.dav.spring.hibernate.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/** The b crypt password encoder. */
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.dav.mybatis.service.UserService#getUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public User getUserByUsername(String username) throws Exception {
		return userRepository.findByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.UserService#save(com.dav.spring.hibernate.entity.User)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(User user) throws Exception {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("USER");
		userRepository.save(user);
	}

	/* (non-Javadoc)
	 * @see com.dav.spring.hibernate.service.UserService#getStudentByPageAndKeyword(int, java.lang.String)
	 */
	@Override
	public Page<User> getStudentByPageAndKeyword(int page, String keyword) throws Exception {
		 PageRequest request = new PageRequest(page - 1, Constants.PAGE_SIZE, Sort.Direction.ASC, "id");
		return userRepository.findAll(request);
	}

}
