package com.udc.muei.tfm.profiledataservice.model.template;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryRepository;
import com.udc.muei.tfm.profiledataservice.model.comment.CommentRepository;
import com.udc.muei.tfm.profiledataservice.model.exceptions.DeleteNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.exceptions.SaveNotAvailableException;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserRepository;

/*
 * 
 * The Class TemplateServiceImpl.
 * 
 * @author a.oteroc
 * 
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TemplateRepository templateRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FileRepository fileRepository;

	@Autowired
	TemplateRateRepository templateRateRepository;

	@Override
	public Template saveOrUpdateTemplate(Template templateDAO, File fileDAO) throws SaveNotAvailableException {
		Template template = templateRepository.save(templateDAO);
		fileDAO.setTemplate(template);
		fileRepository.save(fileDAO);
		return template;
	}

	@Override
	public Boolean deleteTemplate(String templateId) throws DeleteNotAvailableException {
		Optional<Template> templateData = templateRepository.findById(templateId);
		if (templateData != null && templateData.get() != null) {
			Template template = templateData.get();
			File file = fileRepository.findByTemplate(template);
			fileRepository.deleteById(file.getFileId());
			templateRepository.deleteById(template.getTemplateId());
			return true;
		}
		return false;
	}

	@Override
	public Template findByTemplateId(String templateId) {
		Optional<Template> templateData = templateRepository.findById(templateId);
		if (templateData != null && templateData.get() != null) {
			return templateData.get();
		}
		return null;
	}

	@Override
	public List<Template> getAllTemplates() {
		return templateRepository.findAll();
	}

	@Override
	public List<Template> findByCategoryId(String categoryId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		Category category = null;
		if (categoryData != null && categoryData.isPresent()) {
			category = categoryData.get();
		}
		return templateRepository.findByCategory(category);
	}

	@Override
	public List<Template> findByUserId(String userId) {
		Optional<User> userData = userRepository.findById(userId);
		User user = null;
		if (userData != null && userData.isPresent()) {
			user = userData.get();
		}
		return templateRepository.findByUser(user);
	}

	@Override
	public List<Template> findByCategoryIdAndUserId(String categoryId, String userId) {
		Optional<Category> categoryData = categoryRepository.findById(categoryId);
		Optional<User> userData = userRepository.findById(userId);
		Category category = null;
		User user = null;
		if (categoryData != null && categoryData.isPresent() & userData != null && userData.isPresent()) {
			user = userData.get();
			category = categoryData.get();
			return templateRepository.findByCategoryAndUser(category, user);
		}
		return null;
	}

	@Override
	public List<Template> findByCategoryIdAndUserIdAndTopicIds(String categoryId, String userId,
			List<String> tocpidIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTemplateRates(Template template) {
		List<TemplateRate> valorations = templateRateRepository.findByTemplate(template);
		if (valorations != null) {
			int points = 0;
			for (TemplateRate valoration : valorations) {
				points += valoration.getValue();
			}
			if (points < 0) {
				return 0;
			} else {
				return points;
			}
		}
		return 0;
	}

	@Override
	public TemplateRate newTemplateRate(String userId, String templateId, int value) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData.isPresent()) {
			user = userData.get();
		} else {
			return null;
		}
		Template template = null;
		Optional<Template> templateData = templateRepository.findById(templateId);
		if (templateData.isPresent()) {
			template = templateData.get();
		} else {
			return null;
		}
		if (user != null && template != null) {
			long actualPoints = user.getPoints();
			user.setPoints(actualPoints + value);
			userRepository.save(user);
			TemplateRate valorationData = new TemplateRate();
			valorationData.setUser(user);
			valorationData.setTemplate(template);
			Date actualDate = new Date();
			valorationData.setCreatedDate(actualDate);
			valorationData.setUpdateDate(actualDate);
			valorationData.setValue(value);
			TemplateRate createdRate = templateRateRepository.save(valorationData);
			if (createdRate != null && createdRate.getTemplateRateId() != null) {
				return createdRate;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public File findFileByTemplateId(String templateId) {
		Optional<Template> templateData = templateRepository.findById(templateId);
		if (templateData != null && templateData.get() != null) {
			Template template = templateData.get();
			return fileRepository.findByTemplate(template);
		}
		return null;
	}

	@Override
	public TemplateRate findTemplateRateByUserIdAndTemplateId(String userId, String templateId) {
		User user = null;
		Optional<User> userData = userRepository.findById(userId);
		if (userData != null && userData.isPresent() && userData.get() != null) {
			user = userData.get();
		} else {
			return null;
		}
		Template template = null;
		Optional<Template> templateData = templateRepository.findById(templateId);
		if (templateData != null && templateData.isPresent() && templateData.get() != null) {
			template = templateData.get();
		} else {
			return null;
		}
		TemplateRate templateRate = templateRateRepository.findByUserAndTemplate(user, template);
		return templateRate;
	}

	@Override
	public File uploadFile(File file) {
		File fileData = fileRepository.save(file);
		if (fileData != null && fileData.getFileId() != null) {
			return fileData;
		}
		return null;
	}

	@Override
	public Boolean deleteFile(String fileId) throws DeleteNotAvailableException {
		if (fileId != null && !fileId.isEmpty()) {
			Optional<File> file = fileRepository.findById(fileId);
			if (file != null && file.isPresent() && file.get() != null) {
				fileRepository.delete(file.get());
				return true;
			}
		}
		return false;
	}

	@Override
	public File findFileById(String fileId) {
		if (fileId != null && !fileId.isEmpty()) {
			Optional<File> file = fileRepository.findById(fileId);
			if (file != null && file.isPresent() && file.get() != null) {
				return file.get();
			}
		}
		return null;
	}

}
