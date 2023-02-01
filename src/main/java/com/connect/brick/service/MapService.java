package com.connect.brick.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.brick.model.code.EmailForm;
import com.connect.brick.model.data.ApartmentAll;
import com.connect.brick.repository.MapRepository;
import com.connect.brick.repository.code.EmailFormRepository;

@Service
public class MapService {

	@Autowired
    private MapRepository mapRepository;
	
	@Transactional
	public List<ApartmentAll> getAptFromAll() {
        return mapRepository.findAll();
	}
	

}
