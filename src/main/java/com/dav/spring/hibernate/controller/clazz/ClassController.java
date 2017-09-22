package com.dav.spring.hibernate.controller.clazz;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dav.spring.hibernate.common.util.Constants;
import com.dav.spring.hibernate.entity.Clazz;
import com.dav.spring.hibernate.service.ClassService;

// TODO: Auto-generated Javadoc
/**
 * The Class ClassController.
 */
@Controller
@RequestMapping("/class")
public class ClassController {

	/** The class service. */
	@Autowired
	private ClassService classService;

	/**
	 * Index.
	 *
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD')")
	@RequestMapping(value = { "", "/", "/index", "insert" }, method = RequestMethod.GET)
	public String index(ModelMap model) throws Exception {
		Clazz clazz = new Clazz();
		model.addAttribute(Constants.STR_CLAZZ, clazz);
		return Constants.STR_CLASS;
	}

	/**
	 * Insert.
	 *
	 * @param clazz the clazz
	 * @param bindingResult the binding result
	 * @param principal the principal
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD')")
	@RequestMapping(value = "/insert")
	public String insert(@Valid Clazz clazz, BindingResult bindingResult,
			Principal principal, ModelMap model) throws Exception {
		//studentValidator.validate(student, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute(Constants.STR_CLAZZ, clazz);
			model.addAttribute(Constants.STR_USERNAME, Constants.STR_INSERT_SUCCESS);
			return Constants.STR_CLASS;
		} else {
			classService.insertClass(clazz, principal.getName());
			return "redirect:/class/";
		}
	}

	/**
	 * Edits the.
	 *
	 * @param model the model
	 * @param id the id
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD')")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(ModelMap model, @PathVariable("id") Integer id) throws Exception {
		Clazz clazz = classService.findById(id);
		if(clazz != null){
			model.addAttribute("clazz", classService.findById(id));
			return Constants.STR_CLASS;
		}else{
			return "redirect:/class/";
		}
	}

	/**
	 * Update.
	 *
	 * @param clazz the clazz
	 * @param bindingResult the binding result
	 * @param principal the principal
	 * @param model the model
	 * @return the string
	 * @throws Exception the exception
	 */
	@PreAuthorize("hasAnyRole('ADMIN', 'MOD')")
	@RequestMapping(value = "/update")
	public String update(@Valid Clazz clazz, BindingResult bindingResult,
			Principal principal, ModelMap model) throws Exception {
		if (bindingResult.hasErrors()) {
			model.addAttribute(Constants.STR_CLAZZ, clazz);
			model.addAttribute(Constants.STR_USERNAME, Constants.STR_UPDATE_UNSUCCESS);
			return Constants.STR_CLASS;
		} else {
			classService.updateStudent(clazz, principal.getName());
			return "redirect:/class/";
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
		Clazz clazz = classService.findById(id);
		if (clazz != null) {
			classService.delete(id, principal.getName());
		}
		return "redirect:/class/";
	}


	/**
	 * Gets the all class.
	 *
	 * @param keyword the keyword
	 * @param page the page
	 * @param model the model
	 * @return the all class
	 * @throws Exception the exception
	 */
	@ModelAttribute("pageClass")
	public Page<Clazz> getAllClass(
			@RequestParam(required = true, defaultValue = "", value = "keyword") String keyword,
			@RequestParam(required = true, defaultValue = "1", value = "page") int page, ModelMap model) throws Exception {
		model.addAttribute(Constants.STR_KEYWORD, keyword);
		return classService.getAllClass(page, keyword);
	}
}
