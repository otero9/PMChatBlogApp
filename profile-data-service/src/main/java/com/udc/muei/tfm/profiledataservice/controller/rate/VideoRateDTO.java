package com.udc.muei.tfm.profiledataservice.controller.rate;

/*
 * 
 * The Class VideoRateDTO.
 * 
 * @author a.oteroc
 * 
 */
public class VideoRateDTO {

	String videoRateId;

	String userName;

	String videoId;

	int value;

	public VideoRateDTO() {

	}

	public VideoRateDTO(String videoRateId, String userName, String videoId, int value) {
		super();
		this.videoRateId = videoRateId;
		this.userName = userName;
		this.videoId = videoId;
		this.value = value;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoRateId() {
		return videoRateId;
	}

	public void setVideoRateId(String videoRateId) {
		this.videoRateId = videoRateId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
