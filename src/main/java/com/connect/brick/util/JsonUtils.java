package com.connect.brick.util;

import java.io.File;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.beans.factory.annotation.Value;

import com.connect.brick.model.Image;
import com.connect.brick.model.Material;
import com.connect.brick.model.SubMaterial;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtils {

	@Value("${upload.image.locations.path}")
	private static String uploadImageLocationPath;
	
	
	/* 이미지
	 *  name: "myimage.jpg", 
        size: 12345, 
        type: 'image/jpeg', 
        status: Dropzone.ADDED, 
        url: thumbnailUrls[i] 
        
	 * */
	public static ArrayNode makeFileInformation(Material material) {

		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();

		List<Image> images = material.getImages();
		
		Image main = material.getMainImage();
		
		for (Image image : images) {

			File imageFile = new File(
					uploadImageLocationPath + image.getFilePath() + File.separatorChar + image.getFileName());

			JsonNode objectNode = mapper.createObjectNode();

			((ObjectNode) objectNode).put("no", image.getNo());
			((ObjectNode) objectNode).put("name", image.getFileName());
			((ObjectNode) objectNode).put("uuid", image.getUuid());
			((ObjectNode) objectNode).put("size", image.getFileSize());
			
			if(main.getNo().equals(image.getNo()))
				((ObjectNode) objectNode).put("main", true);
			else
				((ObjectNode) objectNode).put("main", false);
			
			((ObjectNode) objectNode).put("type", new MimetypesFileTypeMap().getContentType(imageFile));

			arrayNode.add(objectNode);
		}

		return arrayNode;
	}
	
	public static ArrayNode makeFileInformation(SubMaterial submaterial) {

		ObjectMapper mapper = new ObjectMapper();

		ArrayNode arrayNode = mapper.createArrayNode();

		List<Image> images = submaterial.getImages();
		
		Image main = submaterial.getMainImage();
		
		for (Image image : images) {

			File imageFile = new File(
					uploadImageLocationPath + image.getFilePath() + File.separatorChar + image.getFileName());

			JsonNode objectNode = mapper.createObjectNode();

			((ObjectNode) objectNode).put("no", image.getNo());
			((ObjectNode) objectNode).put("name", image.getFileName());
			((ObjectNode) objectNode).put("uuid", image.getUuid());
			((ObjectNode) objectNode).put("size", image.getFileSize());
			
			if(main.getNo().equals(image.getNo()))
				((ObjectNode) objectNode).put("main", true);
			else
				((ObjectNode) objectNode).put("main", false);
			
			((ObjectNode) objectNode).put("type", new MimetypesFileTypeMap().getContentType(imageFile));

			arrayNode.add(objectNode);
		}

		return arrayNode;
	}
	
}
