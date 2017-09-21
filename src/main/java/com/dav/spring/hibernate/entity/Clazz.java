package com.dav.spring.hibernate.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Clazz.
 */
@Entity
@Table(name = "classes")
public class Clazz implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/** The name. */
	@NotNull
	@Size(min = 2, max = 255)
	@Pattern(regexp = "^[a-zA-Z#\\d\\p{L}]+( [a-zA-Z#\\d\\p{L}]+)*$", message = "{Pattern.name}")
	private String name;

	/** The create name. */
	@NotNull
	@Size(min = 2, max = 255)
	@Pattern(regexp = "^[a-zA-Z\\d\\p{L}]+( [a-zA-Z\\d\\p{L}]+)*$", message = "{Pattern.name}")
	private String createName;

	/** The update name. */
	@NotNull
	@Size(min = 2, max = 255)
	@Pattern(regexp = "^[a-zA-Z\\d\\p{L}]+( [a-zA-Z\\d\\p{L}]+)*$", message = "{Pattern.name}")
	private String updateName;

	/** The is active. */
	private Integer isActive;

	/** The students. */
	@ManyToMany(mappedBy = "classes", fetch=FetchType.LAZY)
	@JsonIgnore
	Set<Student> students;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the creates the name.
	 *
	 * @return the creates the name
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * Sets the creates the name.
	 *
	 * @param createName the new creates the name
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * Gets the update name.
	 *
	 * @return the update name
	 */
	public String getUpdateName() {
		return updateName;
	}

	/**
	 * Sets the update name.
	 *
	 * @param updateName the new update name
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * Gets the checks if is active.
	 *
	 * @return the checks if is active
	 */
	public Integer getIsActive() {
		return isActive;
	}

	/**
	 * Sets the checks if is active.
	 *
	 * @param isActive the new checks if is active
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets the students.
	 *
	 * @return the students
	 */
	public Set<Student> getStudents() {
		return students;
	}

	/**
	 * Sets the students.
	 *
	 * @param students the new students
	 */
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
