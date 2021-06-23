package com.udc.muei.tfm.profiledataservice.model.video;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class VideoRate.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "video_rates")
public class VideoRate {

	@Id
	private String videoRateId;

	@DBRef
	private User user;

	@DBRef
	private Video video;

	private int value;

	private Date createdDate;

	private Date updateDate;

	public VideoRate() {

	}

	public VideoRate(String videoRateId, User user, Video video, int value, Date createdDate, Date updateDate) {
		super();
		this.videoRateId = videoRateId;
		this.user = user;
		this.video = video;
		this.value = value;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	public String getVideoRateId() {
		return videoRateId;
	}

	public void setVideoRateId(String videoRateId) {
		this.videoRateId = videoRateId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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

}
