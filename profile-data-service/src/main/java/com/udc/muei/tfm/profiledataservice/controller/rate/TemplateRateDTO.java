package com.udc.muei.tfm.profiledataservice.controller.rate;

/*
 * 
 * The Class BlogRateDTO.
 * 
 * @author a.oteroc
 * 
 */
public class TemplateRateDTO {

	String templateRateId;

	String userName;

	String templateId;

	int value;

	public TemplateRateDTO() {

	}

	public TemplateRateDTO(String templateRateId, String userName, String templateId, int value) {
		super();
		this.templateRateId = templateRateId;
		this.userName = userName;
		this.templateId = templateId;
		this.value = value;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateRateId() {
		return templateRateId;
	}

	public void setTemplateRateId(String templateRateId) {
		this.templateRateId = templateRateId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
