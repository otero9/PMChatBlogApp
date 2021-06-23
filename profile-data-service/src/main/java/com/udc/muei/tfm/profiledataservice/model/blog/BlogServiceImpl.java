package com.udc.muei.tfm.profiledataservice.model.blog;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryRepository;
import com.udc.muei.tfm.profiledataservice.model.comment.Comment;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRate;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRateRepository;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRepository;
import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserRepository;

/*
 * 
 * The Class BlogServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CommentRateRepository commentRateRepository;

	@Autowired
	BlogRateRepository blogRateRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Blog saveOrUpdateBlog(Blog blogDAO) throws SaveNotAvailableException {
		Blog createdBlog = blogRepository.save(blogDAO);
		return createdBlog;
	}

	@Override
	public Boolean deleteBlog(String blogId) throws DeleteNotAvailableException {
		Optional<Blog> blog = blogRepository.findById(blogId);
		if (blog != null && blog.isPresent() && blog.get() != null) {
			List<Comment> comments = commentRepository.findByBlog(blog.get());
			if (comments != null && !comments.isEmpty()) {
				for (Comment comment : comments) {
					List<CommentRate> valorations = commentRateRepository.findByComment(comment);
					if (valorations != null && !valorations.isEmpty()) {
						for (CommentRate valoration : valorations) {
							commentRateRepository.deleteById(valoration.getCommentRateId());
						}
					}
					commentRepository.deleteById(comment.getCommentId());
				}
			}
			List<BlogRate> valorations = blogRateRepository.findByBlog(blog.get());
			if (valorations != null && !valorations.isEmpty()) {
				for (BlogRate valoration : valorations) {
					blogRateRepository.deleteById(valoration.getBlogRateId());
				}
			}
			blogRepository.deleteById(blogId);
			return true;
		}
		return false;
	}

	@Override
	public Blog findByBlogId(String blogId) {
		Optional<Blog> blogData = blogRepository.findById(blogId);
		if (blogData != null && blogData.isPresent() && blogData.get() != null) {
			return blogData.get();
		}
		return null;
	}

	@Override
	public List<Blog> getAllBlogs() {
		return blogRepository.findAll();
	}

	@Override
	public List<Blog> findByCategoryId(String categoryId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		Category category = null;
		if (categoryData != null && categoryData.isPresent()) {
			category = categoryData.get();
		}
		return blogRepository.findByCategory(category);
	}

	@Override
	public List<Blog> findByUserId(String userId) {
		Optional<User> userData = userRepository.findById(userId);
		User user = null;
		if (userData != null && userData.isPresent()) {
			user = userData.get();
		}
		return blogRepository.findByUser(user);
	}

	@Override
	public List<Blog> findByCategoryIdAndUserId(String categoryId, String userId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		Optional<User> userData = userRepository.findById(userId);
		Category category = null;
		User user = null;
		if (categoryData != null && categoryData.isPresent() & userData != null && userData.isPresent()) {
			user = userData.get();
			category = categoryData.get();
			return blogRepository.findByCategoryAndUser(category, user);
		}
		return null;
	}

	@Override
	public int getBlogRates(Blog blog) {
		List<BlogRate> valorations = blogRateRepository.findByBlog(blog);
		if (valorations != null) {
			int points = 0;
			for (BlogRate valoration : valorations) {
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
	public BlogRate newBlogRate(String userId, String blogId, int value) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			user = userData.get();
		} else {
			return null;
		}
		Blog blog = null;
		Optional<Blog> blogData = blogRepository.findById(blogId);
		if (blogData.isPresent()) {
			blog = blogData.get();
		} else {
			return null;
		}
		if (user != null && blog != null) {
			long actualPoints = user.getPoints();
			user.setPoints(actualPoints + value);
			userRepository.save(user);
			BlogRate valorationData = new BlogRate();
			valorationData.setUser(user);
			valorationData.setBlog(blog);
			Date actualDate = new Date();
			valorationData.setCreatedDate(actualDate);
			valorationData.setUpdateDate(actualDate);
			valorationData.setValue(value);
			BlogRate createdRate = blogRateRepository.save(valorationData);
			if (createdRate != null && createdRate.getBlogRateId() != null) {
				return createdRate;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public BlogRate findBlogRateByUserIdAndBlogId(String userId, String blogId) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			user = userData.get();
		} else {
			return null;
		}
		Blog blog = null;
		Optional<Blog> blogData = blogRepository.findById(blogId);
		if (blogData != null && blogData.isPresent() && blogData.get() != null) {
			blog = blogData.get();
		} else {
			return null;
		}
		BlogRate blogRate = blogRateRepository.findByUserAndBlog(user, blog);
		return blogRate;
	}

}
