package com.connect.brick.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.connect.brick.model.DpMaterial;
import com.connect.brick.model.Tag;
import com.connect.brick.model.code.MainColor;

public class SearchComponent {

	@Value("${upload.image.locations.path}")
	private String uploadImageLocationPath;
	
	public String regionName = null;
	public String mysqlTable = null;
	
	public String table = "apt";
	public String name_apart = ".name_apart";
	public String addr_sd = ".addr_sd";
	public String addr_sg = ".addr_sg";
	public String addr_gu = ".addr_gu";
	public String addr_emd = ".addr_emd";
	public String concat = "CONCAT(apt.addr_sg, ' ', apt.addr_gu, ' ', apt.addr_emd, ' ', apt.name_apart)";
	
	public SearchComponent(String mysqlTable, String regionName) {

		this.regionName = regionName;
		this.mysqlTable = mysqlTable;
		
	}
	
	public String getSelectFromOrg(String org) {
		return getSelect() + " AND " + getLikeIn(org);
	}
	
	public String getSelectFromAll(List<String> tokenList) {

		String query = "";
		
		if(tokenList.size() > 0) {

			query = " UNION " + getSelect();
			
			String query_regexp = "";
			
			for (String token : tokenList) {
				
				 query_regexp += " AND " + getLikeIn(token);
			}
			
			query += query_regexp;
		}
		
		
		return query;
	}
	public String getSelectFromRegexp(List<String> tokenList) {

		String query = "";
		
		if(tokenList.size() > 2)
			query = " UNION " +  getSelect() + " AND " + getRegexp(tokenList);
		
		
		return query;
	}
	
	
	public String getSelectFromExceptLast(List<String> tokenList) {
		
		String query = "";
		
		if(tokenList.size() > 2) {
			
				query = " UNION "+ getSelect();
				
				String query_regexp = "";
				
				for (int i = 0; i < tokenList.size(); i++) {
					String token = tokenList.get(i);
					
					if(i!=tokenList.size()-1)
						query_regexp += " AND " + getLikeIn(token);
					
				}
				
				query += query_regexp;
		}
		
		return query;
	}
	
	
	public String getSelect() {
		
		String select = "SELECT * FROM "+ mysqlTable + " " + table + " where 1=1 ";
		
		if(regionName!=null)
			return select + " AND " + table + addr_sd + " = '" + regionName + "'";
		else
			return select;
	}
	
	public String getLikeIn(String token) {

		String org_word = token.replace(" ", "%");
		
		return /* table + name_apart */ concat + " LIKE " + "'%" + org_word + "%'";
	}
	
	public String getRegexp(List<String> tokenList) {
		
		String query = /* table + name_apart */ concat + " REGEXP " + "'";
		
		String query_regexp = "";
		
		for (int i = 0; i < tokenList.size(); i++) {
			String token = tokenList.get(i);
			
			if(i==0)
				query_regexp += token;
			else
				query_regexp += "|"+token;
		}
		
		query += query_regexp + "'";
		
		return query;
	}

//	public String getSelectFromCustom(List<Token> tokenList) {
//		
//		String result = "";
//		
//		if(tokenList.size() > 0) {
//			
//			String query = "";
//			
//			for (Token token1 : tokenList) {
//				
//				String query_regexp = " UNION " + getSelect();
//				query_regexp += " AND ( ";
//				for (Token token2 : tokenList) {
//					
//					if(token1.getBeginIndex()==token2.getBeginIndex())
//						query_regexp += " OR " + getLikeIn(token2.getMorph());
//					else
//						query_regexp += " AND " + getLikeIn(token2.getMorph());
//				}
//				
//				query += query_regexp + ")";
//			}
//			
//			result += query;
//		}
//		
//		return result;
//	}
	

	public String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	public String getMysqlTable() {
		return mysqlTable;
	}

	public void setMysqlTable(String mysqlTable) {
		this.mysqlTable = mysqlTable;
	}

	
	public static List<DpMaterial> filterBySearch(List<DpMaterial> results, String search) {
		
		List<DpMaterial> filters = new ArrayList<DpMaterial>();
		
		for (DpMaterial dpMaterial : results) {
			String lowerSearch = search.toLowerCase();
			String cbName = dpMaterial.getMaterial().getCbName().toLowerCase();
			String lowerTags = dpMaterial.getMaterial().getMtContents().getTags();
			
			if(cbName!=null
					&& cbName.contains(lowerSearch))
				filters.add(dpMaterial);
			else if(lowerTags!=null 
					&& lowerTags.contains(search))
				filters.add(dpMaterial);
			
		}
		
		
		return filters;
	}

	/*
	 * 1. 타일이름 -> 검색
	 * 2. 태그 매칭 -> 검색 or 색상 매칭
	 * 3. 색상 매칭 -> 색상 매칭
	 * 4. 표면 검색 -> 표면
	 * 
	 */
	public static List<DpMaterial> filter(List<DpMaterial> results, List<String> searchColorName, String search) {
		
		List<DpMaterial> filters = new ArrayList<DpMaterial>();
		
		filters = filterForSearch(results, search);
		System.out.println("####################################################");
		for (DpMaterial dpMaterial : filters) {
			System.out.println(dpMaterial.getDpNo());
		}
		System.out.println("####################################################");
		filters = filterForColor(filters, searchColorName);
		
		return filters;
	}
	
	public static List<DpMaterial> filterForColor(List<DpMaterial> results, List<String> searchColorName) {
		
		if(searchColorName==null || searchColorName.isEmpty())
			return results;
		
		List<DpMaterial> filters = new ArrayList<DpMaterial>();
		
		for (DpMaterial dpMaterial : results) {
			
			List<Tag> tags = dpMaterial.getMaterial().getMtContents().getMtTags();
			
			boolean checkTag = false;
			for (Tag tag : tags) {
				
				if(searchColorName.contains(tag.getTagName())) {
					filters.add(dpMaterial);
					checkTag = true;
					break;
				}
			}
			
			//match tag and color
			if(checkTag)
				continue;
			
			MainColor mainColor = dpMaterial.getMaterial().getMtSpec().getMainColor();
			
			if(searchColorName.contains(mainColor.getMainColorName()))
				filters.add(dpMaterial);
		}
		
		return filters;
	}
	
	public static List<DpMaterial> filterForSearch(List<DpMaterial> results, String search) {
		
		if(search==null || search.equals(""))
			return results;
		
		String[] lowers = search.split(" ");
		
		String lowerSearch = search.toLowerCase();
		
		List<DpMaterial> filters = new ArrayList<DpMaterial>();
		
		for (DpMaterial dpMaterial : results) {
			String cbName = dpMaterial.getMaterial().getCbName().toLowerCase().replace(" ", "");
			String lowerTags = dpMaterial.getMaterial().getMtContents().getTags();
			
			if(lowerTags!=null && lowerTags.contains(lowerSearch))
				filters.add(dpMaterial);
			else
				for (String lower : lowers)
					if(cbName.contains(lower)) {
						filters.add(dpMaterial);
						break;
					}
//			else if(cbName.contains(lowerSearch))
//			filters.add(dpMaterial);
			
		}
		
		return filters;
	}
	
	public static List<String> getColorTonesByNo(List<MainColor> all, List<Long> color) {
		
		if(color==null || color.isEmpty() || color.size()<1)
			return null;
		
		List<String> mcName = new ArrayList<>();
		
		for (MainColor mc : all) {
			for (Long no : color) {
				if(mc.getNo()==no)
					mcName.add(mc.getMainColorName());
			}
		}
		
		return mcName;
	}
	
	
}
