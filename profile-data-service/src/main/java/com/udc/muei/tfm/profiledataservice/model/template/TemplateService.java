package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.List;

import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;

public interface TemplateService {

	/**
	 * saveOrUpdateTemplate
	 * 
	 * @param templateDAO
	 * @param fileDAO
	 * @return Template
	 */
	Template saveOrUpdateTemplate(Template templateDAO, File fileDAO) throws SaveNotAvailableException;

	/**
	 * deleteTemplate
	 * 
	 * @param templateId
	 * @return Boolean
	 */
	Boolean deleteTemplate(String templateId) throws DeleteNotAvailableException;

	/**
	 * findByTemplateId
	 * 
	 * @param templateId
	 * @return Template
	 */
	Template findByTemplateId(String templateId);

	/**
	 * findFileByTemplateId
	 * 
	 * @param templateId
	 * @return File
	 */
	File findFileByTemplateId(String templateId);

	/**
	 * getAllTemplates
	 * 
	 * @return List<Template>
	 */
	List<Template> getAllTemplates();

	/**
	 * findByCategoryId
	 * 
	 * @param categoryId
	 * @return List<Template>
	 */
	List<Template> findByCategoryId(String categoryId);

	/**
	 * findByUserId
	 * 
	 * @param userId
	 * @return List<Template>
	 */
	List<Template> findByUserId(String userId);

	/**
	 * findByCategoryIdAndUserId
	 * 
	 * @param categoryId
	 * @param userId
	 * @return List<Template>
	 */
	List<Template> findByCategoryIdAndUserId(String categoryId, String userId);

	/**
	 * findByCategoryIdAndUserIdAndTopicIds
	 * 
	 * @param categoryId
	 * @param userId
	 * @param topics
	 * @return List<Template>
	 */
	List<Template> findByCategoryIdAndUserIdAndTopicIds(String categoryId, String userId, List<String> tocpidIds);

	/**
	 * getTemplateRates
	 * 
	 * @param Template
	 * @return int
	 */
	int getTemplateRates(Template template);

	/**
	 * newTemplateRate
	 * 
	 * @param userId
	 * @param templateId
	 * @param value
	 * @return TemplateRate
	 */
	TemplateRate newTemplateRate(String userId, String templateId, int value);

	/**
	 * findTemplateRateByUserIdAndTemplateId
	 * 
	 * @param userId
	 * @param templateId
	 * @return TemplateRate
	 */
	TemplateRate findTemplateRateByUserIdAndTemplateId(String userId, String templateId);

	/**
	 * uploadFile
	 * 
	 * @param file
	 * @return File
	 */
	File uploadFile(File file);

	/**
	 * findFileById
	 * 
	 * @param fileId
	 * @return File
	 */
	File findFileById(String fileId);

	/**
	 * deleteFile
	 * 
	 * @param fileId
	 * @return Boolean
	 */
	Boolean deleteFile(String fileId) throws DeleteNotAvailableException;

}
