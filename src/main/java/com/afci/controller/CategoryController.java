package com.afci.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afci.data.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private final CategoryRepository catRepository;

	public CategoryController(CategoryRepository catRepository) {
		this.catRepository = catRepository;
	}

}
