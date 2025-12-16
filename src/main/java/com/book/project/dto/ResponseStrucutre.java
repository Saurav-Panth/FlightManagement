package com.book.project.dto;

public class ResponseStrucutre<T> {

	private int stausCode;
	private String message;
	private T data;
	
	
	public int getStausCode() {
		return stausCode;
	}
	public void setStausCode(int stausCode) {
		this.stausCode = stausCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
