package com.connect.brick.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.brick.model.code.ClassLg;
import com.connect.brick.model.code.MainColor;
import com.connect.brick.model.code.Country;
import com.connect.brick.model.code.SurfaceTexture;
import com.connect.brick.repository.code.ClassLgRepository;
import com.connect.brick.repository.code.MainColorRepository;
import com.connect.brick.repository.code.CountryRepository;
import com.connect.brick.repository.code.SurfaceTextureRepository;

@Service
public class CodeService {

	@Autowired
    private ClassLgRepository classLgRepository;
	
	@Autowired
    private CountryRepository countryRepository;
	
	@Autowired
    private SurfaceTextureRepository surfaceTextureRepository;
	
	@Autowired
    private MainColorRepository mainColorRepository;
	
	@Transactional
	public List<ClassLg> getClassLgAll() {
		return classLgRepository.findAll();
	}
	
	@Transactional
	public ClassLg getClassLgByNo(Long no) {
		return classLgRepository.findById(no).get();
	}
	
	@Transactional
	public List<Country> getCountryAll() {
		return countryRepository.findAll();
	}
	
	@Transactional
	public List<SurfaceTexture> getSurfaceTextureAll() {
		return surfaceTextureRepository.findAll();
	}
	
	@Transactional
	public List<MainColor> getColorToneAll() {
		return mainColorRepository.findAll();
	}
	
	@Transactional
	public Map<String, List> getCodesAll() {
		
		Map<String, List> map = new HashMap<>();
		
		List<ClassLg> cs = classLgRepository.findAll();
		List<Country> cr =  countryRepository.findAll();
		List<SurfaceTexture> st = surfaceTextureRepository.findAll();
		List<MainColor> ct = mainColorRepository.findAll();
		
		map.put("classLgs", cs);
		map.put("countrys", cr);
		map.put("sfTextures", st);
		map.put("mainColors", ct);
		
		return map;
	}
	
}
