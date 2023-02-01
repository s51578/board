package com.connect.brick.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.internal.util.privilegedactions.GetDeclaredFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.connect.brick.config.RequestMappingConstants;
import com.connect.brick.model.Order;
import com.connect.brick.model.access.Account;

@Component
public class ValidComponent {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean isCheckLoginByPhoneNum(String phoneNum, String pwd) throws Exception {
		
		String result = phoneNum.substring(phoneNum.length()-4, phoneNum.length());
		
		int backNum = Integer.valueOf(result);
		int pwd_int = 0;
		
		try {
			pwd_int = Integer.valueOf(pwd);
		} catch (Exception e) {
			throw new NumberFormatException();
		}
		
		if(pwd_int!=backNum)
			return false;
		
		return true;
	}
	
	public static boolean isValidPhone(String phoneNum) {
		
		if(phoneNum.contains(" "))
			return false;
			
		return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", phoneNum);
	}
	
	public static boolean isValidNumber(Double number) {
		String num = Double.toString(number);
		
		return Pattern.matches("^(-?[0-9]+?)([,]?([0-9]+))*([.]?[0-9]+)\\%?$", num);
	}
	
	public static boolean isOrderListCheck(Order order) {
		
		if(order == null)
			return false;
		if(order.getCustomer() == null )
			return false;
		if(order.getCustomer().getName() == null || order.getCustomer().getName().equals("") || order.getCustomer().getName().equals(" ")) 
			return false;
		if(isValidPhone(order.getCustomer().getPhoneNum()) == false || order.getCustomer().getPhoneNum() == null)
			return false;
		if(order.getCustomer().getAddress() == null || order.getCustomer().getAddress().equals("") || order.getCustomer().getAddress().equals(" ")) 
			return false;
		if(order.getCustomer().getFunnel() == null || order.getCustomer().getFunnel().equals("") || order.getCustomer().getFunnel().equals(" ")) 
			return false;
		if(order.getTypeHouse() == null) 
			return false;
		if(order.getSpaceUse() == null || order.getSpaceUse().equals("") || order.getSpaceUse().equals(" ")) 
			return false;
		if(isValidNumber(order.getSupArea()) == false || order.getSupArea() == null) 
			return false;
		if(isValidNumber(order.getDedArea()) == false || order.getDedArea() == null) 
			return false;
		if(isValidNumber(order.getAreaCons()) == false || order.getAreaCons() == null) 
			return false;
		if(order.getIsDestroy() == null|| order.getIsDestroy().equals("") || order.getIsDestroy().equals(" ")) 
			return false;
		if(order.getLivingFloorMaterial() == null) 
			return false;
		if(order.getKitchenFloorMaterial() == null) 
			return false;
		if(order.getRoomFloorMaterial() == null) 
			return false;
		if(order.getCommercialFloorMaterial() == null) 
			return false;
		if(order.getIsMolding() == null) 
			return false;
		if(order.getStateSpace() == null) 
			return false;
		if(order.getStateSpaceCommerce() == null) 
			return false;
		if(order.getDesireDate() == null) 
			return false;

		return true;
	}

	public boolean isValidEmailAddress(String email) {
		
		if(email.contains(" "))
			return false;
		
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	public boolean joinValid(Account joinForm) {
		
		String id = joinForm.getEmail();
		String password = joinForm.getPassword();
		String confirmPassword = joinForm.getConfirmPassword();
		
		
		if(id==null || id.equals("") || id.contains(" "))
			return false;
		if(id.length()<4)
			return false;
		
		if(password==null 
				|| password.equals("") || password.contains(" "))
			return false;
		if(password.length()<8)
			return false;
		
		if(confirmPassword==null 
				|| confirmPassword.equals("") || confirmPassword.contains(" "))
			return false;
		if(confirmPassword.length()<8)
			return false;
		
		if(!password.equals(confirmPassword))
			return false;
		
		return true;
	}
	
	public boolean joinValidReturn(Account joinForm) throws Exception {
		
		String id = joinForm.getEmail();
		String password = joinForm.getPassword();
		String confirmPassword = joinForm.getConfirmPassword();
		
		if(id==null || id.equals(""))
			throw new Exception("아이디를 입력해주세요");
		if(id.contains(" "))
			throw new Exception("아이디에 공백을 넣을 수 없습니다");
		
		if(id.length()<4)
			throw new Exception("아이디는 4자 이상입니다");
		
		if(password==null || password.equals(""))
			throw new Exception("비밀번호를 입력해주세요");
		if(password.contains(" "))
			throw new Exception("비밀번호에 공백을 넣을 수 없습니다");
		if(password.length()<8)
			throw new Exception("비밀번호는 8자 이상입니다");
		
		if(confirmPassword==null 
				|| confirmPassword.equals("") || confirmPassword.contains(" "))
			throw new Exception("비밀번호 확인란을 입력해주세요");
		if(confirmPassword.length()<8)
			throw new Exception("비밀번호 확인란은 8자 이상입니다");
		
		if(!password.equals(confirmPassword))
			throw new Exception("비밀번호가 일치하지 않습니다");
		
		return true;
	}

	public boolean matchPassword(Account account, String pwd) {
		
		return passwordEncoder.matches(pwd, account.getPassword());
	}
	
	public boolean modifyPasswordValid(Account account, Account validForm) {
		
		String password = validForm.getPassword();
		String confirmPassword = validForm.getConfirmPassword();
		
		if(password==null 
				|| password.equals("") || password.contains(" "))
			return false;
		if(password.length()<8)
			return false;
		
		if(confirmPassword==null 
				|| confirmPassword.equals("") || confirmPassword.contains(" "))
			return false;
		if(confirmPassword.length()<8)
			return false;
		
		if(!passwordEncoder.matches(confirmPassword, passwordEncoder.encode(password)))
			return false;
		
		return true;
	}
	
	
}
