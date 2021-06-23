package com.udc.muei.tfm.profiledataservice.controller.comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.udc.muei.tfm.profiledataservice.model.blog.Blog;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogRepository;
import com.udc.muei.tfm.profiledataservice.model.comment.Comment;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRepository;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class CommentController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/commentAPI")
@Api(tags = "Comment Controller", description = "This API has a CRUD for Comments")
public class CommentController {

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BlogRepository blogRepository;

	@ApiOperation(value = "Search all Comments or search Comments by UserID or/and BlogID")
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDTO>> getComments(
			@RequestParam(required = false) @PathVariable("userId") String userId,
			@RequestParam(required = false) @PathVariable("blogId") String blogId) {
		try {
			List<Comment> comments = new ArrayList<Comment>();
			User user = null;
			Blog blog = null;
			if (userId != null && !userId.isEmpty()) {
				Optional<User> userData = userRepository.findById(userId);
				if (userData.isPresent()) {
					user = userData.get();
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			if (blogId != null && !blogId.isEmpty()) {
				Optional<Blog> blogData = blogRepository.findById(blogId);
				if (blogData.isPresent()) {
					blog = blogData.get();
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			if (user != null) {
				if (blog != null) {
					commentRepository.findByUserAndBlog(user, blog).forEach(comments::add);
				} else {
					commentRepository.findByUser(user).forEach(comments::add);
				}
			} else {
				if (blog != null) {
					commentRepository.findByBlog(blog).forEach(comments::add);
				} else {
					commentRepository.findAll().forEach(comments::add);
				}
			}
			List<CommentDTO> commentsDto = new ArrayList<CommentDTO>();
			if (comments != null) {
				for (Comment comment : comments) {
					CommentDTO commentDto = new CommentDTO();
					commentDto.setCommentId(comment.getCommentId());
					if (comment.getUser() != null) {
						commentDto.setUserName(comment.getUser().getUserName());
					}
					commentDto.setCreatedDate(comment.getCreatedDate());
					commentDto.setCommentValue(comment.getValue());
					commentsDto.add(commentDto);
				}
			}
			return new ResponseEntity<>(commentsDto, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search Comment by ID")
	@GetMapping("/getCommentById")
	public ResponseEntity<CommentDTO> getCommentById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Optional<Comment> commentData = commentRepository.findById(id);

			if (commentData.isPresent() && commentData.get() != null) {
				CommentDTO commentDto = new CommentDTO();
				commentDto.setCommentId(commentData.get().getCommentId());
				commentDto.setCommentValue(commentData.get().getValue());
				commentDto.setCreatedDate(commentData.get().getCreatedDate());
				if (commentData.get().getUser() != null) {
					commentDto.setUserName(commentData.get().getUser().getUserName());
				}
				return new ResponseEntity<>(commentDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Comment")
	@PostMapping("/comment")
	public ResponseEntity<CommentDTO> createComment(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("blogId") String blogId, @RequestBody CommentDTO comment) {
		try {
			if (userId != null && !userId.isEmpty() && blogId != null && !blogId.isEmpty()) {
				User user = null;
				System.out.println("Entra1");
				Optional<User> userData = userRepository.findById(userId);
				if (userData.isPresent()) {
					user = userData.get();
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				System.out.println("Entra2");
				Blog blog = null;
				Optional<Blog> blogData = blogRepository.findById(blogId);
				if (blogData.isPresent()) {
					blog = blogData.get();
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				System.out.println("Entra3");
				System.out.println(user != null);
				System.out.println(blog != null);
				System.out.println(comment.getCommentValue() != null);
				System.out.println(comment.getCommentValue().isEmpty());
				if (user != null && blog != null && comment.getCommentValue() != null
						&& !comment.getCommentValue().isEmpty()) {
					long actualPoints = user.getPoints();
					user.setPoints(actualPoints + 1);
					userRepository.save(user);
					Comment commentData = new Comment();
					commentData.setUser(user);
					commentData.setBlog(blog);
					Date actualDate = new Date();
					commentData.setCreatedDate(actualDate);
					commentData.setUpdateDate(actualDate);
					commentData.setValue(comment.getCommentValue());
					Comment createdComment = commentRepository.save(commentData);
					System.out.println("Entra4");
					if (createdComment != null && createdComment.getCommentId() != null) {
						CommentDTO commentDto = new CommentDTO();
						System.out.println("Entra5");
						commentDto.setCommentId(createdComment.getCommentId());
						return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
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

	@ApiOperation(value = "Update Comment by ID")
	@PutMapping("/updateComment")
	public ResponseEntity<CommentDTO> updateComment(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestBody Comment comment) {
		try {
			if (id == null || id.isEmpty() || comment == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Optional<Comment> commentData = commentRepository.findById(id);

			if (commentData.isPresent()) {
				Comment _Comment = commentData.get();
				_Comment.setUser(comment.getUser());
				_Comment.setBlog(comment.getBlog());
				_Comment.setValue(comment.getValue());
				_Comment.setCreatedDate(comment.getCreatedDate());
				_Comment.setUpdateDate(comment.getUpdateDate());
				Comment updatedComment = commentRepository.save(_Comment);
				if (updatedComment != null && updatedComment.getCommentId() != null) {
					CommentDTO commentDto = new CommentDTO();
					commentDto.setCommentId(updatedComment.getCommentId());
					return new ResponseEntity<>(commentDto, HttpStatus.OK);
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

	@ApiOperation(value = "Delete Comment by ID")
	@DeleteMapping("/comment")
	public ResponseEntity<HttpStatus> deleteComment(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			commentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}