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
	
	public Note insertNote(Note note){
		ContentValues values = new ContentValues();
	    values.put(NOTE_TITLE_COL, note.getSubject());
	    values.put(NOTE_VAL_COL, note.getNote());
	    
	    long insertId = database.insert(NOTE_TABLE, null,
	        values);
	    Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, NOTE_ID_COL + " = " + insertId, null,null, null, null);
	    
	    cursor.moveToFirst();
	    Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
	    cursor.close();
	    
	    return noteObj;
	}
	
	public Note getNote(String subject){
		 Cursor cursor = database.query(NOTE_TABLE,
		    		all_note_Columns, NOTE_TITLE_COL + " = " + subject, null,null, null, null);
		    cursor.moveToFirst();
		    Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
		    cursor.close();
		return noteObj;
	}
	
	public int deleteNote(Note note){
		String[] arr = {note.getSubject()};
	    return database.delete(NOTE_TABLE, NOTE_TITLE_COL
	        + " =? " ,arr );
	}
	
	public List<Note> getNotes(){
		List<Note> noteList = new ArrayList<Note>();
		Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, null,null,null, null, null);
	    
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
			noteList.add(noteObj);
			cursor.moveToNext();
	    }
	    cursor.close();
	    
		return noteList;
	}
	
	public Note updateNote(Note note){
		ContentValues values = new ContentValues();
	    values.put(NOTE_TITLE_COL, note.getSubject());
	    values.put(NOTE_VAL_COL, note.getNote());
	    String[] arr={note.getSubject()};
	    database.update(NOTE_TABLE, values, NOTE_TITLE_COL+" = ",arr );
	    Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, NOTE_TITLE_COL+" = "+note.getSubject(),null,null, null, null);
	    
	    cursor.moveToFirst();
	    Note noteObj = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
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
