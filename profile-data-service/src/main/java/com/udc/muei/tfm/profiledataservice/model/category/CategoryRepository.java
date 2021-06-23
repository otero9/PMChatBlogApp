package com.udc.muei.tfm.profiledataservice.model.category;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * 
 * The Interface CategoryRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface CategoryRepository extends MongoRepository<Category, String> {

	/**
	 * findByCategoryName
	 * 
	 * @param categoryName
	 * @return List<Category>
	 */
	Optional<Category> findByCategoryName(String categoryName);

}