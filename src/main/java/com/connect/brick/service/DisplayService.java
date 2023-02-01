package com.connect.brick.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.connect.brick.component.OrderComponent;
import com.connect.brick.model.Contact;
import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.DpPopular;
import com.connect.brick.repository.ContactRepository;
import com.connect.brick.repository.DpMaterialRepository;
import com.connect.brick.repository.DpPopularRepository;

@Service
public class DisplayService {

	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;
	
	@Autowired
    private DpMaterialRepository dpMaterialRepository;
	
	@Autowired
    private DpPopularRepository dpPopularRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	// 페이징
//	@Transactional
//	public Page<Contents> getContentsPageAll(Pageable pageable) {
//		
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
//		
//		pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "regDate"));
//		
//		return contentsRepository.findAll(pageable);
//	}
//	

	@Transactional
	public List<DpMaterial> getDpMaterialOnlyFour() {
		
		return dpMaterialRepository.findAllByOrderByRegDateDesc();
	}

	@Transactional
	public List<DpMaterial> getDpMaterialAll() {
		
		return dpMaterialRepository.findAllByOrderByRegDateDesc();
	}
	
	@Transactional
	public List<DpMaterial> getDpMaterialUpdateSortLimit4() {
		
		return dpMaterialRepository.findTop4ByOrderByRegDateDesc();
	}
	
	@Transactional
	public List<DpMaterial> getDpMaterialSalesPriceSortAll() {
		
		return dpMaterialRepository.findByMaterialMtSalesSalesPriceGreaterThanOrderByMaterialMtSalesSalesPriceDesc(0);
	}
	
	@Transactional
	public List<DpMaterial> getDpMaterialSalesPriceSortLimit4() {
		
		return dpMaterialRepository.findTop4ByOrderByMaterialMtSalesSalesPrice();
	}

	@Transactional
	public List<DpMaterial> getDpMaterialSortAll(String sort, String type) {
		
		if(sort != null && type != null) {
			
			Sort ob_sort = null;
			
			if(sort.equals("name")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("Material.CbName").ascending();
				else
					ob_sort = Sort.by("Material.CbName").descending();
				
				//return dpMaterialRepository.findAll(ob_sort);
				
			} else if(sort.equals("popular")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("DpPopularPopularRank").ascending();
				else
					ob_sort = Sort.by("DpPopularPopularRank").descending();
				
				//return dpMaterialRepository.findAll(ob_sort);
				
			} else if(sort.equals("newest")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("RegDate").ascending();
				else
					ob_sort = Sort.by("RegDate").descending();
				
				//return dpMaterialRepository.findAll(ob_sort);
			
			} else if(sort.equals("price")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("MaterialMtSalesSalesPrice").ascending();
				else
					ob_sort = Sort.by("MaterialMtSalesSalesPrice").descending();
				
				//return dpMaterialRepository.findAll(ob_sort);
				
			}
			
			
			if(ob_sort!=null)
				return dpMaterialRepository.findAll(ob_sort);
			else
				return dpMaterialRepository.findAllByOrderByRegDateDesc();
			
		} else
			return dpMaterialRepository.findAllByOrderByRegDateDesc();
		
	}
	
	@Transactional
	public List<DpMaterial> getDpMaterialAllBySortTag(String sort, String type, 
			List<Long> color, String surface) {
	
	// *****************************************Dont remove : hwang
//		DpMaterial dp = new DpMaterial();
//		Material m = new Material();
//		MaterialSpec ms = new MaterialSpec();
//		MainColor mainColor = new MainColor();
//		
//		ms.setMainColor(mainColor);
//		m.setMtSpec(ms);
//		dp.setMaterial(m);

//		dp.getMaterial().getMtSpec().setSurface(surface);
//		dp.getMaterial().getMtSpec().getMainColor().setNo(color);

//		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
//				.withMatcher("material.cbName", match -> match.contains()).withIgnoreNullValues()
//				.withMatcher("material.mtContents.tags", match -> match.contains()).withIgnoreNullValues();
		
//		Example<DpMaterial> exampleQuery = Example.of(dp, matcher);
		
		if(sort != null && type != null) {
			
			Sort ob_sort = null;
			
			if(sort.equals("name")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("material.cbName").ascending();
				else
					ob_sort = Sort.by("material.cbName").descending();
				
			} else if(sort.equals("popular")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("dpPopular.popularRank").ascending();
				else
					ob_sort = Sort.by("dpPopular.popularRank").descending();
				
			} else if(sort.equals("newest")) {
				
				if(type.equals("asc"))
					ob_sort = Sort.by("regDate").ascending();
				else
					ob_sort = Sort.by("regDate").descending();
				
			} else if(sort.equals("price")) {
				
				if(type.equals("asc")) {
					
					List<DpMaterial> asc = dpMaterialRepository.findJpqlBySurface(surface, ob_sort);
					return OrderComponent.orderBySalesPriceAsc(asc);
					
				} else {
					List<DpMaterial> desc = dpMaterialRepository.findJpqlBySurface(surface, ob_sort);
					return OrderComponent.orderBySalesPriceDesc(desc);
				}
				
			}
			
			
			if(ob_sort!=null)
				return dpMaterialRepository.findJpqlBySurface(surface, ob_sort);
			else
				return dpMaterialRepository.findAllByOrderByRegDateDesc();
			
		} else
			return dpMaterialRepository.findAllByOrderByRegDateDesc();
		
	}
	
	@Transactional
	public DpMaterial getDpMaterialByNo(Long no) {
		
		if(!dpMaterialRepository.findById(no).isPresent())
			return null;
		
		return dpMaterialRepository.findById(no).get();
	}

	@Transactional
	public List<DpMaterial> getMdPickAll() {
		
		return dpMaterialRepository.findAllByMaterialMtContentsMdpickLabelViewTrueOrderByRegDateDesc();
	}
	
	@Transactional
	public List<DpPopular> getPopularAll() {
		
		return dpPopularRepository.findAllByOrderByPopularRank();
	}
	
	@Transactional
	public List<DpPopular> getPopularTop4() {
		
		return dpPopularRepository.findTop4ByOrderByPopularRank();
	}
	
	@Transactional
	public List<DpPopular> getPopularTop8() {
		
		return dpPopularRepository.findTop8ByOrderByPopularRank();
	}
	
	@Transactional
	public DpPopular getPopularByDpMaterial(DpMaterial dm) {
		
		return dpPopularRepository.findByDpMaterial(dm);
	}
	
	@Transactional
	public DpPopular _reg_Popular(DpPopular popular) {
		
		LocalDateTime now = LocalDateTime.now();
		popular.setRegDate(now);
		
		List<DpPopular> list = dpPopularRepository.findAllByOrderByPopularRank();
		
		if(!list.isEmpty()) {
			popular.setPopularRank(list.size()+1);
		} else {
			popular.setPopularRank(1);
		}
		
		return dpPopularRepository.save(popular);
	}
	
	@Transactional
	public void _del_Popular(DpMaterial dp) {
		
		//DpMaterial dm = popular.getDpMaterial();
		
		DpPopular popular = dp.getDpPopular();
		int remove_rank = popular.getPopularRank();
		
		dp.setDpPopular(null);
		
		dpMaterialRepository.save(dp);
		
		List<DpPopular> ranks = dpPopularRepository.findAllByPopularRankGreaterThan(remove_rank);
		if(!ranks.isEmpty()) {
			for (DpPopular pops : ranks) {
				int beforeRanks = pops.getPopularRank();
				pops.setPopularRank(beforeRanks-1);
			}
		}
	}
	
	@Transactional
	public DpPopular getPopularByNo(Long pno) {
		return dpPopularRepository.findById(pno).get();
	}
	
	@Transactional
	public DpPopular getPopularByRank(int rank) {
		return dpPopularRepository.findByPopularRank(rank);
	}
	
	
	@Transactional
	public void _mod_Popular(DpPopular popular, int targetrank) {
		
		int popular_rank = popular.getPopularRank();
		
		popular.setPopularRank(targetrank);
		
		DpPopular recover_popular = getPopularByRank(targetrank);
		recover_popular.setPopularRank(popular_rank);

		dpPopularRepository.save(popular);
		dpPopularRepository.save(recover_popular);
	}
	
	
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
	
}
