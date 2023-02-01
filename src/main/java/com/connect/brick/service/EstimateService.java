package com.connect.brick.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.connect.brick.model.Contact;
import com.connect.brick.model.CostSheet;
import com.connect.brick.model.Estimate;
import com.connect.brick.model.EstimateDoc;
import com.connect.brick.model.EstimateSub;
import com.connect.brick.repository.ContactRepository;
import com.connect.brick.repository.CostSheetRepository;
import com.connect.brick.repository.EstimateDocRepository;
import com.connect.brick.repository.EstimateRepository;

@Service
public class EstimateService {

	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;

	@Autowired
    private EstimateRepository estimateRepository;
	
	@Autowired
    private EstimateDocRepository estimateDocRepository;
	
	@Autowired
    private CostSheetRepository costSheetRepository;

	@Autowired
    private ContactRepository contactRepository;

	@Transactional
	public List<Contact> getContactAll() {
		
		return contactRepository.findAllByOrderByContactDateDesc();
	}
	
	@Transactional
	public Contact getContactByNo(Long no) {
		return contactRepository.findById(no).get();
	}
	
	@Transactional
	public Contact _reg_Contact(Contact contact) {
		
		LocalDateTime now = LocalDateTime.now();
		contact.setContactDate(now);
		
		return contactRepository.save(contact);
	}
	
	@Transactional
	public List<CostSheet> getCostSheetAll() {
		return costSheetRepository.findAll();
	}
	
	@Transactional
	public CostSheet getCostSheetByNo(Long no) {
		return costSheetRepository.findById(no).get();
	}
	
	@Transactional
	public CostSheet getCostSheetByActive(boolean isActive) {
		return costSheetRepository.findByIsActive(isActive);
	}

	@Transactional
	public List<Estimate> getEstimateAll() {
		
		return estimateRepository.findAll();
	}
	
	@Transactional
	public Estimate getEstimateByNo(Long no) {
		
		return estimateRepository.findById(no).get();
	}
	
	@Transactional
	public Estimate _reg_estimate(Estimate estimate) {
		for (EstimateSub sub : estimate.getEstimateSubs()) {
			sub.setEstimate(estimate);
		}
		LocalDateTime now = LocalDateTime.now();
		estimate.setEstimateDate(now);
		return estimateRepository.save(estimate);
	}
	
	@Transactional
	public boolean isEstimateDocByNo(Long no) {
		return estimateDocRepository.existsById(no);
	}
	
	@Transactional
	public EstimateDoc getEstimateDocByNo(Long no) {
		return estimateDocRepository.findById(no).get();
	}
	
	@Transactional
	public EstimateDoc _reg_EstimateDoc_State(EstimateDoc doc, Integer state) {
		
		doc.setState(state);
		
		return estimateDocRepository.save(doc);
	}

	@Transactional
	public boolean _del_order_estimate(Estimate estimate) {

		estimate.setOrder(null);
		estimateRepository.save(estimate);
		
		estimateRepository.delete(estimate);
		
		return true;
	}
}
