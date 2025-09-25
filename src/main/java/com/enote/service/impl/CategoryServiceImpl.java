package com.enote.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.enote.dto.CategoryDto;
import com.enote.dto.CategoryResponse;
import com.enote.model.Category;
import com.enote.repository.CategoryRepository;
import com.enote.service.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired 
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper mapper;
	
//	@Override
//	public boolean saveCategory(Category category) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	
	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		
//		Category category = new Category();
//		category.setName(categoryDto.getName());
//		category.setDescription(categoryDto.getDescription());
//		category.setIsActive(categoryDto.getIsActive());	
		
		Category category = mapper.map(categoryDto, Category.class);
		
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category saveCategory =  categoryRepo.save(category);
		if(ObjectUtils.isEmpty(saveCategory) && saveCategory == null) {
			return false;
		}
		return true;
	}
	
	
//	@Override
//	public List<Category> getAllCategory() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> getAllCategory=categoryRepo.findAll();
		
		List<CategoryDto> categoryDtoList = getAllCategory.stream().map(cat->mapper.map(cat,CategoryDto.class)).toList();
		
		return categoryDtoList;
	}


	@Override
	public List<CategoryResponse> getActiveCategory() {
		List<Category> categories = categoryRepo.findByIsActiveTrue();
		
		List<CategoryResponse> categoriesList = categories.stream().map(cat->mapper.map(cat, CategoryResponse.class)).toList();
		return categoriesList;
	}


	@Override
	public CategoryDto getCategoryByid(Integer id) {
		
		Optional<Category> findBycategory = categoryRepo.findById(id);
		if(findBycategory.isPresent()) {
			Category category = findBycategory.get();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}


	@Override
	public Boolean deletedById(Integer id) {
		Optional<Category> findBycategory = categoryRepo.findById(id);
		if(findBycategory.isPresent()) {
			Category category = findBycategory.get();
			category.setIsDeleted(true);
			categoryRepo.save(category);
			return true;
		}
		return false;
	}


	
	
}
