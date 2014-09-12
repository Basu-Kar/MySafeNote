package com.ksoft.data;

public class PassCode {

	private int id;
	private String passcode;
	private String hintAns; 
	private String hintQstn;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public String getHintAns() {
		return hintAns;
	}
	public void setHintAns(String hintAns) {
		this.hintAns = hintAns;
	}
	public String getHintQstn() {
		return hintQstn;
	}
	public void setHintQstn(String hintQstn) {
		this.hintQstn = hintQstn;
	}
	
	
}
