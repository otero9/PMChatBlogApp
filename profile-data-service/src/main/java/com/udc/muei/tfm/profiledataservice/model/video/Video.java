package com.udc.muei.tfm.profiledataservice.model.video;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.topic.Topic;
import com.udc.muei.tfm.profiledataservice.model.user.User;

public class Video {

	@Id
	private String videoId;

	private String videoUrl;

	private String videoTitle;

	private String videoDescription;

	private Date createdDate;

	private Date updateDate;

	@DBRef
	private Category category;

	@DBRef
	private List<Topic> topics;

	@DBRef
	private User user;

	public Video() {

	}

	public Video(String videoId, String videoUrl, String videoTitle, String videoDescription, Date createdDate,
			Date updateDate, Category category, List<Topic> topics, User user) {
		super();
		this.videoId = videoId;
		this.videoUrl = videoUrl;
		this.videoTitle = videoTitle;
		this.videoDescription = videoDescription;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.category = category;
		this.topics = topics;
		this.user = user;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
