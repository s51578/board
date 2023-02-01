package com.connect.brick.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.Image;
import com.connect.brick.model.Material;
import com.connect.brick.model.SubMaterial;
import com.connect.brick.model.Tag;
import com.connect.brick.model.code.ClassLg;
import com.connect.brick.model.material.MaterialColor;
import com.connect.brick.model.material.MaterialContents;
import com.connect.brick.repository.ImageRepository;
import com.connect.brick.repository.MaterialContentsRepository;
import com.connect.brick.repository.MaterialRepository;
import com.connect.brick.repository.SubMaterialRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialService {

	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;
	
	@Autowired
    private MaterialRepository materialRepository;
	
	@Autowired
    private MaterialContentsRepository materialContentsRepository;
	
	@Autowired
    private SubMaterialRepository subMaterialRepository;
	
	@Autowired
    private ImageRepository imageRepository;
	

	// 페이징
//	@Transactional
//	public Page<Material> getPageMaterialAll(Pageable pageable) {
//		
//		int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
//		pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "regDate"));
//		
//		return materialRepository.findAll(pageable);
//	}
	
	@Transactional
	public List<Material> getMaterialAll() {
		
		return materialRepository.findAllByOrderByRegDateDesc();
	}
	
	@Transactional
	public Material getMaterialByNo(Long no) {
		return materialRepository.findMaterialByNo(no);
	}
	
	@Transactional
	public MaterialContents getMaterialContentsByNo(Long no) {
		
		return materialContentsRepository.findById(no).get();
	}
	
	@Transactional
	public void _del_Material(Long no) {
		
		List<Image> images = getMaterialByNo(no).getImages();
		
		for (Image image : images) {
			File org_file = new File(uploadImageLocationPath + image.getFilePath(), image.getFileName());
			File th_ = new File(uploadImageLocationPath + image.getThumbnailPath(), image.getThumbnailName());

			org_file.delete();
			th_.delete();
		}
		
		materialRepository.deleteById(no);
	}
	
	@Transactional
	public Material _reg_Material(Material material, boolean isOpen) {
	
		LocalDateTime now = LocalDateTime.now();
		
		material.setRegDate(now);
		material.setModDate(now);
		
		//노출
		if(isOpen) {
			DpMaterial dm = new DpMaterial();
			dm.setMaterial(material);
			dm.setRegDate(now);
			
			material.setDpMaterial(dm);
		}

		//색상추출
		for (MaterialColor mc : material.getMtColors()) {
			mc.setMaterial(material);
		}
		
		//검색어
		List<Tag> tags = material.getMtContents().getMtTags();
		
		if(tags!=null) {
			Iterator<Tag> iter = tags.iterator();
			
			while (iter.hasNext()) {
				Tag tag = iter.next();
				if(tag.getTagName()==null || tag.getTagName().equals("") || tag.getTagName()=="")
					iter.remove();
			}
			
		}
		
		//자식 등록
		material.getMtClass().setMaterial(material);
		material.getMtDtd().setMaterial(material);
		material.getMtPf().setMaterial(material);
		material.getMtPkg().setMaterial(material);
		material.getMtSales().setMaterial(material);
		material.getMtSpace().setMaterial(material);
		material.getMtSpec().setMaterial(material);
		material.getMtStd().setMaterial(material);
		material.getMtDelv().setMaterial(material);
		material.getMtContents().setMaterial(material);
		
		material.getMtContents().setHit(0);
		
		//메인 자재 이미지
		Long mimgno = material.getMainImage().getNo();
		
		if(mimgno!=null) {
			Image load = imageRepository.findById(mimgno).get();
			material.setMainImage(load);
		} else
			material.setMainImage(null);
		
		//메인 조감도 이미지
		Long vimgno = material.getViewImage().getNo();
		
		if(vimgno!=null) {
			Image load = imageRepository.findById(vimgno).get();
			material.setViewImage(load);
		} else
			material.setViewImage(null);
		
		//상세 이미지
		List<Image> imgs = new ArrayList<>();
		
		if(material.getImages()!=null)
			for (Image image : material.getImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					imgs.add(load);
				}
			}
		
		material.setImages(imgs);
		
		//가상 조감도 이미지
		List<Image> vrViewImgs = new ArrayList<>();
		
		if(material.getVrViewImages()!=null)
			for (Image image : material.getVrViewImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					vrViewImgs.add(load);
				}
			}
		
		material.setVrViewImages(vrViewImgs);
		
		//가상 제품 이미지
		List<Image> vrProductImgs = new ArrayList<>();
		
		if(material.getVrProductImages()!=null)
			for (Image image : material.getVrProductImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					vrProductImgs.add(load);
				}
			}
		
		material.setVrProductImages(vrProductImgs);
		
		//실제 제품 이미지
		List<Image> realProductImgs = new ArrayList<>();
		
		if(material.getRealProductImages()!=null)
			for (Image image : material.getRealProductImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					realProductImgs.add(load);
				}
			}
		
		material.setRealProductImages(realProductImgs);
		
		//실제 시공 사례 이미지
		List<Image> realConsImgs = new ArrayList<>();
		
		if(material.getRealConsImages()!=null)
			for (Image image : material.getRealConsImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					realConsImgs.add(load);
				}
			}
		
		material.setRealConsImages(realConsImgs);
		
		//실제 시공 링크
		Long linkImgNo = material.getLinkImage().getNo();
		
		if(linkImgNo!=null) {
			Image load = imageRepository.findById(linkImgNo).get();
			load.setCaption(material.getLinkImage().getCaption());
			load.setLinkAddr(material.getLinkImage().getLinkAddr());
			material.setLinkImage(load);
		} else
			material.setLinkImage(null);
		
		return materialRepository.save(material);
	}
	
	@Transactional
	public Material _mod_Material(Material material, boolean isOpen) {
		
		LocalDateTime now = LocalDateTime.now();

		material.setModDate(now);
		material.setModDate(now);
		
		//수정 시 히트 전달 -> 향후 DTO로 개선
		Material m = materialRepository.findById(material.getNo()).get();
		material.getMtContents().setHit(m.getMtContents().getHit());
		
		//노출
		if(isOpen) {
			
			DpMaterial open = m.getDpMaterial();
			
			if(open!=null) {
				material.setDpMaterial(open);
			} else {
				
				DpMaterial dm = new DpMaterial();
				dm.setMaterial(material);
				dm.setRegDate(now);
				
				material.setDpMaterial(dm);
				
			}
			
		} else {
			material.setDpMaterial(null);
		}

		//색상추출
		for (MaterialColor mc : material.getMtColors()) {
			mc.setMaterial(material);
		}
		
		//검색어
		List<Tag> tags = material.getMtContents().getMtTags();
		
		if(tags!=null) {
			Iterator<Tag> iter = tags.iterator();
			String tagsString = "";
			while (iter.hasNext()) {
				Tag tag = iter.next();
				if(tag.getTagName()==null || tag.getTagName().equals("") || tag.getTagName()=="") {
					iter.remove();
					continue;
				}
				
				tagsString += tag.getTagName()+",";
			}
			material.getMtContents().setTags(tagsString);
		}
		
		//메인 자재 이미지
		Long mimgno = material.getMainImage().getNo();
		
		if(mimgno!=null) {
			Image load = imageRepository.findById(mimgno).get();
			material.setMainImage(load);
		}
		
		//메인 조감도 이미지
		Long vimgno = material.getViewImage().getNo();
		
		if(vimgno!=null) {
			Image load = imageRepository.findById(vimgno).get();
			material.setViewImage(load);
		} else
			material.setViewImage(null);
		
		//상세 이미지
		List<Image> imgs = new ArrayList<>();
		
		if(material.getImages()!=null)
			for (Image image : material.getImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					imgs.add(load);
				}
			}
		
		material.setImages(imgs);
		
		//가상 조감도 이미지
		List<Image> vrViewImgs = new ArrayList<>();
		
		if(material.getVrViewImages()!=null)
			for (Image image : material.getVrViewImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					vrViewImgs.add(load);
				}
			}
		
		material.setVrViewImages(vrViewImgs);
		
		//가상 제품 이미지
		List<Image> vrProductImgs = new ArrayList<>();
		
		if(material.getVrProductImages()!=null)
			for (Image image : material.getVrProductImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					vrProductImgs.add(load);
				}
			}
		
		material.setVrProductImages(vrProductImgs);
		
		//실제 제품 이미지
		List<Image> realProductImgs = new ArrayList<>();
		
		if(material.getRealProductImages()!=null)
			for (Image image : material.getRealProductImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					realProductImgs.add(load);
				}
			}
		
		material.setRealProductImages(realProductImgs);
		
		//실제 시공 사례 이미지
		List<Image> realConsImgs = new ArrayList<>();
		
		if(material.getRealConsImages()!=null)
			for (Image image : material.getRealConsImages()) {
				if(image.getNo()!=null) {
					Image load = imageRepository.findById(image.getNo()).get();
					load.setCaption(image.getCaption());
					load.setLinkAddr(image.getLinkAddr());
					realConsImgs.add(load);
				}
			}
		
		material.setRealConsImages(realConsImgs);
		
		//실제 시공 링크
		Long linkImgNo = material.getLinkImage().getNo();
		
		if(linkImgNo!=null) {
			Image load = imageRepository.findById(linkImgNo).get();
			load.setCaption(material.getLinkImage().getCaption());
			load.setLinkAddr(material.getLinkImage().getLinkAddr());
			material.setLinkImage(load);
		} else
			material.setLinkImage(null);
		
		return materialRepository.save(material);
	}
	
	@Transactional
	public Material _reg_Material_yet(Material material, String filesInfo) throws JsonParseException, JsonMappingException, IOException {
		
		LocalDateTime now = LocalDateTime.now();
		
		material.setRegDate(now);
		material.setModDate(now);

		material.getMtClass().setMaterial(material);
		material.getMtDtd().setMaterial(material);
		material.getMtPf().setMaterial(material);
		material.getMtPkg().setMaterial(material);
		material.getMtSales().setMaterial(material);
		material.getMtSpace().setMaterial(material);
		material.getMtSpec().setMaterial(material);
		material.getMtStd().setMaterial(material);

		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> filesArray = mapper.readValue(filesInfo,
				new TypeReference<List<Map<String, Object>>>() {
				});

		if(filesArray.isEmpty() || filesArray.size()<1)
			return null;
		
		List<Image> images = new ArrayList<Image>();
		//3. 이미지 등록
		//Set<Image> images = new HashSet<Image>();
		for (Map<String, Object> fileInfo : filesArray) {
			
			String uuid = fileInfo.get("uuid").toString();
			
			Image image = imageRepository.findByUuid(uuid);
			
			//image.setMaterial(material);
			//image.setIsReg(true);

			String ckmain = fileInfo.get("main").toString();
			if(ckmain.equals("true"))
				material.setMainImage(image);
			
			images.add(image);
		}

		material.setImages(images);
		
		return materialRepository.save(material);
	}

	@Transactional
	public Material _mod_Material_yet(Material material, String filesInfo) throws JsonParseException, JsonMappingException, IOException {
		
		LocalDateTime now = LocalDateTime.now();
		
		Long yet = material.getNo();
		
		Material yetMaterial = getMaterialByNo(yet);
		
		yetMaterial.setImages(null);
		yetMaterial.setMainImage(null);
		
//		Contents yetContent = yetMaterial.getContents();
//		
//		material.setContents(yetContent);
		
		/*
		 * List<Image> yetimages = yetMaterial.getImages();
		 * 
		 * for (Image image : yetimages) {
		 * 
		 * image.setMaterial(null); image.setIsMain(false); image.setIsReg(false);
		 * 
		 * imageRepository.save(image); }
		 */
		
		material.setRegDate(yetMaterial.getRegDate());
		
		material.getMtClass().setMaterial(material);
		material.getMtDtd().setMaterial(material);
		material.getMtPf().setMaterial(material);
		material.getMtPkg().setMaterial(material);
		material.getMtSales().setMaterial(material);
		material.getMtSpace().setMaterial(material);
		material.getMtSpec().setMaterial(material);
		material.getMtStd().setMaterial(material);

		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> filesArray = mapper.readValue(filesInfo,
				new TypeReference<List<Map<String, Object>>>() {
				});

		if(filesArray.isEmpty() || filesArray.size()<1)
			return null;
		
		List<Image> images = new ArrayList<Image>();
		//3. 이미지 등록
		//Set<Image> images = new HashSet<Image>();
		for (Map<String, Object> fileInfo : filesArray) {
			
			String uuid = fileInfo.get("uuid").toString();
			
			Image image = imageRepository.findByUuid(uuid);
			
			//image.setMaterial(material);
			//image.setIsReg(true);

			String ckmain = fileInfo.get("main").toString();
			if(ckmain.equals("true"))
				material.setMainImage(image);
			
			images.add(image);
		}

		material.setImages(images);
		
		return materialRepository.save(material);
	}
	
	@Transactional
	public List<SubMaterial> getSubMaterialAll() {
		
		return subMaterialRepository.findAllByOrderByRegDateDesc();
	}
	
	@Transactional
	public SubMaterial getSubMaterialByNo(Long no) {
		return subMaterialRepository.findSubMaterialByNo(no);
	}
	
	@Transactional
	public List<SubMaterial> getSubMaterialByClassLg(ClassLg cl) {
		return subMaterialRepository.findSubMaterialByClassLg(cl);
	}
	
	@Transactional
	public void _del_SubMaterial(Long no) {
		
		List<Image> images = getSubMaterialByNo(no).getImages();
		
		for (Image image : images) {
			File org_file = new File(uploadImageLocationPath + image.getFilePath(), image.getFileName());
			File th_ = new File(uploadImageLocationPath + image.getThumbnailPath(), image.getThumbnailName());

			org_file.delete();
			th_.delete();
		}
		
		subMaterialRepository.deleteById(no);
	}
	
	@Transactional
	public SubMaterial _reg_SubMaterial(SubMaterial sub, String filesInfo) throws JsonParseException, JsonMappingException, IOException {
		
		LocalDateTime now = LocalDateTime.now();
		
		sub.setRegDate(now);
		
		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> filesArray = mapper.readValue(filesInfo,
				new TypeReference<List<Map<String, Object>>>() {
				});

		if(filesArray.isEmpty() || filesArray.size()<1)
			return null;
		
		List<Image> images = new ArrayList<Image>();
		//3. 이미지 등록
		//Set<Image> images = new HashSet<Image>();
		for (Map<String, Object> fileInfo : filesArray) {
			
			String uuid = fileInfo.get("uuid").toString();
			
			Image image = imageRepository.findByUuid(uuid);
			
			//image.setMaterial(material);
			//image.setIsReg(true);

			String ckmain = fileInfo.get("main").toString();
			if(ckmain.equals("true"))
				sub.setMainImage(image);
			
			images.add(image);
		}

		sub.setImages(images);
		
		return subMaterialRepository.save(sub);
	}
	
	@Transactional
	public SubMaterial _mod_SubMaterial(SubMaterial sub, String filesInfo) throws JsonParseException, JsonMappingException, IOException {
		
		Long yetNo = sub.getNo();
		
		SubMaterial yet = getSubMaterialByNo(yetNo);
		
		yet.setImages(null);
		yet.setMainImage(null);
		
		sub.setRegDate(yet.getRegDate());
		
		ObjectMapper mapper = new ObjectMapper();

		List<Map<String, Object>> filesArray = mapper.readValue(filesInfo,
				new TypeReference<List<Map<String, Object>>>() {
				});

		if(filesArray.isEmpty() || filesArray.size()<1)
			return null;
		
		List<Image> images = new ArrayList<Image>();
		//3. 이미지 등록
		//Set<Image> images = new HashSet<Image>();
		for (Map<String, Object> fileInfo : filesArray) {
			
			String uuid = fileInfo.get("uuid").toString();
			
			Image image = imageRepository.findByUuid(uuid);
			
			//image.setMaterial(material);
			//image.setIsReg(true);

			String ckmain = fileInfo.get("main").toString();
			if(ckmain.equals("true"))
				sub.setMainImage(image);
			
			images.add(image);
		}

		sub.setImages(images);
		
		return subMaterialRepository.save(sub);
	}

	@Transactional
	public MaterialContents updateHit(MaterialContents mc) {
		
		int hit = mc.getHit();
		mc.setHit(hit+1);
		
		return materialContentsRepository.save(mc);
	}
	
	@Transactional
	public List<MaterialContents> mtRank4() {
		return materialContentsRepository.findByEstimateTileRankBetweenOrderByEstimateTileRank(1,4);
	}
	
}
