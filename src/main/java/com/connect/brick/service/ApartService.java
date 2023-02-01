package com.connect.brick.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connect.brick.model.code.AptExc;
import com.connect.brick.model.data.ApartmentAll;
import com.connect.brick.model.data.ApartmentSu;
import com.connect.brick.model.data.Region;
import com.connect.brick.model.data.RegionSd;
import com.connect.brick.model.data.RegionSg;
import com.connect.brick.model.dto._ApartmentDTO;
import com.connect.brick.repository.RegionRepository;
import com.connect.brick.repository.RegionSdRepository;
import com.connect.brick.repository.RegionSgRepository;
import com.connect.brick.repository.code.AptExcRepository;
import com.connect.brick.repository.data.ApartmentAllRepository;
import com.connect.brick.repository.data.ApartmentEtcRepository;
import com.connect.brick.repository.data.ApartmentIchRepository;
import com.connect.brick.repository.data.ApartmentKkdRepository;
import com.connect.brick.repository.data.ApartmentSuRepository;

import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;


@Service
public class ApartService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
    private AptExcRepository aptExcRepository;
	
	@Autowired
    private ApartmentAllRepository apartmentAllRepository;
	
	@Autowired
    private ApartmentSuRepository apartmentSuRepository;
	
	@Autowired
    private ApartmentKkdRepository apartmentKkdRepository;
	
	@Autowired
    private ApartmentIchRepository apartmentIchRepository;
	
	@Autowired
    private ApartmentEtcRepository apartmentEtcRepository;
	
	@Autowired
    private RegionRepository regionRepository;
	
	@Autowired
    private RegionSdRepository regionSdRepository;
	
	@Autowired
    private RegionSgRepository regionSgRepository;
	
	@Transactional
	public List<AptExc> getAptExcAll() {
		return aptExcRepository.findAll();
	}
	
	@Transactional
	public List<ApartmentAll> searchApartmentByString(String string) {
		
		
		return apartmentAllRepository.findByNameApartContaining(string);
	}
	
	@Transactional
	public List<Region> getRegionAddrSdAll() {
		return regionRepository.findAll();
	}
	
	@Transactional
	public List<RegionSd> getRegionSdAll() {
		return regionSdRepository.findAll();
	}
	
	@Transactional
	public List<RegionSd> getRegionSdAllBySort() {
		return regionSdRepository.findAllByOrderBySortNumAsc();
	}
	
	@Transactional
	public RegionSd getRegionSd(String region) {
		return regionSdRepository.findById(region).get();
	}
	
	@Transactional
	public List<RegionSg> getRegionSgAll() {
		return regionSgRepository.findAll();
	}
	
	@Transactional
	public _ApartmentDTO getApartByNo(String region, Long no) {
		
		if(region.equals("11"))
			return modelMapper.map(
					apartmentSuRepository.findByNo(no),
					new TypeToken<_ApartmentDTO>() {}.getType());
		else if (region.equals("28"))
			return modelMapper.map(
					apartmentIchRepository.findByNo(no),
					new TypeToken<_ApartmentDTO>() {}.getType());
		else if (region.equals("41"))
			return modelMapper.map(
					apartmentKkdRepository.findByNo(no),
					new TypeToken<_ApartmentDTO>() {}.getType());
		else
			return modelMapper.map(
					apartmentEtcRepository.findByNo(no),
					new TypeToken<_ApartmentDTO>() {}.getType());
		
	}
	
	@Transactional
	public List<_ApartmentDTO> getApartByName(String region, String aptName, String aptEmd, String aptJb) {
		
		if(region.equals("11"))
			return modelMapper.map(
					apartmentSuRepository.findDtoByNameApartAndAddrEmdAndAddrJb(aptName, aptEmd, aptJb),
					new TypeToken<List<_ApartmentDTO>>() {}.getType());
		else if (region.equals("28"))
			return modelMapper.map(
					apartmentIchRepository.findDtoByNameApartAndAddrEmdAndAddrJb(aptName, aptEmd, aptJb),
					new TypeToken<List<_ApartmentDTO>>() {}.getType());
		else if (region.equals("41"))
			return modelMapper.map(
					apartmentKkdRepository.findDtoByNameApartAndAddrEmdAndAddrJb(aptName, aptEmd, aptJb),
					new TypeToken<List<_ApartmentDTO>>() {}.getType());
		else
			return modelMapper.map(
					apartmentEtcRepository.findDtoByNameApartAndAddrEmdAndAddrJb(aptName, aptEmd, aptJb),
					new TypeToken<List<_ApartmentDTO>>() {}.getType());
		
	}
//	
//	@Transactional
//	public List<_ApartmentDTO> getApartBySearch(String region, String search) {
//
//		String search_for_name_apart = search.replace(" ", "%");
//		String search_for_natural = search.replace("", "%");
//		String region_name = getRegionSd(region).getAddrSdSearchName();
//		
//		System.out.println("#########################");
//		System.out.println(search_for_name_apart);
//		System.out.println("#########################");
//		System.out.println(search_for_natural);
//		System.out.println("#########################");
//		
//		if(region.equals("11"))
//			 return modelMapper.map(
//					 apartmentSuRepository.findApartmentBySearch(search_for_name_apart, search_for_natural), 
//					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
//		else if(region.equals("28"))
//			return modelMapper.map(
//					apartmentIchRepository.findApartmentBySearch(search_for_name_apart, search_for_natural), 
//					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
//		else if(region.equals("41"))
//			return modelMapper.map(
//					apartmentKkdRepository.findApartmentBySearch(search_for_name_apart, search_for_natural), 
//					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
//		else
//			return modelMapper.map(
//					apartmentEtcRepository.findApartmentBySearch(region_name, search_for_name_apart, search_for_natural), 
//					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
//	}

	@Transactional
	public List<_ApartmentDTO> getResultBySearch(String region, String search, List<Token> tokenList, List<String> morphMustList) {

		String region_name = getRegionSd(region).getAddrSdSearchName(); // RegionDataConstant.changeAddrSdForSearch(addrSd);


		long beforeTokenTime = System.currentTimeMillis();
		//////////////////////////////////////////////////////////
		
		List<AptExc> excs = aptExcRepository.findAll();
		Iterator iter = morphMustList.iterator();
		
		while (iter.hasNext()) {
			
			String word = (String) iter.next();

			//boolean check = apartExcRepository.existsByExcName(word);
			boolean exc_check = false;
			
			for (AptExc aptExc : excs) {
				if(word.equals(aptExc.getExcName()))
					exc_check = true;
			}
			
			if(exc_check)
				iter.remove();
			
		}

		long afterTokenTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffTokenTime = (afterTokenTime - beforeTokenTime);
		System.out.println("############################################################################");
		System.out.println("EXC 분석 시간 : " + secDiffTokenTime);
		System.out.println("############################################################################");
		
		List<_ApartmentDTO> result = null;
		
		long beforeDBSearchTime = System.currentTimeMillis();
		//////////////////////////////////////////////////////////
		
		if(region.equals("11"))
			result = modelMapper.map(
					 apartmentSuRepository.findBySearch(tokenList, morphMustList, search), 
					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
		else if(region.equals("28"))
			result =  modelMapper.map(
					apartmentIchRepository.findBySearch(tokenList, morphMustList, search),
					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
		else if(region.equals("41"))
			result =  modelMapper.map(
					apartmentKkdRepository.findBySearch(tokenList, morphMustList, search),
					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
		else
			result =  modelMapper.map(
					apartmentEtcRepository.findBySearch(tokenList, morphMustList, search, region_name),
					 new TypeToken<List<_ApartmentDTO>>(){}.getType());
		
		//List<ApartmentSu> sus = apartmentSuRepository.findBySearch(tokenList, morphMustList, search);
		long afterDBSearchTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
		long secDiffDBSearchTime = (afterDBSearchTime - beforeDBSearchTime);
		System.out.println("############################################################################");
		System.out.println("DB 서치 분석 시간 : " + secDiffDBSearchTime);
		System.out.println("############################################################################");
		
		return result;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<ApartmentSu> getResultBySearch(String region, String search) {
//		
//		String[] searchs = search.split(" ");
//		
//		apartmentSuRepository.findAll();
//		
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
//				.forEntity(ApartmentSu.class).get();
//		
//		Query query = queryBuilder.keyword().onFields(
//				"addrSd", "addrSg", "addrGu", "addrEmd", "addrJb", "nameApart"
//				).matching("*"+search+"*").createQuery();
//		
////		Query query = queryBuilder.simpleQueryString().onFields(
////				"addrSd", "addrSg", "addrGu", "addrEmd", "addrJb", "nameApart"
////				).matching("\""+ search +"\"").createQuery();
//		
//		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, ApartmentSu.class);
//		List<ApartmentSu> aparts = (List<ApartmentSu>) fullTextQuery.getResultList();
//		
//		return aparts;
//
//	}
//	
//	public void buildSearchIndex() throws InterruptedException {
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//		fullTextEntityManager.createIndexer().startAndWait();
//	}

}
