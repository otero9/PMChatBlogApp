package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * 
 * The Class File.
 * 
 * @author a.oteroc
 * 
 */
@Document(collection = "files")
public class File {

	@Id
	private String fileId;

	@DBRef
	private Template template;

	private String fileName;

	private String contentType;

	private long size;

	private String md5;

	private byte[] content;

	private String path;

	private Date createDate;

	private Date updateDate;

	public File() {

	}

	public File(String fileId, Template template, String fileName, String contentType, long size, String md5,
			byte[] content, String path, Date createDate, Date updateDate) {
		super();
		this.fileId = fileId;
		this.template = template;
		this.fileName = fileName;
		this.contentType = contentType;
		this.size = size;
		this.md5 = md5;
		this.content = content;
		this.path = path;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}