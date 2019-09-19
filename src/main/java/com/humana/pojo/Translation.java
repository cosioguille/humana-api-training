package com.humana.pojo;

import java.util.List;

public class Translation {

	private int code;
	private String lang;
	private List<String> text;
	
	public Translation() {
		
	}
	
	public Translation(int code, String lang, List<String> text) {
		this.setCode(code);
		this.setLang(lang);
		this.setText(text);
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getLang() {
		return lang;
	}
	
	public void setLang(String lang) {
		this.lang = lang;
	}
	
	public List<String> getText() {
		return text;
	}
	
	public void setText(List<String> text) {
		this.text = text;
	}
	
}