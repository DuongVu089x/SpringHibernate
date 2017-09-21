package com.dav.spring.hibernate.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dav.spring.hibernate.entity.User;
import com.dav.spring.hibernate.repository.UserRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationServiceImpl.
 */
@Service
public class AuthenticationServiceImpl implements UserDetailsService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetails userDetails = null;
		try {
			User userInfo = userRepository.findByUsername(username);
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userInfo.getRole());
			userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(userInfo.getUsername(),
					userInfo.getPassword(), Arrays.asList(authority));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userDetails;
	}
}
