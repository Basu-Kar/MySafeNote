package com.ksoft.data;

import static com.ksoft.data.NoteConstant.*;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteData {

	private SQLiteDatabase database;
	private NoteSQLHelper dbHelper;
	private String[] all_note_Columns = { NOTE_ID_COL,
			NOTE_TITLE_COL,
			NOTE_VAL_COL 
			};
	private String[] password_Column = { NOTE_PASSCODE_PWD_COL
			};
	
	public String getPassCode(){
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
				password_Column, null, null,null, null, null);
	    
	    cursor.moveToFirst();
	    String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(0));
	    cursor.close();
	    
	    return passCodeDcpt;
	}
	
	public Note insertNote(Note note){
		String passCode = getPassCode();
		ContentValues values = new ContentValues();
	    values.put(NOTE_TITLE_COL, note.getSubject().trim());
	    values.put(NOTE_VAL_COL, PassCodeUtil.encryptedData(passCode, note.getNote().trim()) );
	    
	    long insertId = database.insert(NOTE_TABLE, null,
	        values);
	    Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, NOTE_ID_COL + " = " + insertId, null,null, null, null);
	    
	    cursor.moveToFirst();
	    Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),PassCodeUtil.decryptedData(passCode, cursor.getString(2)));
	    cursor.close();
	    
	    return noteObj;
	}
	
	public Note getNote(String subject){
		String passCode = getPassCode();
		 Cursor cursor = database.query(NOTE_TABLE,
		    		all_note_Columns, NOTE_TITLE_COL + " = " + subject, null,null, null, null);
		    cursor.moveToFirst();
		    Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),PassCodeUtil.decryptedData(passCode, cursor.getString(2)));
		    cursor.close();
		return noteObj;
	}
	
	
	public boolean isDuplicateTitle(String noteTitle){
		boolean duplicate = false;
		Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, null,null,null, null, null);
	    
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			if(noteTitle!=null && noteTitle.trim().equalsIgnoreCase(cursor.getString(1))){
				duplicate=true;
				break;
			}
			cursor.moveToNext();
	    }
	    cursor.close();
	    
		return duplicate;
		
	}
	
	public int deleteNote(Note note){
		String[] arr = {note.getId()+""};
	    return database.delete(NOTE_TABLE, NOTE_ID_COL
	        + " =? " ,arr );
	}
	
	public List<Note> getNotes(){
		String passCode = getPassCode();
		List<Note> noteList = new ArrayList<Note>();
		Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, null,null,null, null, null);
	    
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			//System.out.println("cursor.getInt(0):"+cursor.getInt(0));
			Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),PassCodeUtil.decryptedData(passCode, cursor.getString(2)));
			noteList.add(noteObj);
			cursor.moveToNext();
	    }
	    cursor.close();
	    
		return noteList;
	}
	
	public Note updateNote(Note note){
		String passCode = getPassCode();
		ContentValues values = new ContentValues();
	    values.put(NOTE_TITLE_COL, note.getSubject().trim());
	    values.put(NOTE_VAL_COL, PassCodeUtil.encryptedData(passCode,note.getNote().trim()));
	    //String[] arr={note.getSubject()};
	    database.update(NOTE_TABLE, values, NOTE_ID_COL+" = "+note.getId(),null );
	    Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, NOTE_ID_COL+" = "+note.getId(),null,null, null, null);
	    
	    cursor.moveToFirst();
	    Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),PassCodeUtil.decryptedData(passCode, cursor.getString(2)));
	    cursor.close();
	    return noteObj;
	}
	 public NoteData(Context context) {
		    dbHelper = new NoteSQLHelper(context);
	 }
	
	 public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	 }
	
	 public void close() {
	    dbHelper.close();
	 }
	
}
