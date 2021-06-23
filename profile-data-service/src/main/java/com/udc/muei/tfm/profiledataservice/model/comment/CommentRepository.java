package com.udc.muei.tfm.profiledataservice.model.comment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udc.muei.tfm.profiledataservice.model.blog.Blog;
import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Interface CommentRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

	/**
	 * findByUser
	 * 
	 * @param user
	 * @return List<CommentDAO>
	 */
	List<Comment> findByUser(User user);

	/**
	 * findByBlog
	 * 
	 * @param blog
	 * @return List<CommentDAO>
	 */
	List<Comment> findByBlog(Blog blog);

	/**
	 * findByUserAndBlog
	 * 
	 * @param user
	 * @param blog
	 * @return List<CommentDAO>
	 */
	List<Comment> findByUserAndBlog(User user, Blog blog);

}