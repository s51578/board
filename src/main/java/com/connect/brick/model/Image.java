package com.connect.brick.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.core.io.InputStreamResource;

@Entity
@Table(name = "TB_IMAGE")
public class Image {

	@Id
	@Column(name = "image_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "file_path")
	private String filePath;
	
	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_size")
	private Long fileSize;
	
	@Column(name = "thumbnail_path")
	private String thumbnailPath;
	
	@Column(name = "thumbnail_name")
	private String thumbnailName;

	@Column(name = "thumbnail_size")
	private Long thumbnailSize;
	
	@Column(name = "caption")
	private String caption;
	
	@Column(name = "link_addr")
	private String linkAddr;
	
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	/*
	 * @Column(name = "isMain") private Boolean isMain;
	 * 
	 * @Column(name = "isReg") private Boolean isReg;
	 
	
	@ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	@JoinColumn(name="material_no")
	private Material material;
	*/
	
	@Transient
	private InputStreamResource resource;
	
	public Image() {
		super();
	}

	public Image(String fileName, String filePath) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
	}
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getThumbnailName() {
		return thumbnailName;
	}

	public void setThumbnailName(String thumbnailName) {
		this.thumbnailName = thumbnailName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	
	/*
	 * public boolean getIsMain() { return isMain; }
	 * 
	 * public void setIsMain(boolean isMain) { this.isMain = isMain; }
	 * 
	 * public boolean getIsReg() { return isReg; }
	 * 
	 * public void setIsReg(boolean isReg) { this.isReg = isReg; }
	 
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
*/
	public InputStreamResource getResource() {
		return resource;
	}

	public void setResource(InputStreamResource resource) {
		this.resource = resource;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getThumbnailSize() {
		return thumbnailSize;
	}

	public void setThumbnailSize(Long thumbnailSize) {
		this.thumbnailSize = thumbnailSize;
	}


}
