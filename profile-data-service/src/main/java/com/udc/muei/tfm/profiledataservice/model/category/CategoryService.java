package com.udc.muei.tfm.profiledataservice.model.category;

import java.util.List;

/*
 * 
 * The Interface CategoryService.
 * 
 * @author a.oteroc
 * 
 */
public interface CategoryService {

	/**
	 * save
	 * 
	 * @param category
	 * @return Category
	 */
	Category save(Category category);

	/**
	 * update
	 * 
	 * @param category
	 * @return Category
	 */
	Category update(Category category);

	/**
	 * findByCategoryId
	 * 
	 * @param categoryId
	 * @return Category
	 */
	Category findByCategoryId(String categoryId);

	/**
	 * findByCategoryName
	 * 
	 * @param categoryName
	 * @return Category
	 */
	Category findByCategoryName(String categoryName);

	/**
	 * delete
	 * 
	 * @param categoryId
	 */
	void delete(String categoryId);

	/**
	 * getAllCategories
	 * 
	 * @return List<Category>
	 */
	List<Category> getAllCategories();

}
