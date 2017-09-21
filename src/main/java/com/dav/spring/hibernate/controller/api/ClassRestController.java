package com.dav.spring.hibernate.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.entity.Clazz;
import com.dav.spring.hibernate.service.ClassService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassRestController.
 */
@RestController
@RequestMapping("/api/class")
public class ClassRestController {

	/** The class service. */
	@Autowired
	private ClassService classService;

	/**
	 * Gets the clazz by keyword.
	 *
	 * @param keyword the keyword
	 * @return the clazz by keyword
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/getClassByKeyword", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Clazz> getClazzByKeyword(
			@RequestParam(required = true, defaultValue = "", value = "keyword") String keyword) throws Exception {
		return classService.getAllClass(Constants.NUM_1, keyword).getContent();
	}
}
