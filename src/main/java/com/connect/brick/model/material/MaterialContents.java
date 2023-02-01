package com.connect.brick.model.material;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.connect.brick.model.Material;
import com.connect.brick.model.Tag;
import com.connect.brick.util.TimeUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "_MATERIAL_CONTENTS")
public class MaterialContents {

	public MaterialContents() {
		this.monthDealLabelView = false;
		this.productLabelView = false;
		this.mdpickLabelView = false;
		this.hit = 0;
	}
	
	public MaterialContents(Boolean monthDealLabelView, 
			Boolean productLabelView, 
			Boolean mdpickLabelView,
			Integer hit) {
		super();
		this.monthDealLabelView = monthDealLabelView;
		this.productLabelView = productLabelView;
		this.mdpickLabelView = mdpickLabelView;
		this.hit = hit;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "material_no", updatable=false)
	private Material material;

//	@OneToMany
//	@JoinTable(name = "CONTENTS_HAS_SUB_MATERIAL", 
//	joinColumns = @JoinColumn(name = "contents_no"), 
//	inverseJoinColumns = @JoinColumn(name = "sub_no"))
//	private List<SubMaterial> subs;

	@Column(name = "month_deal_label_view")
	private Boolean monthDealLabelView;

	@Column(name = "product_label_view")
	private Boolean productLabelView;

	@Column(name = "mdpick_label_view")
	private Boolean mdpickLabelView;
	
	@Column(name = "estimate_tile_rank")
	private Integer estimateTileRank = 0;

	@Column(name = "video_url")
	private String videoUrl;
	
	@Column(name = "hit")
	private Integer hit;

	//폐기
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "RL_MATERIAL_CONTENTS_HAS_TAGS", 
	joinColumns = @JoinColumn(name = "contents_no"), 
	inverseJoinColumns = @JoinColumn(name = "tag_no"))
	private List<Tag> mtTags;
	
	@Column(name = "tags")
	private String tags;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

//	public List<SubMaterial> getSubs() {
//		return subs;
//	}
//
//	public void setSubs(List<SubMaterial> subs) {
//		this.subs = subs;
//	}

	public Boolean getMonthDealLabelView() {
		return monthDealLabelView;
	}

	public void setMonthDealLabelView(Boolean monthDealLabelView) {
		this.monthDealLabelView = monthDealLabelView;
	}

	public Boolean getProductLabelView() {
		return productLabelView;
	}

	public void setProductLabelView(Boolean productLabelView) {
		this.productLabelView = productLabelView;
	}

	public Boolean getMdpickLabelView() {
		return mdpickLabelView;
	}

	public void setMdpickLabelView(Boolean mdpickLabelView) {
		this.mdpickLabelView = mdpickLabelView;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public List<Tag> getMtTags() {
		return mtTags;
	}


	public void setMtTags(List<Tag> mtTags) {
		this.mtTags = mtTags;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public Integer getEstimateTileRank() {
		return estimateTileRank;
	}

	public void setEstimateTileRank(Integer estimateTileRank) {
		this.estimateTileRank = estimateTileRank;
	}
}
