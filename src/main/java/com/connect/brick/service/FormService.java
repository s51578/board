package com.connect.brick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.brick.model.code.EmailForm;
import com.connect.brick.repository.code.EmailFormRepository;

@Service
public class FormService {

	@Autowired
    private EmailFormRepository emailFormRepository;

	public List<EmailForm> getEmailFormAll() {
		
        return emailFormRepository.findAll();
	}
	
	public EmailForm getEmailFormByNo(Long no) {
		
        return emailFormRepository.findById(no).get();
	}


}
