package com.afci.data;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// attributs
    private Long categoryId;

    private String categoryName;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Book> books;

	public Category(Long categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public Long getIdCategory() {
		return categoryId;
	}

	public void setIdCategory(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getNameCategory() {
		return categoryName;
	}

	public void setNameCategory(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + categoryId + ", nameCategory=" + categoryName + "]";
	}

}