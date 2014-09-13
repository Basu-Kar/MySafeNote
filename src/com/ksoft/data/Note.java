package com.ksoft.data;

public class Note {

	private int id;
	private String subject;
	private String note;
	
	public Note(){}
	public Note(String subject){
		this.subject=subject;
	}
	public Note(int id,String subject,String note){
		this.id=id;
		this.subject=subject;
		this.note=note;
	}
	
	public Note(String subject,String note){
		this.subject=subject;
		this.note=note;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
