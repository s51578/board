package com.connect.brick.component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.transaction.NotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.connect.brick.model.Image;
import com.connect.brick.service.ImageService;
import com.connect.brick.util.UploadImageFileUtils;

@Component
public class UploadComponent {

	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;
	
	@Autowired
    private ImageService imageService;
	
	public Image _reg_image(MultipartFile file,
			String localImagePath,
			String uuid) throws NotSupportedException, IllegalStateException, IOException {
		
		LocalDateTime now = LocalDateTime.now();
		
		String org_name = file.getOriginalFilename();
		String th_name = "_th_"+org_name;
		
		File folder = new File(uploadImageLocationPath + localImagePath);
		
		if(!folder.exists())
			folder.mkdirs();
		
		File saved = new File(folder.getAbsoluteFile(), file.getOriginalFilename());

		file.transferTo(saved);
		
		String thumbnailSavedPath;
		
		try {
			thumbnailSavedPath = UploadImageFileUtils.uploadThumnailImage(uploadImageLocationPath,
					localImagePath, org_name, th_name);
		} catch (IOException e) {
			throw new NotSupportedException();
		}
		
		File savedThumbnailFile = new File(uploadImageLocationPath + thumbnailSavedPath, th_name);

		Image image = new Image();
		
		image.setUuid(uuid);
		image.setFilePath(localImagePath);
		image.setFileName(org_name);
		image.setFileSize(file.getSize());
		image.setThumbnailName(th_name);
		image.setThumbnailPath(thumbnailSavedPath);
		image.setThumbnailSize(savedThumbnailFile.length());
		image.setCreateDate(now);
		//image.setIsReg(false);
		
		return imageService.registerImage(image);
	}
	
}
