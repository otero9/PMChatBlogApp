package com.udc.muei.tfm.profiledataservice.model.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 
 * The Class CategoryServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional
	public Category save(Category category) {
		Date actualDate = new Date();
		category.setCreateDate(actualDate);
		category.setUpdateDate(actualDate);
		Category _Category = categoryRepository.save(category);
		if (_Category != null && _Category.getCategoryId() != null) {
			return _Category;
		}
		return null;
	}

	@Override
	@Transactional
	public Category update(Category category) {
		Optional<Category> categoryData = categoryRepository.findById(category.getCategoryId());
		if (categoryData != null && categoryData.isPresent() && categoryData.get() != null) {
			Category _category = categoryData.get();
			Date actualDate = new Date();
			_category.setUpdateDate(actualDate);
			Category _Category = categoryRepository.save(_category);
			if (_Category != null && _Category.getCategoryId() != null) {
				return _Category;
			}
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Category findByCategoryId(String categoryId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		if (categoryData.isPresent()) {
			return categoryData.get();
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Category findByCategoryName(String categoryName) {
		Optional<Category> categoryData = categoryRepository.findByCategoryName(categoryName);
		if (categoryData.isPresent()) {
			return categoryData.get();
		}
		return null;
	}

	@Override
	@Transactional
	public void delete(String categoryId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		if (categoryData != null && categoryData.isPresent() && categoryData.get() != null) {
			categoryRepository.delete(categoryData.get());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		categoryRepository.findAll().forEach(categories::add);
		return categories;
	}

}
