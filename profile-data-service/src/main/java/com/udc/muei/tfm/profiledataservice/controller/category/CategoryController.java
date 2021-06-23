package com.udc.muei.tfm.profiledataservice.controller.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udc.muei.tfm.profiledataservice.model.blog.BlogRepository;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogRateRepository;
import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryService;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRepository;
import com.udc.muei.tfm.profiledataservice.model.template.FileRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class CategoryController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/categoryAPI")
@Api(tags = "Category Controller", description = "This API has a CRUD for Categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	FileRepository fileRepository;

	@Autowired
	BlogRateRepository valorationRepository;

	@ApiOperation(value = "Search all Categories")
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories() {
		try {
			List<Category> categories = new ArrayList<Category>();

			categoryService.getAllCategories().forEach(categories::add);

			List<CategoryDTO> categoriesDtos = new ArrayList<CategoryDTO>();

			for (Category category : categories) {
				CategoryDTO categoryDto = new CategoryDTO();
				categoryDto.setCategoryId(category.getCategoryId());
				categoryDto.setCategoryName(category.getCategoryName());
				categoryDto.setCategoryDescription(category.getCategoryDescription());
				categoriesDtos.add(categoryDto);
			}

			if (categoriesDtos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(categoriesDtos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search Category by ID")
	@GetMapping("/getCategoryById")
	public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id != null && !id.isEmpty()) {
				Category categoryData = categoryService.findByCategoryId(id);
				if (categoryData != null) {
					CategoryDTO categoryDto = new CategoryDTO();
					categoryDto.setCategoryId(categoryData.getCategoryId());
					categoryDto.setCategoryName(categoryData.getCategoryName());
					categoryDto.setCategoryDescription(categoryData.getCategoryDescription());
					return new ResponseEntity<>(categoryDto, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search Category by CategoryName")
	@GetMapping("/getCategoryByCategoryName}")
	public ResponseEntity<CategoryDTO> getCategoryByCategoryName(
			@RequestParam(required = true) @PathVariable("categoryName") String categoryName) {
		try {
			if (categoryName == null || categoryName.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Category categoryData = categoryService.findByCategoryName(categoryName);

			if (categoryData != null) {

				CategoryDTO categoryDto = new CategoryDTO();

				categoryDto.setCategoryId(categoryData.getCategoryId());
				categoryDto.setCategoryName(categoryData.getCategoryName());
				categoryDto.setCategoryDescription(categoryData.getCategoryDescription());

				if (categoryDto != null) {
					return new ResponseEntity<>(categoryDto, HttpStatus.OK);
				}
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Category")
	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO category) {
		try {
			if (category != null && category.getCategoryName() != null && !category.getCategoryName().isEmpty()
					&& category.getCategoryDescription() != null && !category.getCategoryDescription().isEmpty()) {
				Category categoryData = new Category();
				categoryData.setCategoryName(category.getCategoryName());
				categoryData.setCategoryDescription(category.getCategoryDescription());
				Category _Category = categoryService.save(categoryData);
				if (_Category != null) {
					CategoryDTO categoryDto = new CategoryDTO();
					categoryDto.setCategoryId(_Category.getCategoryId());
					return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update Category by ID")
	@PutMapping("/updateCategory")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestBody CategoryDTO category) {
		try {
			if (id == null || id.isEmpty() || category == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Category _Category = new Category();
			_Category.setCategoryId(id);
			_Category.setCategoryName(category.getCategoryName());
			_Category.setCategoryDescription(category.getCategoryDescription());
			Category updatedCategory = categoryService.update(_Category);

			if (updatedCategory != null) {
				CategoryDTO categoryDto = new CategoryDTO();
				categoryDto.setCategoryId(updatedCategory.getCategoryId());
				return new ResponseEntity<>(categoryDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Remove Category by ID")
	@DeleteMapping("/category")
	public ResponseEntity<?> deleteCategory(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			categoryService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Category not
			// found!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}