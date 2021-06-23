package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/*
 * 
 * The Interface FileRepository.
 * 
 * @author a.oteroc
 * 
 */
public interface FileRepository extends MongoRepository<File, String> {

	/**
	 * findByFileName
	 * 
	 * @param fileName
	 * @return List<File>
	 */
	List<File> findByFileName(String fileName);

	/**
	 * findByTemplate
	 * 
	 * @param template
	 * @return List<File>
	 */
	File findByTemplate(Template template);

}
