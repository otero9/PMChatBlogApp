package com.udc.muei.tfm.profiledataservice.controller.file;

/*
 * 
 * The Class FileDTO.
 * 
 * @author a.oteroc
 * 
 */
public class FileDTO {

	private String fileId;

	private String name;
	private String contentType;
	private long size;
	private String md5;
	private byte[] content;

	public FileDTO() {

	}

	public FileDTO(String fileId, String name, String contentType, long size, String md5, byte[] content) {
		super();
		this.fileId = fileId;
		this.name = name;
		this.contentType = contentType;
		this.size = size;
		this.md5 = md5;
		this.content = content;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
