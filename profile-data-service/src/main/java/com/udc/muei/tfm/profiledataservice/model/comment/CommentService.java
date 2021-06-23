package com.udc.muei.tfm.profiledataservice.model.comment;

import java.util.List;

import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;

/*
 * 
 * The Interface CommentService.
 * 
 * @author a.oteroc
 * 
 */
public interface CommentService {

	/**
	 * saveOrUpdateTemplate
	 * 
	 * @param commentDAO
	 * @return Comment
	 */
	Comment saveOrUpdateComment(Comment commentDAO) throws SaveNotAvailableException;

	/**
	 * deleteComment
	 * 
	 * @param commentId
	 * @return Boolean
	 */
	Boolean deleteComment(String commentId) throws DeleteNotAvailableException;

	/**
	 * findById
	 * 
	 * @param commentId
	 * @return Comment
	 */
	Comment findById(String commentId);

	/**
	 * findByBlogId
	 * 
	 * @param blogId
	 * @return List<Comment>
	 */
	List<Comment> findByBlogId(String blogId);

	/**
	 * findByUser
	 * 
	 * @param userId
	 * @return List<Comment>
	 */
	List<Comment> findByUser(String userId);

	/**
	 * findByUserAndBlog
	 * 
	 * @param userId
	 * @param blogId
	 * @return List<Comment>
	 */
	List<Comment> findByUserAndBlog(String userId, String blogId);

	/**
	 * getCommentRates
	 * 
	 * @param Comment
	 * @return int
	 */
	int getCommentRates(Comment comment);

	/**
	 * newCommentRate
	 * 
	 * @param userId
	 * @param commentId
	 * @param value
	 * @return CommentRate
	 */
	CommentRate newCommentRate(String userId, String commentId, int value);

	/**
	 * findCommentRateByUserIdAndCommentId
	 * 
	 * @param userId
	 * @param commentId
	 * @return CommentRate
	 */
	CommentRate findCommentRateByUserIdAndCommentId(String userId, String commentId);
}