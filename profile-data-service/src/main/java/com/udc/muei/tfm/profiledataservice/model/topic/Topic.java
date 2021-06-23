package com.udc.muei.tfm.profiledataservice.model.topic;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
 * The Class Topic.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "topics")
public class Topic {

	@Id
	private String topicId;

	private String topicName;

	private String topicDescription;

	private Date createDate;

	private Date updateDate;

	public Topic() {

	}

	public Topic(String topicId, String topicName, String topicDescription, Date createdDate, Date updateDate) {
		super();
		this.topicId = topicId;
		this.topicName = topicName;
		this.topicDescription = topicDescription;
		this.createDate = createdDate;
		this.updateDate = updateDate;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicDescription() {
		return topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createdDate) {
		this.createDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}