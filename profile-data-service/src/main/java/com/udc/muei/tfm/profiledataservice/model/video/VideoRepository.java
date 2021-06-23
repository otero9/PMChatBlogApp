package com.udc.muei.tfm.profiledataservice.model.video;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.user.User;

public interface VideoRepository extends MongoRepository<Video, String> {

	/**
	 * findByCategory
	 * 
	 * @param category
	 * @return List<Video>
	 */
	List<Video> findByCategory(Category category);

	/**
	 * findByUser
	 * 
	 * @param user
	 * @return List<Video>
	 */
	List<Video> findByUser(User user);

	/**
	 * findByCategoryAndUser
	 * 
	 * @param category
	 * @param user
	 * @return List<Video>
	 */
	List<Video> findByCategoryAndUser(Category category, User user);

}
