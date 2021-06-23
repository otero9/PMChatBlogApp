package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.topic.Topic;
import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class Template.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "templates")
public class Template {

	@Id
	private String templateId;

	private String templateTitle;

	private String templateDescription;

	private Date createdDate;

	private Date updateDate;

	@DBRef
	private Category category;

	@DBRef
	private List<Topic> topics;

	@DBRef
	private User user;

	public Template() {

	}

	public Template(String templateId, String templateTitle, String templateDescription, Date createdDate,
			Date updateDate, Category category, List<Topic> topics, User user) {
		super();
		this.templateId = templateId;
		this.templateTitle = templateTitle;
		this.templateDescription = templateDescription;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
		this.category = category;
		this.topics = topics;
		this.user = user;
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
