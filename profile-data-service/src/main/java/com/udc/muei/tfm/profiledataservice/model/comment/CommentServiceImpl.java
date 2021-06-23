package com.udc.muei.tfm.profiledataservice.model.comment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.udc.muei.tfm.profiledataservice.model.blog.Blog;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogRepository;
import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserRepository;

/*
 * 
 * The Class CommentServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommentServiceImpl implements CommentService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CommentRateRepository commentRateRepository;

	@Override
	public Comment saveOrUpdateComment(Comment commentDAO) throws SaveNotAvailableException {
		Comment createdComment = commentRepository.save(commentDAO);
		return createdComment;
	}

	@Override
	public Boolean deleteComment(String commentId) throws DeleteNotAvailableException {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment != null && comment.isPresent() && comment.get() != null) {

			List<CommentRate> valorations = commentRateRepository.findByComment(comment.get());
			if (valorations != null && !valorations.isEmpty()) {
				for (CommentRate valoration : valorations) {
					commentRateRepository.deleteById(valoration.getCommentRateId());
				}
			}
			commentRepository.deleteById(commentId);
			return true;
		}
		return false;
	}

	@Override
	public Comment findById(String commentId) {
		Optional<Comment> commentData = commentRepository.findById(commentId);
		if (commentData != null && commentData.isPresent() && commentData.get() != null) {
			return commentData.get();
		}
		return null;
	}

	@Override
	public List<Comment> findByBlogId(String blogId) {
		Optional<Blog> blogData = blogRepository.findById(blogId);
		Blog blog = null;
		if (blogData != null && blogData.isPresent()) {
			blog = blogData.get();
			return commentRepository.findByBlog(blog);
		}
		return null;
	}

	@Override
	public List<Comment> findByUser(String userId) {
		Optional<User> userData = userRepository.findById(userId);
		User user = null;
		if (userData != null && userData.isPresent()) {
			user = userData.get();
			return commentRepository.findByUser(user);
		}
		return null;
	}

	@Override
	public List<Comment> findByUserAndBlog(String userId, String blogId) {
		Optional<Blog> blogData = blogRepository.findById(blogId);
		Optional<User> userData = userRepository.findById(userId);
		Blog blog = null;
		User user = null;
		if (blogData != null && blogData.isPresent() & userData != null && userData.isPresent()) {
			user = userData.get();
			blog = blogData.get();
			return commentRepository.findByUserAndBlog(user, blog);
		}
		return null;
	}

	@Override
	public int getCommentRates(Comment comment) {
		List<CommentRate> valorations = commentRateRepository.findByComment(comment);
		if (valorations != null) {
			int points = 0;
			for (CommentRate valoration : valorations) {
				points += valoration.getValue();
			}
			if (points < 0) {
				return 0;
			} else {
				return points;
			}
		}
		return 0;
	}

	@Override
	public CommentRate newCommentRate(String userId, String commentId, int value) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			user = userData.get();
		} else {
			return null;
		}
		Comment comment = null;
		Optional<Comment> commentData = commentRepository.findById(commentId);
		if (commentData.isPresent()) {
			comment = commentData.get();
		} else {
			return null;
		}
		if (user != null && comment != null) {
			long actualPoints = user.getPoints();
			user.setPoints(actualPoints + value);
			userRepository.save(user);
			CommentRate valorationData = new CommentRate();
			valorationData.setUser(user);
			valorationData.setComment(comment);
			Date actualDate = new Date();
			valorationData.setCreatedDate(actualDate);
			valorationData.setUpdateDate(actualDate);
			valorationData.setValue(value);
			CommentRate createdRate = commentRateRepository.save(valorationData);
			if (createdRate != null && createdRate.getCommentRateId() != null) {
				return createdRate;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public CommentRate findCommentRateByUserIdAndCommentId(String userId, String commentId) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			user = userData.get();
		} else {
			return null;
		}
		Comment comment = null;
		Optional<Comment> commentData = commentRepository.findById(commentId);
		if (commentData != null && commentData.isPresent() && commentData.get() != null) {
			comment = commentData.get();
		} else {
			return null;
		}
		CommentRate commentRate = commentRateRepository.findByUserAndComment(user, comment);
		return commentRate;
	}

}