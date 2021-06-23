package com.udc.muei.tfm.profiledataservice.model.blog;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface BlogRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface BlogRepository extends MongoRepository<Blog, String> {

	/**
	 * findByCategory
	 * 
	 * @param category
	 * @return List<Blog>
	 */
	List<Blog> findByCategory(Category category);

	/**
	 * findByUser
	 * 
	 * @param user
	 * @return List<Blog>
	 */
	List<Blog> findByUser(User user);

	/**
	 * findByCategoryAndUser
	 * 
	 * @param category
	 * @param user
	 * @return List<Blog>
	 */
	List<Blog> findByCategoryAndUser(Category category, User user);

}