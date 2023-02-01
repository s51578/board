package com.connect.brick.controller.master;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.transaction.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.connect.brick.component.UploadComponent;
import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.Image;
import com.connect.brick.model.access.Account;
import com.connect.brick.service.AccountService;
import com.connect.brick.service.ImageService;

@Controller
@ComponentScan("com.connect.brick.component")
public class _DropzoneUploadController {
	
	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;
	
	@Autowired
    private AccountService accountService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UploadComponent uploadComponent;
	
	@RequestMapping(value = RequestMappingConstants._DROPZONE_IMAGE_UPLOAD, method = RequestMethod.POST, produces = "application/json; charset=utf8")
	public ResponseEntity imageUploadProcess(Model model, Authentication user,
			MultipartHttpServletRequest request, 
			@RequestParam(name = "uploadImage") MultipartFile file,
			@RequestParam(name = "uuid") String uuid) {
		
		String id = user.getName();
		Account register = accountService.getAccountById(id);

		//String datePath = UploadImageFileUtils.calcDatePath();
	
		String type = "private";
		
		String superFolder = type + File.separatorChar + "master";
		
		String localImagePath = (superFolder + File.separatorChar + uuid).replace(File.separatorChar, '/');
		
		try {
			uploadComponent._reg_image(file, localImagePath, uuid);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("사용할 수 없는 이미지입니다", HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = RequestMappingConstants._DROPZONE_IMAGE_PRELOAD, method = RequestMethod.GET)
	public ResponseEntity imagePreload(Model model, Authentication user,
			@RequestParam(name = "imageNo") Long imageNo) {

		Image image = imageService.findImageByNo(imageNo);
		
		if(image==null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		
		File imageFile = new File(uploadImageLocationPath
				+image.getFilePath() + File.separatorChar + image.getFileName());
		
    	InputStreamResource resource = null;
    	
		try {
			resource = new InputStreamResource(new FileInputStream(imageFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		
		return ResponseEntity.ok()
	            .headers(headers)
	            .contentLength(imageFile.length())
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}
	
	@RequestMapping(value = RequestMappingConstants._DROPZONE_IMAGE_REMOVE, method = RequestMethod.POST)
	public ResponseEntity removeFile(Model model, Authentication user,
			MultipartHttpServletRequest request) {
		
		
	
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	
}