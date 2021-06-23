package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.udc.muei.tfm.profiledataservice.model.user.User;

/*
 * 
 * The Class BlogRate.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "template_rates")
public class TemplateRate {

	@Id
	private String templateRateId;

	@DBRef
	private User user;

	@DBRef
	private Template template;

	private int value;

	private Date createdDate;

	private Date updateDate;

	public TemplateRate() {

	}

	public TemplateRate(String templateRateId, User user, Template template, int value, Date createdDate,
			Date updateDate) {
		super();
		this.templateRateId = templateRateId;
		this.user = user;
		this.template = template;
		this.value = value;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}

	public String getTemplateRateId() {
		return templateRateId;
	}

	public void setTemplateRateId(String templateRateId) {
		this.templateRateId = templateRateId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
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
