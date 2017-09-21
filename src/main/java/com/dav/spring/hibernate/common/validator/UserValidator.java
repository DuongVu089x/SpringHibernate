package com.dav.spring.hibernate.common.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dav.spring.hibernate.entity.User;
import com.dav.spring.hibernate.service.UserService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserValidator.
 */
@Component
public class UserValidator implements Validator {

	/** The user service. */
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object o, Errors errors) {
		try {
			User user = (User) o;

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
			if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
				errors.rejectValue("username", "Size.userForm.username");
			}
			if (userService.getUserByUsername(user.getUsername()) != null) {
	            errors.rejectValue("username", "Duplicate.userForm.username");
	        }
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
			if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
				errors.rejectValue("password", "Size.userForm.password");
			}
			if (!user.getPasswordConfirm().equals(user.getPassword())) {
				errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
