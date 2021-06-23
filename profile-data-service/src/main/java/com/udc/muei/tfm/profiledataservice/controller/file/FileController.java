package com.udc.muei.tfm.profiledataservice.controller.file;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.udc.muei.tfm.profiledataservice.model.template.File;
import com.udc.muei.tfm.profiledataservice.model.template.FileRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 * 
 * The Class FileController.
 * 
 * @author a.oteroc
 * 
 */
@RestController
@CrossOrigin
@RequestMapping("/profileDataService/fileAPI")
@Api(tags = "File Controller", description = "This API has a CRUD for Files")
public class FileController {

	@Autowired
	FileRepository fileRepository;

	@ApiOperation(value = "Search all Files or search Files by filerName")
	@GetMapping("/files")
	public ResponseEntity<List<File>> getFiles(@RequestParam(required = false) String fileName) {
		try {
			List<File> files = new ArrayList<File>();

			if (fileName == null || fileName.isEmpty()) {
				fileRepository.findAll().forEach(files::add);
			} else {
				fileRepository.findByFileName(fileName).forEach(files::add);
			}

			if (files.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(files, HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Search File by ID")
	@GetMapping("/getFileById")
	public ResponseEntity<File> getFileById(@RequestParam(required = true) @PathVariable("id") String id) {

		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Optional<File> fileData = fileRepository.findById(id);

			if (fileData.isPresent()) {
				return new ResponseEntity<>(fileData.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Upload new File")
	@PostMapping("/file")
	public ResponseEntity<File> uploadFile(@RequestBody File file) {
		try {
			if (file != null && file.getContent() != null && file.getContent().length > 0
					&& file.getContentType() != null && !file.getContentType().isEmpty() && file.getMd5() != null
					&& !file.getMd5().isEmpty() && file.getFileName() != null && !file.getFileName().isEmpty()
					&& file.getPath() != null && !file.getPath().isEmpty() && file.getSize() > 0) {
				Date actualDate = new Date();
				file.setCreateDate(actualDate);
				file.setUpdateDate(actualDate);
				File _file = fileRepository.save(file);
				if (_file != null) {
					return new ResponseEntity<>(_file, HttpStatus.CREATED);
				} else {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Update File by ID")
	@PutMapping("/updateFile")
	public ResponseEntity<File> updateFile(@RequestParam(required = true) @PathVariable("id") String id,
			@RequestBody File file) {

		try {
			if (id == null || id.isEmpty() || file == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

			Optional<File> fileData = fileRepository.findById(id);

			if (fileData.isPresent()) {
				File _File = fileData.get();
				_File.setFileName(file.getFileName());
				_File.setContentType(file.getContentType());
				_File.setSize(file.getSize());
				_File.setMd5(file.getMd5());
				_File.setContent(file.getContent());
				_File.setPath(file.getPath());
				_File.setCreateDate(file.getCreateDate());
				_File.setUpdateDate(new Date());
				File updatedFile = fileRepository.save(_File);
				if (updatedFile != null) {
					return new ResponseEntity<>(fileRepository.save(updatedFile), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Remove File by ID")
	@DeleteMapping("/file")
	public ResponseEntity<HttpStatus> deleteFile(@RequestParam(required = true) @PathVariable("id") String id) {
		try {
			if (id == null || id.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			fileRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}