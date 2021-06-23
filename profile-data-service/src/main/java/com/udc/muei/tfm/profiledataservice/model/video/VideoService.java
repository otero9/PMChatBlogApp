package com.udc.muei.tfm.profiledataservice.model.video;

import java.util.List;

import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;

public interface VideoService {

	/**
	 * saveVideo
	 * 
	 * @param videoDAO
	 * @return Video
	 */
	Video saveVideo(Video videoDAO) throws SaveNotAvailableException;

	/**
	 * updateVideo
	 * 
	 * @param videoDAO
	 * @return Video
	 */
	Video updateVideo(Video videoDAO) throws SaveNotAvailableException;

	/**
	 * deleteVideo
	 * 
	 * @param videoId
	 * @return Boolean
	 */
	Boolean deleteVideo(String videoId) throws DeleteNotAvailableException;

	/**
	 * findByVideoId
	 * 
	 * @param videoId
	 * @return Video
	 */
	Video findByVideoId(String videoId);

	/**
	 * getAllVideos
	 * 
	 * @return List<Video>
	 */
	List<Video> getAllVideos();

	/**
	 * findByCategoryId
	 * 
	 * @param categoryId
	 * @return List<Video>
	 */
	List<Video> findByCategoryId(String categoryId);

	/**
	 * findByUserId
	 * 
	 * @param userId
	 * @return List<Video>
	 */
	List<Video> findByUserId(String userId);

	/**
	 * findByCategoryIdAndUserId
	 * 
	 * @param categoryId
	 * @param userId
	 * @return List<Video>
	 */
	List<Video> findByCategoryIdAndUserId(String categoryId, String userId);

	/**
	 * findByCategoryIdAndUserId
	 * 
	 * @param categoryId
	 * @param userId
	 * @param topics
	 * @return List<Video>
	 */
	List<Video> findByCategoryIdAndUserIdAndTopicIds(String categoryId, String userId, List<String> tocpidIds);

	/**
	 * getVideoRates
	 * 
	 * @param video
	 * @return int
	 */
	int getVideoRates(Video video);

	/**
	 * newVideoRate
	 * 
	 * @param userId
	 * @param videoId
	 * @param value
	 * @return VideoRate
	 */
	VideoRate newVideoRate(String userId, String videoId, int value);

	/**
	 * findVideoRateByUserIdAndVideoId
	 * 
	 * @param userId
	 * @param videoId
	 * @return VideoRate
	 */
	VideoRate findVideoRateByUserIdAndVideoId(String userId, String videoId);

}