package com.ziyouling.good.goodserver.vo;

public class NameAndCode {
	private String name;
	private String code;
	
	public NameAndCode()
	{
		
	}
	
	public NameAndCode(String code, String name)
	{
		this.code = code;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
