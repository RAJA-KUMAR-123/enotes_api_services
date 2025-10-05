package com.enote.service;

import java.util.List;

import com.enote.dto.CategoryDto;
import com.enote.dto.CategoryResponse;

public interface CategoryService {
	
	public Boolean saveCategory(CategoryDto categoryDto);
	public List<CategoryDto> getAllCategory();
//	public List<Category> getAllCategory();
	public List<CategoryResponse> getActiveCategory();
	public CategoryDto getCategoryByid(Integer id) throws Exception;
	public Boolean deletedById(Integer id);
}
