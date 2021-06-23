package com.udc.muei.tfm.profiledataservice.controller.blog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udc.muei.tfm.profiledataservice.controller.comment.CommentDTO;
import com.udc.muei.tfm.profiledataservice.model.blog.Blog;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogService;
import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryService;
import com.udc.muei.tfm.profiledataservice.model.comment.Comment;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentService;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserService;
import com.udc.muei.tfm.profiledataservice.utils.BlogPointsSorter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class BlogController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/blogAPI")
@Api(tags = "Blog Controller", description = "This API has a CRUD for Blogs")
public class BlogController {

	@Autowired
	BlogService blogService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	CommentService commentService;

	@ApiOperation(value = "Search all Blogs or search Blogs by UserID or/and CategoryID")
	@GetMapping("/blogs")
	public ResponseEntity<List<BlogDTO>> getBlogs(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String categoryId) {
		try {
			List<Blog> blogs = null;
			if (userId != null && !userId.isEmpty()) {
				if (categoryId != null && !categoryId.isEmpty()) {
					blogs = blogService.findByCategoryIdAndUserId(categoryId, userId);
				} else {
					blogs = blogService.findByUserId(userId);
				}

			} else {
				if (categoryId != null && !categoryId.isEmpty()) {
					blogs = blogService.findByCategoryId(categoryId);
				} else {
					blogs = blogService.getAllBlogs();
				}
			}
			if (blogs != null && !blogs.isEmpty()) {
				List<BlogDTO> blogsDto = new ArrayList<>();

				for (Blog blog : blogs) {
					BlogDTO blogDto = new BlogDTO();
					blogDto.setBlogId(blog.getBlogId());
					blogDto.setCategoryId(blog.getCategory().getCategoryId());
					blogDto.setCategoryName(blog.getCategory().getCategoryName());
					blogDto.setUserId(blog.getUser().getUserId());
					blogDto.setUserName(blog.getUser().getUserName());
					blogDto.setTitle(blog.getTitle());
					blogDto.setCreatedDate(blog.getCreatedDate());
					blogDto.setUpdateDate(blog.getUpdateDate());
					List<Comment> comments = commentService.findByBlogId(blog.getBlogId());
					if (comments != null && !comments.isEmpty()) {
						blogDto.setCountComments(comments.size());
					} else {
						blogDto.setCountComments(0);
					}
					blogDto.setPoints(blogService.getBlogRates(blog));
					blogsDto.add(blogDto);
				}
				return new ResponseEntity<>(blogsDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search best Blogs")
	@GetMapping("/bestBlogs")
	public ResponseEntity<List<BlogDTO>> getBestBlogs(@RequestParam(required = false) String categoryId) {
		try {
			List<Blog> blogs = null;
			if (categoryId != null && !categoryId.isEmpty()) {
				blogs = blogService.findByCategoryId(categoryId);
			} else {
				blogs = blogService.getAllBlogs();
			}
			if (blogs != null && !blogs.isEmpty()) {
				List<BlogDTO> blogsDto = new ArrayList<>();

				for (Blog blog : blogs) {
					BlogDTO blogDto = new BlogDTO();
					blogDto.setBlogId(blog.getBlogId());
					blogDto.setCategoryId(blog.getCategory().getCategoryId());
					blogDto.setCategoryName(blog.getCategory().getCategoryName());
					blogDto.setUserId(blog.getUser().getUserId());
					blogDto.setUserName(blog.getUser().getUserName());
					blogDto.setTitle(blog.getTitle());
					blogDto.setCreatedDate(blog.getCreatedDate());
					blogDto.setUpdateDate(blog.getUpdateDate());
					List<Comment> comments = commentService.findByBlogId(blog.getBlogId());
					if (comments != null && !comments.isEmpty()) {
						blogDto.setCountComments(comments.size());
					} else {
						blogDto.setCountComments(0);
					}
					blogDto.setPoints(blogService.getBlogRates(blog));
					blogsDto.add(blogDto);
				}
				blogsDto.sort(new BlogPointsSorter());
				if (blogsDto.size() > 0 && blogsDto.size() > 10) {
					return new ResponseEntity<>(blogsDto.subList(0, 9), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(blogsDto, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search Blog by ID")
	@GetMapping("/getBlogById")
	public ResponseEntity<BlogDTO> getBlogById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Blog blog = blogService.findByBlogId(id);
			if (blog != null) {
				BlogDTO blogDto = new BlogDTO();
				blogDto.setBlogId(blog.getBlogId());
				blogDto.setCategoryId(blog.getCategory().getCategoryId());
				blogDto.setCategoryName(blog.getCategory().getCategoryName());
				blogDto.setUserId(blog.getUser().getUserId());
				blogDto.setUserName(blog.getUser().getUserName());
				blogDto.setTitle(blog.getTitle());
				blogDto.setHeader(blog.getHeader());
				blogDto.setBody(blog.getBody());
				blogDto.setFooter(blog.getFooter());
				blogDto.setCreatedDate(blog.getCreatedDate());
				blogDto.setUpdateDate(blog.getUpdateDate());
				blogDto.setPoints(blogService.getBlogRates(blog));
				List<Comment> comments = commentService.findByBlogId(blog.getBlogId());
				List<CommentDTO> commentsDto = new ArrayList<CommentDTO>();
				if (comments != null && comments.size() > 0) {
					for (Comment comment : comments) {
						CommentDTO commentDto = new CommentDTO();
						commentDto.setCommentId(comment.getCommentId());
						commentDto.setCommentValue(comment.getValue());
						commentDto.setUserName(comment.getUser().getUserName());
						commentDto.setCreatedDate(comment.getCreatedDate());
						commentDto.setPoints(commentService.getCommentRates(comment));
						commentsDto.add(commentDto);
					}
					blogDto.setCountComments(comments.size());
				}
				blogDto.setComments(commentsDto);

				return new ResponseEntity<>(blogDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Blog")
	@PostMapping("/blog")
	public ResponseEntity<BlogDTO> createBlog(
			@RequestParam(required = true) @PathVariable("categoryId") String categoryId,
			@RequestParam(required = true) @PathVariable("userId") String userId, @RequestBody BlogDTO blog) {
		try {

			if (categoryId != null && !categoryId.isEmpty() && userId != null && !userId.isEmpty()) {
				User user = null;
				user = userService.findByUserIdDAO(userId);
				if (user == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				Category category = categoryService.findByCategoryId(categoryId);
				if (category == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				if (user != null && category != null && blog.getTitle() != null && !blog.getTitle().isEmpty()
						&& blog.getHeader() != null && !blog.getHeader().isEmpty() && blog.getBody() != null
						&& !blog.getBody().isEmpty() && blog.getFooter() != null && !blog.getFooter().isEmpty()) {
					Blog blogData = new Blog();
					blogData.setUser(user);
					blogData.setCategory(category);
					Date actualDate = new Date();
					blogData.setCreatedDate(actualDate);
					blogData.setUpdateDate(actualDate);
					blogData.setTitle(blog.getTitle());
					blogData.setHeader(blog.getHeader());
					blogData.setBody(blog.getBody());
					blogData.setFooter(blog.getFooter());
					Blog createdBlog = blogService.saveOrUpdateBlog(blogData);
					long actualPoints = user.getPoints();
					user.setPoints(actualPoints + 1);
					userService.saveUserDAO(user);
					if (createdBlog != null && createdBlog.getBlogId() != null) {
						BlogDTO blogDto = new BlogDTO();
						blogDto.setBlogId(createdBlog.getBlogId());
						return new ResponseEntity<>(blogDto, HttpStatus.CREATED);
					} else {
						return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update Blog by ID")
	@PutMapping("/updateBlog")
	public ResponseEntity<BlogDTO> updateBlog(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestBody BlogDTO blog) {
		try {
			if (id == null || id.isEmpty() || blog == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Blog _Blog = blogService.findByBlogId(id);
			if (_Blog != null) {
				Category category = categoryService.findByCategoryId(id);
				if (category != null) {
					_Blog.setCategory(category);
				}
				_Blog.setTitle(blog.getTitle());
				_Blog.setHeader(blog.getHeader());
				_Blog.setBody(blog.getBody());
				_Blog.setFooter(blog.getFooter());
				_Blog.setUpdateDate(new Date());
				Blog updatedBlog = blogService.saveOrUpdateBlog(_Blog);
				if (updatedBlog != null && updatedBlog.getBlogId() != null) {
					BlogDTO blogDto = new BlogDTO();
					blogDto.setBlogId(updatedBlog.getBlogId());
					return new ResponseEntity<>(blogDto, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Delete Blog by ID")
	@DeleteMapping("/blog")
	public ResponseEntity<?> deleteBlog(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: invalid blogId!");
			}
			Boolean deleted = blogService.deleteBlog(id);
			if (deleted) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Blog not found!");
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}