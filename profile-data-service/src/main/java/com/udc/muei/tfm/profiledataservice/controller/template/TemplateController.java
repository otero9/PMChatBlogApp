package com.udc.muei.tfm.profiledataservice.controller.template;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.udc.muei.tfm.profiledataservice.model.category.Category;
import com.udc.muei.tfm.profiledataservice.model.category.CategoryService;
import com.udc.muei.tfm.profiledataservice.model.template.File;
import com.udc.muei.tfm.profiledataservice.model.template.Template;
import com.udc.muei.tfm.profiledataservice.model.template.TemplateService;
import com.udc.muei.tfm.profiledataservice.model.user.User;
import com.udc.muei.tfm.profiledataservice.model.user.UserService;
import com.udc.muei.tfm.profiledataservice.utils.TemplatePointsSorter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class TemplateController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/templateAPI")
@Api(tags = "Template Controller", description = "This API has a CRUD for Templates")
public class TemplateController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	TemplateService templateService;

	@Autowired
	UserService userService;

	@ApiOperation(value = "Search all Templates or search Templates by UserID or/and CategoryID")
	@GetMapping("/templates")
	public ResponseEntity<List<TemplateDTO>> getTemplates(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String categoryId) {
		try {
			List<Template> templates = null;
			if (userId != null && !userId.isEmpty()) {
				if (categoryId != null && !categoryId.isEmpty()) {
					templates = templateService.findByCategoryIdAndUserId(categoryId, userId);
				} else {
					templates = templateService.findByUserId(userId);
				}

			} else {
				if (categoryId != null && !categoryId.isEmpty()) {
					templates = templateService.findByCategoryId(categoryId);
				} else {
					templates = templateService.getAllTemplates();
				}
			}
			if (templates != null && !templates.isEmpty()) {
				List<TemplateDTO> templatesDTOs = new ArrayList<>();

				for (Template template : templates) {
					TemplateDTO templateDTO = new TemplateDTO();
					templateDTO.setCategoryId(template.getCategory().getCategoryId());
					templateDTO.setCategoryName(template.getCategory().getCategoryName());
					templateDTO.setUserId(template.getUser().getUserId());
					templateDTO.setUserName(template.getUser().getUserName());
					templateDTO.setTemplateId(template.getTemplateId());
					templateDTO.setTemplateTitle(template.getTemplateTitle());
					templateDTO.setTemplateDescription(template.getTemplateDescription());
					templateDTO.setCreatedDate(template.getCreatedDate());
					templateDTO.setUpdateDate(template.getUpdateDate());
					File file = templateService.findFileByTemplateId(template.getTemplateId());
					templateDTO.setFileId(file.getFileId());
					templateDTO.setFileName(file.getFileName());
					templateDTO.setMd5(file.getMd5());
					templateDTO.setContent(file.getContent());
					templateDTO.setContentType(file.getContentType());
					templateDTO.setSize(file.getSize());
					templateDTO.setPath(file.getPath());
					templateDTO.setPoints(templateService.getTemplateRates(template));
					templatesDTOs.add(templateDTO);
				}
				return new ResponseEntity<>(templatesDTOs, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search best Templates")
	@GetMapping("/bestTemplates")
	public ResponseEntity<List<TemplateDTO>> getBestTemplates(@RequestParam(required = false) String categoryId) {
		try {
			List<Template> templates = null;
			if (categoryId != null && !categoryId.isEmpty()) {
				templates = templateService.findByCategoryId(categoryId);
			} else {
				templates = templateService.getAllTemplates();
			}
			if (templates != null && !templates.isEmpty()) {
				List<TemplateDTO> templatesDTOs = new ArrayList<>();

				for (Template template : templates) {
					TemplateDTO templateDTO = new TemplateDTO();
					templateDTO.setCategoryId(template.getCategory().getCategoryId());
					templateDTO.setCategoryName(template.getCategory().getCategoryName());
					templateDTO.setUserId(template.getUser().getUserId());
					templateDTO.setUserName(template.getUser().getUserName());
					templateDTO.setTemplateId(template.getTemplateId());
					templateDTO.setTemplateTitle(template.getTemplateTitle());
					templateDTO.setTemplateDescription(template.getTemplateDescription());
					templateDTO.setCreatedDate(template.getCreatedDate());
					templateDTO.setUpdateDate(template.getUpdateDate());
					File file = templateService.findFileByTemplateId(template.getTemplateId());
					templateDTO.setFileId(file.getFileId());
					templateDTO.setFileName(file.getFileName());
					templateDTO.setMd5(file.getMd5());
					templateDTO.setContent(file.getContent());
					templateDTO.setContentType(file.getContentType());
					templateDTO.setSize(file.getSize());
					templateDTO.setPath(file.getPath());
					templateDTO.setPoints(templateService.getTemplateRates(template));
					templatesDTOs.add(templateDTO);
				}
				templatesDTOs.sort(new TemplatePointsSorter());
				if (templatesDTOs.size() > 0 && templatesDTOs.size() > 10) {
					return new ResponseEntity<>(templatesDTOs.subList(0, 9), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(templatesDTOs, HttpStatus.OK);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search Template by ID")
	@GetMapping("/getTemplateById")
	public ResponseEntity<TemplateDTO> getTemplateById(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Template template = templateService.findByTemplateId(id);
			if (template != null) {
				TemplateDTO templateDTO = new TemplateDTO();
				templateDTO.setCategoryId(template.getCategory().getCategoryId());
				templateDTO.setCategoryName(template.getCategory().getCategoryName());
				templateDTO.setUserId(template.getUser().getUserId());
				templateDTO.setUserName(template.getUser().getUserName());
				templateDTO.setTemplateId(template.getTemplateId());
				templateDTO.setTemplateTitle(template.getTemplateTitle());
				templateDTO.setTemplateDescription(template.getTemplateDescription());
				templateDTO.setCreatedDate(template.getCreatedDate());
				templateDTO.setUpdateDate(template.getUpdateDate());
				File file = templateService.findFileByTemplateId(template.getTemplateId());
				templateDTO.setFileId(file.getFileId());
				templateDTO.setFileName(file.getFileName());
				templateDTO.setPoints(templateService.getTemplateRates(template));
				return new ResponseEntity<>(templateDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Upload File")
	@PostMapping("/file")
	public ResponseEntity<TemplateDTO> uploadFile(
			@RequestParam(required = true) @PathVariable("file") MultipartFile file) {
		try {
			if (file == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			InputStream inputStream = file.getInputStream();
			File fileDAO = new File();
			fileDAO.setContentType(file.getContentType());
			fileDAO.setSize(file.getSize());
			fileDAO.setFileName(file.getOriginalFilename());
			Date actualDate = new Date();
			fileDAO.setCreateDate(actualDate);
			fileDAO.setUpdateDate(actualDate);
			fileDAO.setContent(inputStream.readAllBytes());
			fileDAO = templateService.uploadFile(fileDAO);
			TemplateDTO templateDto = new TemplateDTO();
			templateDto.setFileId(fileDAO.getFileId());
			System.out.println("FileId:" + fileDAO.getFileId());
			return new ResponseEntity<>(templateDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Download File")
	@GetMapping("/file")
	public ResponseEntity<byte[]> downloadFile(@RequestParam(required = true) @PathVariable("fileId") String fileId) {
		try {
			if (fileId == null || fileId.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			File file = templateService.findFileById(fileId);
			if (file != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.valueOf(file.getContentType()));
				headers.setContentLength(file.getContent().length);
				headers.set("Content-Disposition", "attachment; filename=" + file.getFileName());
				return new ResponseEntity<>(file.getContent(), headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Create new Template")
	@PostMapping("/template")
	public ResponseEntity<TemplateDTO> createTemplate(
			@RequestParam(required = true) @PathVariable("categoryId") String categoryId,
			@RequestParam(required = true) @PathVariable("userId") String userId, @RequestBody TemplateDTO template) {
		try {
			if (categoryId != null && !categoryId.isEmpty() && userId != null && !userId.isEmpty()) {
				User user = null;
				user = userService.findByUserIdDAO(userId);
				if (user == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				Category category = categoryService.findByCategoryId(categoryId);
				if (category == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				if (user != null && category != null && template.getTemplateTitle() != null
						&& !template.getTemplateTitle().isEmpty() && template.getTemplateDescription() != null
						&& !template.getTemplateDescription().isEmpty() && template.getFileId() != null
						&& !template.getFileId().isEmpty()) {
					Template templateData = new Template();
					templateData.setUser(user);
					templateData.setCategory(category);
					Date actualDate = new Date();
					templateData.setCreatedDate(actualDate);
					templateData.setUpdateDate(actualDate);
					templateData.setTemplateTitle(template.getTemplateTitle());
					templateData.setTemplateDescription(template.getTemplateDescription());
					File file = templateService.findFileById(template.getFileId());
					Template createdTemplate = null;
					if (file != null) {
						createdTemplate = templateService.saveOrUpdateTemplate(templateData, file);
						long actualPoints = user.getPoints();
						user.setPoints(actualPoints + 1);
						userService.saveUserDAO(user);
					} else {
						return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					if (createdTemplate != null && createdTemplate.getTemplateId() != null) {
						TemplateDTO templateDTO = new TemplateDTO();
						templateDTO.setTemplateId(createdTemplate.getTemplateId());
						return new ResponseEntity<>(templateDTO, HttpStatus.CREATED);
					} else {
						return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update Template by ID")
	@PutMapping("/updateTemplate")
	public ResponseEntity<TemplateDTO> updateTemplate(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestBody TemplateDTO template) {
		try {
			if (id == null || id.isEmpty() || template == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			Template _Template = templateService.findByTemplateId(id);
			if (_Template != null) {
				_Template.setCategory(categoryService.findByCategoryId(template.getCategoryId()));
				_Template.setUpdateDate(new Date());
				_Template.setTemplateTitle(template.getTemplateTitle());
				_Template.setTemplateDescription(template.getTemplateDescription());
				File fileToRemove = templateService.findFileByTemplateId(id);
				templateService.deleteFile(fileToRemove.getFileId());
				File file = templateService.findFileById(template.getFileId());
				Template updatedTemplate = templateService.saveOrUpdateTemplate(_Template, file);
				if (updatedTemplate != null && updatedTemplate.getTemplateId() != null) {
					TemplateDTO templateDTO = new TemplateDTO();
					templateDTO.setTemplateId(updatedTemplate.getTemplateId());
					return new ResponseEntity<>(templateDTO, HttpStatus.OK);
				} else {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Delete Template by ID")
	@DeleteMapping("/template")
	public ResponseEntity<?> deleteTemplate(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: invalid templateId!");
			}
			Boolean deleted = templateService.deleteTemplate(id);
			if (deleted) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Template not found!");
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
