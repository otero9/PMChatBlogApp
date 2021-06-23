package com.udc.muei.tfm.profiledataservice.model.blog;

import java.util.List;

import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;

/*
 * 
 * The Interface BlogService.
 * 
 * @author a.oteroc
 * 
 */
public interface BlogService {

	/**
	 * saveOrUpdateBlog
	 * 
	 * @param blogDAO
	 * @return Blog
	 */
	Blog saveOrUpdateBlog(Blog blogDAO) throws SaveNotAvailableException;

	/**
	 * deleteBlog
	 * 
	 * @param blogId
	 * @return Boolean
	 */
	Boolean deleteBlog(String blogId) throws DeleteNotAvailableException;

	/**
	 * findByBlogId
	 * 
	 * @return Blog
	 */
	Blog findByBlogId(String blogId);

	/**
	 * getAllBlogs
	 * 
	 * @return List<Blog>
	 */
	List<Blog> getAllBlogs();

	/**
	 * findByCategoryId
	 * 
	 * @param categoryId
	 * @return List<Blog>
	 */
	List<Blog> findByCategoryId(String categoryId);

	/**
	 * findByUserId
	 * 
	 * @param userId
	 * @return List<Blog>
	 */
	List<Blog> findByUserId(String userId);

	/**
	 * findByCategoryIdAndUserId
	 * 
	 * @param categoryId
	 * @param userId
	 * @return List<Blog>
	 */
	List<Blog> findByCategoryIdAndUserId(String categoryId, String userId);

	/**
	 * getBlogRates
	 * 
	 * @param blog
	 * @return int
	 */
	int getBlogRates(Blog blog);

	/**
	 * newBlogRate
	 * 
	 * @param userId
	 * @param blogId
	 * @param value
	 * @return BlogRate
	 */
	BlogRate newBlogRate(String userId, String blogId, int value);

	/**
	 * findBlogRateByUserIdAndBlogId
	 * 
	 * @param userId
	 * @param blogId
	 * @return BlogRate
	 */
	BlogRate findBlogRateByUserIdAndBlogId(String userId, String blogId);
}
