package com.my.friends;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.InputVerifier;


public class TextFieldsValidator /*extends InputVerifier*/{
	private Pattern pattern;
	private Matcher matcher;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
												+ "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String USER_NAME_PATTERN = "^[A-Za-z\\s]*$";
	//private static final String PHONE_NUMBER_PATTERN = "4087876767";

	public boolean validateEmail(final String emailId){
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(emailId);
		return matcher.matches();
	}
	
	public boolean validateUserName(final String userName){
		pattern = Pattern.compile(USER_NAME_PATTERN);
		matcher = pattern.matcher(userName);
		return matcher.matches();
	}
	
	//change starts
	public boolean validatePhoneNumber (final String phNo){
		 pattern = Pattern.compile("\\d{10}");
	     matcher = pattern.matcher(phNo);
	     return matcher.matches();
	}
	//change ends
}
