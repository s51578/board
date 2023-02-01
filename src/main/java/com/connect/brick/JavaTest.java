package com.connect.brick;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.connect.brick.util.StringUtils;

public class JavaTest {
	public static boolean isValidPhone(String Phone_num) {
		return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", Phone_num);
	}
	public static void main(String[] args) {
		String content = "0103477-4302";
		System.out.println(isValidPhone(content));
		
	}
}
