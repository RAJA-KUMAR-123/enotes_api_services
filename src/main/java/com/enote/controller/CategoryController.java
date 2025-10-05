package com.enote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enote.dto.CategoryDto;
import com.enote.dto.CategoryResponse;
import com.enote.exception.ResourceNotFoundException;
import com.enote.model.Category;
import com.enote.service.CategoryService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

	@Autowired
     private CategoryService categoryService;
	
	@PostMapping("/category_save")
	 public ResponseEntity<?> saveCategory(@RequestBody CategoryDto categoryDto){
		 Boolean saveCategory = categoryService.saveCategory(categoryDto);
		 if(saveCategory) {
			// return CommonUtil.createResponseBuilderMessage("save ", HttpStatus.CREATED);
			 return new ResponseEntity<>("saved ", HttpStatus.CREATED);
		 }
	//	 return CommonUtil.createResponseErrorMessage("not save", HttpStatus.INTERNAL_SERVER_ERROR);
		 return new ResponseEntity<>("not save", HttpStatus.INTERNAL_SERVER_ERROR);
	 }
	
	
	@GetMapping("/get_category")
	 public ResponseEntity<?> getAllCategory(){
//		String name =null;
//		name.toUpperCase();
		 List<CategoryDto> getAllCategory = categoryService.getAllCategory();
		 if(CollectionUtils.isEmpty(getAllCategory)) {
			 return ResponseEntity.noContent().build();
		 }
//		 return CommonUtil.createResponseBuilder(getAllCategory, HttpStatus.OK);
		 return new ResponseEntity<>(getAllCategory, HttpStatus.OK);
	 }
	
	
	@GetMapping("/getActiveCategory")
	 public ResponseEntity<?> getActiveCategory(){
//		String name =null;
//		name.toUpperCase();
		 List<CategoryResponse> getAllCategory = categoryService.getActiveCategory();

		 System.out.println(getAllCategory);
		 if(CollectionUtils.isEmpty(getAllCategory)) {
			 return ResponseEntity.noContent().build();
		 }
//		 return CommonUtil.createResponseBuilder(getAllCategory, HttpStatus.OK);
		 return new ResponseEntity<>(getAllCategory, HttpStatus.OK);
	 }
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryid(@PathVariable Integer id) throws Exception{  
//		CategoryDto categoryDto=categoryService.getCategoryByid(id);   
//		   if(ObjectUtils.isEmpty(categoryDto)) {
//				 return new ResponseEntity<>("This id "+ id +" is not found",HttpStatus.NOT_FOUND);
//			 }
////		   return CommonUtil.createResponseErrorMessage(categoryDto, HttpStatus.OK);
//			 return new ResponseEntity<>(categoryDto, HttpStatus.OK);
		try {
			CategoryDto categoryDto=categoryService.getCategoryByid(id);
			   if(ObjectUtils.isEmpty(categoryDto)) {
					 return new ResponseEntity<>("This "+ id +" is not found",HttpStatus.NOT_FOUND);
				 }
				 return new ResponseEntity<>(categoryDto, HttpStatus.OK);
		}
		catch(ResourceNotFoundException e) {
			log.error("Controller :: getCategoryById ::  "+e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		catch(Exception e) {
			log.error("Controller :: getCategoryById ::  "+e.getMessage());
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id){
		Boolean  categoryDto = categoryService.deletedById(id);
		if(categoryDto) {
//			return CommonUtil.createResponseErrorMessage("Delete data successfully from your Database", HttpStatus.OK);
			 return new ResponseEntity<>("Delete data successfully from your Database", HttpStatus.OK);
		}
//		return CommonUtil.createResponseErrorMessage("This "+ id +" is not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>("This "+ id +" is not deleted",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
}
