package com.connect.brick.component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailComponent {

	@Value("${mail.username}")
	private String mailusername;
	
	@Value("${mail.password}")
	private String mailpassword;
	
	@Value("${mail.smtp.host}")
	private String mailsmtphost;
	
	@Value("${mail.smtp.port}")
	private String mailsmtpport;
	
	@Value("${mail.smtp.auth}")
	private String mailsmtpauth;
	
	@Value("${mail.smtp.ssl.enable}")
	private String mailsmtpsslenable;
	
	@Value("${mail.smtp.ssl.trust}")
	private String mailsmtpssltrust;

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailComponent() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}
	
	public void gmailSend(String recipient, String subject, String content) throws AddressException, MessagingException, UnsupportedEncodingException {
		
		String user = mailusername; // gmail 계정
		String password = mailpassword; // 패스워드
		
		// SMTP 서버 정보를 설정한다.
		Properties prop = new Properties();
		prop.put("mail.smtp.host", mailsmtphost);
		prop.put("mail.smtp.port", mailsmtpport);
		prop.put("mail.smtp.auth", mailsmtpauth);
		prop.put("mail.smtp.ssl.enable", mailsmtpsslenable);
		prop.put("mail.smtp.ssl.trust", mailsmtpssltrust);

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		
		// 수신자메일주소
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient, "와플", "UTF-8"));
		
		// Subject
		message.setSubject(subject, "UTF-8"); // 메일 제목을 입력
		// Text
		message.setContent(content, "text/html; charset=utf-8");
		
		// send the message
		Transport.send(message); //// 전송
		
	}
	
	public String getEncMD5(String txt) {

		StringBuffer sbuf = new StringBuffer();

		MessageDigest mDigest = null;
		try {
			mDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		mDigest.update(txt.getBytes());

		byte[] msgStr = mDigest.digest();

		for (int i = 0; i < msgStr.length; i++) {
			String tmpEncTxt = Integer.toHexString((int) msgStr[i] & 0x00ff);
			sbuf.append(tmpEncTxt);
		}

		return sbuf.toString();
	}
	
	public String getRamdomPassword(int len) {
		
		char[] charSet = new char[] {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
				'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
			}; 
		
		int idx = 0;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {
			idx = (int)(charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		
		return sb.toString();
	}
	
}
