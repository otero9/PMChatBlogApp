package com.udc.muei.tfm.profiledataservice.controller.video;

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

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryService;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserService;
import com.udc.muei.tfm.profiledataservice.model.video.Video;
import com.udc.muei.tfm.profiledataservice.model.video.VideoService;
import com.udc.muei.tfm.profiledataservice.utils.VideoPointsSorter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class VideoController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/videoAPI")
@Api(tags = "Video Controller", description = "This API has a CRUD for Videos")
public class VideoController {

	@Autowired
	VideoService videoService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@ApiOperation(value = "Search all Videos or search Videos by UserID or/and CategoryID")
	@GetMapping("/videos")
	public ResponseEntity<List<VideoDTO>> getVideos(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String categoryId) {
		try {
			List<Video> videos = null;
			if (userId != null && !userId.isEmpty()) {
				if (categoryId != null && !categoryId.isEmpty()) {
					videos = videoService.findByCategoryIdAndUserId(categoryId, userId);
				} else {
					videos = videoService.findByUserId(userId);
				}

			} else {
				if (categoryId != null && !categoryId.isEmpty()) {
					videos = videoService.findByCategoryId(categoryId);
				} else {
					videos = videoService.getAllVideos();
				}
			}
			if (videos != null && !videos.isEmpty()) {
				List<VideoDTO> videosDto = new ArrayList<>();
				for (Video video : videos) {
					VideoDTO videoDTO = new VideoDTO();
					videoDTO.setVideoId(video.getVideoId());
					videoDTO.setVideoTitle(video.getVideoTitle());
					videoDTO.setVideoDescription(video.getVideoDescription());
					videoDTO.setCreatedDate(video.getCreatedDate());
					videoDTO.setUpdateDate(video.getUpdateDate());
					videoDTO.setVideoUrl(video.getVideoUrl());
					videoDTO.setUserId(video.getUser().getUserId());
					videoDTO.setUserName(video.getUser().getUserName());
					videoDTO.setCategoryId(video.getCategory().getCategoryId());
					videoDTO.setCategoryName(video.getCategory().getCategoryName());
					videoDTO.setPoints(videoService.getVideoRates(video));
					videosDto.add(videoDTO);
				}
				return new ResponseEntity<>(videosDto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search best Videos")
	@GetMapping("/bestVideos")
	public ResponseEntity<List<VideoDTO>> getBestVideos(@RequestParam(required = false) String categoryId) {
		try {
			List<Video> videos = null;
			if (categoryId != null && !categoryId.isEmpty()) {
				videos = videoService.findByCategoryId(categoryId);
			} else {
				videos = videoService.getAllVideos();
			}
			if (videos != null && !videos.isEmpty()) {
				List<VideoDTO> videosDto = new ArrayList<>();
				for (Video video : videos) {
					VideoDTO videoDTO = new VideoDTO();
					videoDTO.setVideoId(video.getVideoId());
					videoDTO.setVideoTitle(video.getVideoTitle());
					videoDTO.setVideoDescription(video.getVideoDescription());
					videoDTO.setCreatedDate(video.getCreatedDate());
					videoDTO.setUpdateDate(video.getUpdateDate());
					videoDTO.setVideoUrl(video.getVideoUrl());
					videoDTO.setUserId(video.getUser().getUserId());
					videoDTO.setUserName(video.getUser().getUserName());
					videoDTO.setCategoryId(video.getCategory().getCategoryId());
					videoDTO.setCategoryName(video.getCategory().getCategoryName());
					videoDTO.setPoints(videoService.getVideoRates(video));
					videosDto.add(videoDTO);
				}
				videosDto.sort(new VideoPointsSorter());
				if (videosDto.size() > 0 && videosDto.size() > 10) {
					return new ResponseEntity<>(videosDto.subList(0, 9), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(videosDto, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search Video by ID")
	@GetMapping("/getVideoById")
	public ResponseEntity<VideoDTO> getVideoById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Video video = videoService.findByVideoId(id);
			if (video != null) {
				VideoDTO videoDTO = new VideoDTO();
				videoDTO.setVideoId(video.getVideoId());
				videoDTO.setVideoTitle(video.getVideoTitle());
				videoDTO.setVideoDescription(video.getVideoDescription());
				videoDTO.setCreatedDate(video.getCreatedDate());
				videoDTO.setUpdateDate(video.getUpdateDate());
				videoDTO.setVideoUrl(video.getVideoUrl());
				videoDTO.setUserId(video.getUser().getUserId());
				videoDTO.setUserName(video.getUser().getUserName());
				videoDTO.setCategoryId(video.getCategory().getCategoryId());
				videoDTO.setCategoryName(video.getCategory().getCategoryName());
				videoDTO.setPoints(videoService.getVideoRates(video));
				return new ResponseEntity<>(videoDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Delete Video by ID")
	@DeleteMapping("/video")
	public ResponseEntity<?> deleteVideo(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: invalid videoId!");
			}
			Boolean deleted = videoService.deleteVideo(id);
			if (deleted) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Video not found!");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Upload new Video")
	@PostMapping("/video")
	public ResponseEntity<VideoDTO> uploadVideo(
			@RequestParam(required = true) @PathVariable("categoryId") String categoryId,
			@RequestParam(required = true) @PathVariable("userId") String userId, @RequestBody VideoDTO video) {
		try {
			if (categoryId != null && !categoryId.isEmpty() && userId != null && !userId.isEmpty()) {
				User user = userService.findByUserIdDAO(userId);
				if (user == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				Category category = categoryService.findByCategoryId(categoryId);
				if (category == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				if (user != null && category != null && video.getVideoTitle() != null
						&& !video.getVideoTitle().isEmpty() && video.getVideoDescription() != null
						&& !video.getVideoDescription().isEmpty() && video.getVideoUrl() != null
						&& !video.getVideoUrl().isEmpty()) {
					Video videoData = new Video();
					videoData.setUser(user);
					videoData.setCategory(category);
					Date actualDate = new Date();
					videoData.setCreatedDate(actualDate);
					videoData.setUpdateDate(actualDate);
					videoData.setVideoTitle(video.getVideoTitle());
					videoData.setVideoDescription(video.getVideoDescription());
					videoData.setVideoUrl(video.getVideoUrl());
					Video createdVideo = videoService.saveVideo(videoData);
					long actualPoints = user.getPoints();
					user.setPoints(actualPoints + 1);
					userService.saveUserDAO(user);
					if (createdVideo != null && createdVideo.getVideoId() != null) {
						VideoDTO videoDTO = new VideoDTO();
						videoDTO.setVideoId(createdVideo.getVideoId());
						return new ResponseEntity<>(videoDTO, HttpStatus.CREATED);
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

	@ApiOperation(value = "Update Video by ID")
	@PutMapping("/updateVideo")
	public ResponseEntity<VideoDTO> updateVideo(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestBody VideoDTO video) {
		try {
			if (id == null || id.isEmpty() || video == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Video videoData = videoService.findByVideoId(id);
			if (videoData != null) {
				videoData.setUpdateDate(new Date());
				videoData.setVideoTitle(video.getVideoTitle());
				videoData.setVideoDescription(video.getVideoDescription());
				videoData.setVideoUrl(video.getVideoUrl());
				videoData.setUpdateDate(new Date());
				Video videoUpdated = videoService.updateVideo(videoData);
				if (videoUpdated != null) {
					VideoDTO videoResponse = new VideoDTO();
					videoResponse.setVideoId(videoUpdated.getVideoId());
					return new ResponseEntity<>(videoResponse, HttpStatus.OK);
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

}
