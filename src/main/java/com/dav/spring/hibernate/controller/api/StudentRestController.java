package com.dav.spring.hibernate.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.entity.Student;
import com.dav.spring.hibernate.service.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentRestController.
 */
@RestController
@RequestMapping("/api/student")
public class StudentRestController {

	/** The student service. */
	@Autowired
	private StudentService studentService;

	/**
	 * Gets the student by keyword.
	 *
	 * @param keyword the keyword
	 * @return the student by keyword
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/getStudentByKeyword", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Student> getStudentByKeyword(
			@RequestParam(required = true, defaultValue = "", value = "keyword") String keyword) throws Exception {
		return studentService.getStudentByPageAndKeyword(Constants.NUM_1, keyword).getContent();
	}

}
