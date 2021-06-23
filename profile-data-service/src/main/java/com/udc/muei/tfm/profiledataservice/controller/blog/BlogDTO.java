package com.udc.muei.tfm.profiledataservice.controller.blog;

import java.util.Date;
import java.util.List;

import com.udc.muei.tfm.profiledataservice.controller.comment.CommentDTO;
import com.udc.muei.tfm.profiledataservice.controller.file.FileDTO;
import com.udc.muei.tfm.profiledataservice.controller.topic.TopicDTO;

/*
 * 
 * The Class BlogDTO.
 * 
 * @author a.oteroc
 * 
 */
public class BlogDTO {

	private String blogId;
	private String title;
	private String header;
	private String body;
	private String footer;
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

	public BlogDTO() {

	}

	public BlogDTO(String blogId, String title, String header, String body, String footer, Date createdDate,
			Date updateDate, String userId, String userName, String categoryId, String categoryName,
			List<TopicDTO> topics, int points, int countComments, List<CommentDTO> comments, List<FileDTO> files) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.header = header;
		this.body = body;
		this.footer = footer;
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

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
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
