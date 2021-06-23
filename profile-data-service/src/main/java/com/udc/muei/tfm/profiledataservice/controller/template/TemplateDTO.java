package com.udc.muei.tfm.profiledataservice.controller.template;

import java.util.Date;
import java.util.List;

import com.udc.muei.tfm.profiledataservice.controller.topic.TopicDTO;

public class TemplateDTO {

	private String templateId;
	private String templateTitle;
	private String templateDescription;
	private Date createdDate;
	private Date updateDate;
	private String fileId;
	private String fileName;
	private String contentType;
	private long size;
	private String md5;
	private byte[] content;
	private String path;
	private String userId;
	private String userName;
	private String categoryId;
	private String categoryName;
	private List<TopicDTO> topics;
	private int points;

	public TemplateDTO() {

	}

	public TemplateDTO(String templateId, String templateTitle, String templateDescription, Date createdDate,
			Date updateDate, String fileId, String fileName, String contentType, long size, String md5, byte[] content,
			String path, String userId, String userName, String categoryId, String categoryName, List<TopicDTO> topics,
			int points) {
		super();
		this.templateId = templateId;
		this.templateTitle = templateTitle;
		this.templateDescription = templateDescription;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.fileId = fileId;
		this.fileName = fileName;
		this.contentType = contentType;
		this.size = size;
		this.md5 = md5;
		this.content = content;
		this.path = path;
		this.userId = userId;
		this.userName = userName;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.topics = topics;
		this.points = points;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateTitle() {
		return templateTitle;
	}

	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}

	public String getTemplateDescription() {
		return templateDescription;
	}

	public void setTemplateDescription(String templateDescription) {
		this.templateDescription = templateDescription;
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

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

}
