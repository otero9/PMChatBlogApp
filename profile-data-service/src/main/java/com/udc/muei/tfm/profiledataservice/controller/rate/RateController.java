package com.udc.muei.tfm.profiledataservice.controller.rate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.udc.muei.tfm.profiledataservice.model.blog.BlogRate;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogRateRepository;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogRepository;
import com.udc.muei.tfm.profiledataservice.model.blog.BlogService;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRate;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentService;
import com.udc.muei.tfm.profiledataservice.model.template.TemplateRate;
import com.udc.muei.tfm.profiledataservice.model.template.TemplateService;
import com.udc.muei.tfm.profiledataservice.model.user.UserRate;
import com.udc.muei.tfm.profiledataservice.model.user.UserRepository;
import com.udc.muei.tfm.profiledataservice.model.user.UserService;
import com.udc.muei.tfm.profiledataservice.model.video.VideoRate;
import com.udc.muei.tfm.profiledataservice.model.video.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class RateController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/rateAPI")
@Api(tags = "Rate Controller", description = "This API has a CRUD for Rates")
public class RateController {

	@Autowired
	BlogRateRepository valorationRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BlogRepository blogRepository;

	@Autowired
	UserService userService;

	@Autowired
	BlogService blogService;

	@Autowired
	CommentService commentService;

	@Autowired
	TemplateService templateService;

	@Autowired
	VideoService videoService;

	@ApiOperation(value = "Create new User Rate")
	@PostMapping("/rateUser")
	public ResponseEntity<UserRateDTO> newUserRate(@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("userRatedId") String userRatedId,
			@RequestBody UserRateDTO valoration) {
		try {
			if (userId != null && !userId.isEmpty() && userRatedId != null && !userRatedId.isEmpty()
					&& valoration != null && (valoration.getValue() == 1 || valoration.getValue() == -1)) {

				UserRate userRate = userService.newUserRate(userId, userRatedId, valoration.getValue());

				if (userRate != null) {
					UserRateDTO valorationDTO = new UserRateDTO();
					valorationDTO.setUserId(userRatedId);
					valorationDTO.setUserRatedId(userRate.getUserRateId());
					valorationDTO.setValue(valoration.getValue());
					valorationDTO.setUserName(valoration.getUserName());
					return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
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

	@ApiOperation(value = "Search User Rate to Other User")
	@GetMapping("/getUserRateUser")
	public ResponseEntity<UserRateDTO> getUserRateUser(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("userRatedId") String userRatedId) {
		try {
			if (userId != null && !userId.isEmpty() && userRatedId != null && !userRatedId.isEmpty()) {
				UserRate userRate = userService.findUserRateByUserIdAndUserRatedId(userId, userRatedId);
				UserRateDTO valorationDTO = new UserRateDTO();
				valorationDTO.setUserRatedId(userRatedId);
				if (userRate != null && userRate.getUserRateId() != null) {
					valorationDTO.setUserRatedId(userRate.getUserRateId());
					valorationDTO.setValue(userRate.getValue());
				} else {
					valorationDTO.setValue(0);
				}
				return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Blog Rate")
	@PostMapping("/rateBlog")
	public ResponseEntity<BlogRateDTO> newBlogRate(@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("blogId") String blogId, @RequestBody BlogRateDTO valoration) {
		try {
			if (userId != null && !userId.isEmpty() && blogId != null && !blogId.isEmpty() && valoration != null
					&& (valoration.getValue() == 1 || valoration.getValue() == -1)) {

				BlogRate blogRate = blogService.newBlogRate(userId, blogId, valoration.getValue());

				if (blogRate != null) {
					BlogRateDTO valorationDTO = new BlogRateDTO();
					valorationDTO.setBlogId(blogId);
					valorationDTO.setBlogRateId(blogRate.getBlogRateId());
					valorationDTO.setValue(valoration.getValue());
					valorationDTO.setUserName(valoration.getUserName());
					return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
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

	@ApiOperation(value = "Search User Blog Rate")
	@GetMapping("/getUserBlogRate")
	public ResponseEntity<BlogRateDTO> getUserBlogRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("blogId") String blogId) {
		try {
			if (userId != null && !userId.isEmpty() && blogId != null && !blogId.isEmpty()) {
				BlogRate blogRate = blogService.findBlogRateByUserIdAndBlogId(userId, blogId);
				BlogRateDTO valorationDTO = new BlogRateDTO();
				valorationDTO.setBlogId(blogId);
				if (blogRate != null && blogRate.getBlogRateId() != null) {
					valorationDTO.setBlogRateId(blogRate.getBlogRateId());
					valorationDTO.setValue(blogRate.getValue());
				} else {
					valorationDTO.setValue(0);
				}
				return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Template Rate")
	@PostMapping("/rateTemplate")
	public ResponseEntity<TemplateRateDTO> newVTemplateRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("templateId") String templateId,
			@RequestBody TemplateRateDTO valoration) {
		try {
			if (userId != null && !userId.isEmpty() && templateId != null && !templateId.isEmpty() && valoration != null
					&& (valoration.getValue() == 1 || valoration.getValue() == -1)) {

				TemplateRate templateRate = templateService.newTemplateRate(userId, templateId, valoration.getValue());

				if (templateRate != null) {
					TemplateRateDTO valorationDTO = new TemplateRateDTO();
					valorationDTO.setTemplateId(templateId);
					valorationDTO.setTemplateRateId(templateRate.getTemplateRateId());
					valorationDTO.setValue(valoration.getValue());
					valorationDTO.setUserName(valoration.getUserName());
					return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
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

	@ApiOperation(value = "Search User Template Rate")
	@GetMapping("/getUserTemplateRate")
	public ResponseEntity<TemplateRateDTO> getUserTemplateRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("templateId") String templateId) {
		try {
			if (userId != null && !userId.isEmpty() && templateId != null && !templateId.isEmpty()) {
				TemplateRate templateRate = templateService.findTemplateRateByUserIdAndTemplateId(userId, templateId);
				TemplateRateDTO valorationDTO = new TemplateRateDTO();
				valorationDTO.setTemplateId(templateId);
				if (templateRate != null && templateRate.getTemplateRateId() != null) {
					valorationDTO.setTemplateRateId(templateRate.getTemplateRateId());
					valorationDTO.setValue(templateRate.getValue());
				} else {
					valorationDTO.setValue(0);
				}
				return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Video Rate")
	@PostMapping("/rateVideo")
	public ResponseEntity<VideoRateDTO> newVideoRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("videoId") String videoId,
			@RequestBody VideoRateDTO valoration) {
		try {
			if (userId != null && !userId.isEmpty() && videoId != null && !videoId.isEmpty() && valoration != null
					&& (valoration.getValue() == 1 || valoration.getValue() == -1)) {
				VideoRate videoRate = videoService.newVideoRate(userId, videoId, valoration.getValue());
				if (videoRate != null) {
					VideoRateDTO valorationDTO = new VideoRateDTO();
					valorationDTO.setVideoId(videoId);
					valorationDTO.setVideoRateId(videoRate.getVideoRateId());
					valorationDTO.setValue(valoration.getValue());
					valorationDTO.setUserName(valoration.getUserName());
					return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
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

	@ApiOperation(value = "Search User Video Rate")
	@GetMapping("/getUserVideoRate")
	public ResponseEntity<VideoRateDTO> getUserVideoRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("videoId") String videoId) {
		try {
			if (userId != null && !userId.isEmpty() && videoId != null && !videoId.isEmpty()) {
				VideoRate videoRate = videoService.findVideoRateByUserIdAndVideoId(userId, videoId);
				VideoRateDTO valorationDTO = new VideoRateDTO();
				valorationDTO.setVideoId(videoId);
				if (videoRate != null && videoRate.getVideoRateId() != null) {
					valorationDTO.setVideoRateId(videoRate.getVideoRateId());
					valorationDTO.setValue(videoRate.getValue());
				} else {
					valorationDTO.setValue(0);
				}
				return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Comment Rate")
	@PostMapping("/rateComment")
	public ResponseEntity<CommentRateDTO> newCommentRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("commentId") String commentId,
			@RequestBody CommentRateDTO valoration) {
		try {
			if (userId != null && !userId.isEmpty() && commentId != null && !commentId.isEmpty() && valoration != null
					&& (valoration.getValue() == 1 || valoration.getValue() == -1)) {

				CommentRate commentRate = commentService.newCommentRate(userId, commentId, valoration.getValue());

				if (commentRate != null) {
					CommentRateDTO valorationDTO = new CommentRateDTO();
					valorationDTO.setCommentId(commentId);
					valorationDTO.setCommentRateId(commentRate.getCommentRateId());
					valorationDTO.setValue(valoration.getValue());
					valorationDTO.setUserName(valoration.getUserName());
					return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
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

	@ApiOperation(value = "Search User Comment Rate")
	@GetMapping("/getUserCommentRate")
	public ResponseEntity<CommentRateDTO> getUserCommentRate(
			@RequestParam(required = true) @PathVariable("userId") String userId,
			@RequestParam(required = true) @PathVariable("commentId") String commentId) {
		try {
			if (userId != null && !userId.isEmpty() && commentId != null && !commentId.isEmpty()) {
				CommentRate commentRate = commentService.findCommentRateByUserIdAndCommentId(userId, commentId);
				CommentRateDTO valorationDTO = new CommentRateDTO();
				valorationDTO.setCommentId(commentId);
				if (commentRate != null && commentRate.getCommentRateId() != null) {
					valorationDTO.setCommentRateId(commentRate.getCommentRateId());
					valorationDTO.setValue(commentRate.getValue());
				} else {
					valorationDTO.setValue(0);
				}
				return new ResponseEntity<>(valorationDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * @ApiOperation(value =
	 * "Search all Rates or search Rates by UserID or/and BlogID")
	 * 
	 * @GetMapping("/valorations") public ResponseEntity<List<RateDTO>> getRates(
	 * 
	 * @RequestParam(required = false) @PathVariable("userId") String userId,
	 * 
	 * @RequestParam(required = false) @PathVariable("blogId") String blogId) { try
	 * { List<BlogRate> valorations = new ArrayList<BlogRate>(); User user = null;
	 * Blog blog = null; if (userId != null && !userId.isEmpty()) { Optional<User>
	 * userData = userRepository.findById(userId); if (userData.isPresent()) { user
	 * = userData.get(); } else { return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } } if (blogId != null &&
	 * !blogId.isEmpty()) { Optional<Blog> blogData =
	 * blogRepository.findById(blogId); if (blogData.isPresent()) { blog =
	 * blogData.get(); } else { return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	 * } } if (user != null) { if (blog != null) {
	 * valorationRepository.findByUserAndBlog(user, blog).forEach(valorations::add);
	 * } else { valorationRepository.findByUser(user).forEach(valorations::add); } }
	 * else { if (blog != null) {
	 * valorationRepository.findByBlog(blog).forEach(valorations::add); } else {
	 * valorationRepository.findAll().forEach(valorations::add); } } List<RateDTO>
	 * valorationsDto = new ArrayList<RateDTO>(); if (valorations!=null) { for
	 * (BlogRate valoration: valorations) { RateDTO valorationDto = new RateDTO();
	 * valorationDto.setRateId(valoration.getBlogRateId());
	 * valorationDto.setValue(valoration.getValue()); if
	 * (valoration.getBlog()!=null) {
	 * valorationDto.setBlogId(valoration.getBlog().getBlogId()); } if
	 * (valoration.getUser()!=null) {
	 * valorationDto.setUserName(valoration.getUser().getUserName()); }
	 * valorationsDto.add(valorationDto); } }
	 * 
	 * return new ResponseEntity<>(valorationsDto, HttpStatus.OK); } catch
	 * (Exception e) { return new ResponseEntity<>(null,
	 * HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @ApiOperation(value = "Search Rate by ID")
	 * 
	 * @GetMapping("/getRateById") public ResponseEntity<RateDTO>
	 * getRateById(@RequestParam(required = true) @PathVariable("id") String id) {
	 * try { if (id == null || id.isEmpty()) { return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); } Optional<BlogRate> valorationData
	 * = valorationRepository.findById(id);
	 * 
	 * if (valorationData.isPresent() && valorationData.get()!=null) { RateDTO
	 * valorationDto = new RateDTO();
	 * valorationDto.setRateId(valorationData.get().getBlogRateId());
	 * valorationDto.setValue(valorationData.get().getValue()); if
	 * (valorationData.get().getBlog()!=null) {
	 * valorationDto.setBlogId(valorationData.get().getBlog().getBlogId()); } if
	 * (valorationData.get().getUser()!=null) {
	 * valorationDto.setUserName(valorationData.get().getUser().getUserName()); }
	 * return new ResponseEntity<>(valorationDto, HttpStatus.OK); } else { return
	 * new ResponseEntity<>(HttpStatus.NOT_FOUND); } } catch (Exception e) { return
	 * new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @ApiOperation(value = "Update Rate by ID")
	 * 
	 * @PutMapping("/updateRate") public ResponseEntity<RateDTO>
	 * updateRate(@RequestParam(required = true) @PathVariable("id") String id,
	 * 
	 * @RequestBody RateDTO valoration) { try { if (id == null || id.isEmpty() ||
	 * valoration == null) { return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
	 * Optional<BlogRate> valorationData = valorationRepository.findById(id);
	 * 
	 * if (valorationData.isPresent()) { BlogRate _Rate = valorationData.get();
	 * _Rate.setValue(valoration.getValue()); _Rate.setUpdateDate(new Date());
	 * BlogRate updatedRate = valorationRepository.save(_Rate); if (updatedRate !=
	 * null && updatedRate.getBlogRateId()!=null) { RateDTO valorationDto = new
	 * RateDTO(); valorationDto.setRateId(updatedRate.getBlogRateId()); return new
	 * ResponseEntity<>(valorationDto, HttpStatus.OK); } else { return new
	 * ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } } else { return
	 * new ResponseEntity<>(HttpStatus.NOT_FOUND); } } catch (Exception e) { return
	 * new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 * 
	 * @ApiOperation(value = "Delete Rate by ID")
	 * 
	 * @DeleteMapping("/valoration") public ResponseEntity<HttpStatus>
	 * deleteRate(@RequestParam(required = true) @PathVariable("id") String id) {
	 * try { if (id == null || id.isEmpty()) { return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST); }
	 * valorationRepository.deleteById(id); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.EXPECTATION_FAILED); } }
	 */

}