package com.udc.muei.tfm.profiledataservice.model.video;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryRepository;
import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserRepository;

/*
 * 
 * The Class VideoServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	VideoRateRepository videoRateRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public Video saveVideo(Video videoDAO) throws SaveNotAvailableException {
		Video createdVideo = videoRepository.save(videoDAO);
		return createdVideo;
	}

	@Override
	public Video updateVideo(Video videoDAO) throws SaveNotAvailableException {
		Video updatedVideo = videoRepository.save(videoDAO);
		return updatedVideo;
	}

	@Override
	public Boolean deleteVideo(String videoId) throws DeleteNotAvailableException {
		Optional<Video> video = videoRepository.findById(videoId);
		if (video != null && video.isPresent() && video.get() != null) {

			List<VideoRate> valorations = videoRateRepository.findByVideo(video.get());
			if (valorations != null && !valorations.isEmpty()) {
				for (VideoRate valoration : valorations) {
					videoRateRepository.deleteById(valoration.getVideoRateId());
				}
			}
			videoRepository.deleteById(videoId);
			return true;
		}
		return false;
	}

	@Override
	public Video findByVideoId(String videoId) {
		Optional<Video> videoData = videoRepository.findById(videoId);
		if (videoData != null && videoData.isPresent() && videoData.get() != null) {
			return videoData.get();
		}
		return null;
	}

	@Override
	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	@Override
	public List<Video> findByCategoryId(String categoryId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		Category category = null;
		if (categoryData != null && categoryData.isPresent()) {
			category = categoryData.get();
			return videoRepository.findByCategory(category);
		}
		return null;
	}

	@Override
	public List<Video> findByUserId(String userId) {
		Optional<User> userData = userRepository.findById(userId);
		User user = null;
		if (userData != null && userData.isPresent()) {
			user = userData.get();
			return videoRepository.findByUser(user);
		}
		return null;
	}

	@Override
	public int getVideoRates(Video video) {
		List<VideoRate> valorations = videoRateRepository.findByVideo(video);
		if (valorations != null) {
			int points = 0;
			for (VideoRate valoration : valorations) {
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
	public List<Video> findByCategoryIdAndUserId(String categoryId, String userId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		Optional<User> userData = userRepository.findById(userId);
		Category category = null;
		User user = null;
		if (categoryData != null && categoryData.isPresent() & userData != null && userData.isPresent()) {
			user = userData.get();
			category = categoryData.get();
			return videoRepository.findByCategoryAndUser(category, user);
		}
		return null;
	}

	@Override
	public List<Video> findByCategoryIdAndUserIdAndTopicIds(String categoryId, String userId, List<String> tocpidIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VideoRate newVideoRate(String userId, String videoId, int value) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			user = userData.get();
		} else {
			return null;
		}
		Video video = null;
		Optional<Video> videoData = videoRepository.findById(videoId);
		if (videoData != null && videoData.isPresent() && videoData.get() != null) {
			video = videoData.get();
		} else {
			return null;
		}
		if (user != null && video != null) {
			long actualPoints = user.getPoints();
			user.setPoints(actualPoints + value);
			userRepository.save(user);
			VideoRate valorationData = new VideoRate();
			valorationData.setUser(user);
			valorationData.setVideo(video);
			Date actualDate = new Date();
			valorationData.setCreatedDate(actualDate);
			valorationData.setUpdateDate(actualDate);
			valorationData.setValue(value);
			VideoRate createdRate = videoRateRepository.save(valorationData);
			if (createdRate != null && createdRate.getVideoRateId() != null) {
				return createdRate;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public VideoRate findVideoRateByUserIdAndVideoId(String userId, String videoId) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			user = userData.get();
		} else {
			return null;
		}
		Video video = null;
		Optional<Video> videoData = videoRepository.findById(videoId);
		if (videoData != null && videoData.isPresent() && videoData.get() != null) {
			video = videoData.get();
		} else {
			return null;
		}
		VideoRate videoRate = videoRateRepository.findByUserAndVideo(user, video);
		return videoRate;
	}

}
