package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface TemplateRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface TemplateRepository extends MongoRepository<Template, String> {

	/**
	 * findByCategory
	 * 
	 * @param category
	 * @return List<Template>
	 */
	List<Template> findByCategory(Category category);

	/**
	 * findByUser
	 * 
	 * @param user
	 * @return List<Template>
	 */
	List<Template> findByUser(User user);

	/**
	 * findByCategoryAndUser
	 * 
	 * @param category
	 * @param user
	 * @return List<Template>
	 */
	List<Template> findByCategoryAndUser(Category category, User user);

}
