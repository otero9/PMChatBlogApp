package com.udc.muei.tfm.profiledataservice.controller.video;

import java.util.Date;
import java.util.List;

import com.udc.muei.tfm.profiledataservice.controller.comment.CommentDTO;
import com.udc.muei.tfm.profiledataservice.controller.topic.TopicDTO;

/*
 * 
 * The Class VideoDTO.
 * 
 * @author a.oteroc
 * 
 */
public class VideoDTO {

	private String videoId;
	private String videoUrl;
	private String videoTitle;
	private String videoDescription;
	private Date createdDate;
	private Date updateDate;
	private String userId;
	private String userName;
	private String categoryId;
	private String categoryName;
	private List<TopicDTO> topics;
	private int points;
	private int countComments;
	private List<CommentDTO> comments;
	
	public VideoDTO() {
		
	}
	
	public VideoDTO(String videoId, String videoUrl, String videoTitle, String videoDescription, Date createdDate,
			Date updateDate, String userId, String userName, String categoryId, String categoryName,
			List<TopicDTO> topics, int points, int countComments, List<CommentDTO> comments) {
		super();
		this.videoId = videoId;
		this.videoUrl = videoUrl;
		this.videoTitle = videoTitle;
		this.videoDescription = videoDescription;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.userId = userId;
		this.userName = userName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.topics = topics;
		this.points = points;
		this.countComments = countComments;
		this.comments = comments;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<TopicDTO> getTopics() {
		return topics;
	}

	public void setTopics(List<TopicDTO> topics) {
		this.topics = topics;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCountComments() {
		return countComments;
	}

	public void setCountComments(int countComments) {
		this.countComments = countComments;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	
}
