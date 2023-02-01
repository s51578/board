package com.connect.brick.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static List extractReceiver(String content) {

		List<String> tagList = new ArrayList<String>();

		Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣|ㄱ-ㅎㅏ]*)");
		Matcher m = p.matcher(content);
		
		while (m.find()) {
			String extracted = m.group();
			if (extracted != null) {
				extracted = extracted.replace("#", "");
				
				if(!extracted.equals(""))
					tagList.add(extracted);
			}
		}
		
		return tagList;
	}
	
}
