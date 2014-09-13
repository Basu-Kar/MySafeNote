package com.ksoft.data;

public interface NoteConstant {

	public static final String DATABASE_NAME="note.db";
	public static final int DATABASE_VERSION=1;
	
	public static final String NOTE_PASSCODE_TABLE = "note_pwd_code";
	public static final String NOTE_PASSCODE_ID_COL = "note_pwd_id";
	public static final String NOTE_PASSCODE_PWD_COL = "note_pwd";
	public static final String NOTE_PASSCODE_HNT_ANS_COL = "note_pwd_hnt_ans";
	
	public static final String NOTE_TABLE = "note";
	public static final String NOTE_ID_COL = "note_id";
	public static final String NOTE_TITLE_COL = "note_title";
	public static final String NOTE_VAL_COL = "note_val";
	
	
	
	
	
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_ACT_TYPE = "acttype";
	public static final String COLUMN_UNAME = "uname";
	public static final String COLUMN_PASSWORD = "password";
}
