package com.connect.brick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.brick.model.Image;
import com.connect.brick.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
    private ImageRepository imageRepository;
	
	@Transactional
	public Image registerImage(Image image) {
		return imageRepository.save(image);
	}

	@Transactional
	public Image findImageByNo(Long no) {
		return imageRepository.findById(no).get();
	}
	
}
