package com.connect.brick.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties.Tomcat.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadImageFileUtils {

	public static String uploadOriginalImage(String imageLocationPath, 
			String savedPath, String savedName, MultipartFile imageFile) throws IOException {
		System.out.println(imageLocationPath);
		//저장할 파일준비
		File target = new File(imageLocationPath + savedPath, savedName);
		
		imageFile.transferTo(target);
		
		return savedPath.replace(File.separatorChar, '/');
	}

	public static String uploadThumnailImage(String imageLocationPath, 
			String savedPath, String savedOrgName, String thumbnailName) throws IOException {
		
		File orgFile = new File(imageLocationPath + savedPath, savedOrgName);
		
		BufferedImage sourceImg = ImageIO.read(orgFile);
		
		BufferedImage destImg = Scalr.resize(sourceImg, 2000);//Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 400);
		String thumbnailSavedPath = savedPath + File.separatorChar + "thumbnail";
		
		File thumbnailFolder = new File(imageLocationPath + thumbnailSavedPath);
		
		if(!thumbnailFolder.exists())
			thumbnailFolder.mkdir();
		
		File thumbnailFile = new File(imageLocationPath + thumbnailSavedPath, thumbnailName);
		String formatName = savedOrgName.substring(savedOrgName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), thumbnailFile);
		
		//uploadedFileName는 썸네일명으로 화면에 전달된다.
		return thumbnailSavedPath.replace(File.separatorChar, '/');
	}
	
	public static String uploadOriginalFile(String uploadPath, String savedName, MultipartFile imageFile) throws IOException {
		
		//파일을 저장할 폴더 생성(년 월 일 기준)
		String dateFolderPath = calcPath(uploadPath);
		String savedPath = uploadPath + dateFolderPath;
		
		File path = new File(savedPath);

		if(!path.exists())
			path.mkdirs();
		
		//저장할 파일준비
		File target = new File(savedPath, savedName);

		imageFile.transferTo(target);
		
		return savedPath;
	}
	
	public static String uploadThumnailFile(String savedPath, String savedOrgName, String thumbnailName) throws IOException {
		System.out.println(savedPath);
		BufferedImage sourceImg = ImageIO.read(new File(savedPath, savedOrgName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 400);
		String thumbnailSavedPath = savedPath + File.separatorChar + "thumbnail";
		
		File thumbnailFolder = new File(thumbnailSavedPath);
		
		if(!thumbnailFolder.exists())
			thumbnailFolder.mkdir();
		
		File thumbnailFile = new File(thumbnailSavedPath, thumbnailName);
		String formatName = savedOrgName.substring(savedOrgName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), thumbnailFile);
		
		//uploadedFileName는 썸네일명으로 화면에 전달된다.
		return thumbnailSavedPath;
	}
	
	public static String calcDatePath() {
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		return datePath;
	}//calcPath
	
	public static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}//calcPath
	
	//폴더 생성 함수
	public static boolean makeDir(String uploadPath, String... paths) {
		
		if(new File(uploadPath + paths[paths.length -1]).exists()) {
			return true;
		}//if
		
		try {
			
			for(String path : paths) {
				
				File dirPath = new File(uploadPath + path);
				
				if(!dirPath.exists()) {
					dirPath.mkdir();
				}//if
				
			}//for
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}//makeDir
	
	public static boolean removeImageFile(String path, String fileName) {
		
		
		
		
		return true;
	}
	
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//썸네일 이미지 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, 500);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		
		//겹쳐지지 않는 파일명을 위한 유니크한 값 생성
		UUID uid = UUID.randomUUID();
		
		//원본파일 이름과 UUID 결합
		String savedName = uid.toString() + "_" + originalName;
		
		//파일을 저장할 폴더 생성(년 월 일 기준)
		String savedPath = calcPath(uploadPath);
		
		//저장할 파일준비
		File target = new File(uploadPath + savedPath, savedName);
		
		//파일을 저장
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		
		String uploadedFileName = null;
		
		//파일의 확장자에 따라 썸네일(이미지일경우) 또는 아이콘을 생성함.
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		//uploadedFileName는 썸네일명으로 화면에 전달된다.
		return uploadedFileName;
	}//
	
}
