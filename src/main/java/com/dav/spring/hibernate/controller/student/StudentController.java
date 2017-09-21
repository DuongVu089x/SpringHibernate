package com.dav.spring.hibernate.controller.student;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.config.TokenHandler;
import com.dav.spring.hibernate.entity.Clazz;
import com.dav.spring.hibernate.entity.Student;
import com.dav.spring.hibernate.service.ClassService;
import com.dav.spring.hibernate.service.StudentService;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentController.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	/** The student service. */
	@Autowired
	private StudentService studentService;

	/** The class service. */
	@Autowired
	private ClassService classService;

	/** The token handler. */
	@Autowired
	private TokenHandler tokenHandler;

	/**
	 * Index.
	 *
	 * @param model the model
	 * @param principal the principal
	 * @param httpSession the http session
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD', 'USER')")
	@RequestMapping(value = { "", "/", "/index", "insert" }, method = RequestMethod.GET)
	public String index(ModelMap model, Principal principal, HttpSession httpSession) throws Exception {
		if (principal != null && principal.getName() != null && !principal.getName().equals("")) {
			String role = "";
			Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities();
			for (GrantedAuthority simpleGrantedAuthority : authorities) {
				role += simpleGrantedAuthority.toString();
			}
			httpSession.setAttribute(Constants.STR_ROLE, role);
			httpSession.setAttribute(Constants.STR_USERNAME, principal.getName());
			httpSession.setAttribute(Constants.STR_JWT, tokenHandler.createTokenForUser(role));
			Student student = new Student();
			model.addAttribute(Constants.STR_STUDENT, student);
		}
		return Constants.STR_STUDENT;
	}

	/**
	 * Edits the.
	 *
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD', 'USER')")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(ModelMap model, @PathVariable("id") Integer id) throws Exception {
		Student student = studentService.findById(id);
		if(student != null){
			model.addAttribute(Constants.STR_STUDENT, studentService.findById(id));
			return Constants.STR_STUDENT;
		}else{
			return "redirect:/student/";
		}
	}

	/**
	 * Insert.
	 *
	 * @param student the student
	 * @param bindingResult the binding result
	 * @param principal the principal
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD')")
	@RequestMapping(value = "/insert")
	public String insert(@Valid Student student, BindingResult bindingResult,
			Principal principal, ModelMap model) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute(Constants.STR_STUDENT, student);
			model.addAttribute(Constants.STR_MESSAGE, Constants.STR_INSERT_SUCCESS );
			return Constants.STR_STUDENT;
		} else {
			studentService.insertStudent(student, principal.getName());
			return "redirect:/student/";
		}
	}

	/**
	 * Update.
	 *
	 * @param student the student
	 * @param bindingResult the binding result
	 * @param principal the principal
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD')")
	@RequestMapping(value = "/update")
	public String update(@Valid Student student, BindingResult bindingResult,
			Principal principal, ModelMap model) throws Exception {
		//studentValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute(Constants.STR_STUDENT, student);
			model.addAttribute(Constants.STR_MESSAGE, Constants.STR_UPDATE_UNSUCCESS);
			return Constants.STR_STUDENT;
		} else {
			studentService.updateStudent(student, principal.getName());
			return "redirect:/student/";
		}
	}

	/**
	 * Delete.
	 *
	 * @param model the model
	 * @param id the id
	 * @param principal the principal
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/delete/{id}")
	public String delete(ModelMap model, @PathVariable("id") Integer id, Principal principal) throws Exception {
		Student student = studentService.findById(id);
		if(student != null){
			studentService.delete(id, principal.getName());
		}
		return "redirect:/student/";
	}

	/**
	 * Gets the page student.
	 *
	 * @param keyword the keyword
	 * @param page the page
	 * @param model the model
	 * @return the page student
	 * @throws Exception the exception
	 */
	@ModelAttribute("pageStudent")
	public Page<Student> getPageStudent(
			@RequestParam(required = true, defaultValue = "", value = "keyword") String keyword,
			@RequestParam(required = true, defaultValue = "1", value = "page") int page, ModelMap model) throws Exception {
		model.addAttribute(Constants.STR_KEYWORD, keyword);
		return studentService.getStudentByPageAndKeyword(page, keyword);
	}

	/**
	 * Gets the all class.
	 *
	 * @return the all class
	 * @throws Exception the exception
	 */
	@ModelAttribute("allClasses")
	public List<Clazz> getAllClass() throws Exception{
		return classService.getAllClass(Constants.NUM_1, Constants.STR_BLANK).getContent();
	}
}
