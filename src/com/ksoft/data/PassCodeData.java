package com.ksoft.data;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.ksoft.data.NoteConstant.*;

public class PassCodeData {

	private SQLiteDatabase database;
	  private NoteSQLHelper dbHelper;
	  private String[] allColumns = { COLUMN_ID,
			  COLUMN_ACT_TYPE,
			  COLUMN_UNAME,
			  COLUMN_PASSWORD
			  
	  };
	  
	  
	public void insertPassCode(String passcode,String hintAns){
		 ContentValues values = new ContentValues();
		    /*values.put(PasswordEntrySQLHelper.COLUMN_ACT_TYPE, actName);
		    values.put(PasswordEntrySQLHelper.COLUMN_UNAME, uName);
		    values.put(PasswordEntrySQLHelper.COLUMN_PASSWORD, password);
		    
		    long insertId = database.insert(PasswordEntrySQLHelper.TABLE_PASS_ENTRY, null,
		        values);
		    Cursor cursor = database.query(PasswordEntrySQLHelper.TABLE_PASS_ENTRY,
		        allColumns, PasswordEntrySQLHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    PasswordEntry newComment = cursorToPwdEntry(cursor);
		    cursor.close();
		    return newComment;*/
	}
	public void updatePassCode(String passcode, String hintAns){
		
	}
	public String[] getPassCodeAndHintAns(){
		return null;
	}
	public boolean verifyPassCode(String passcode){
		
		return true;
	}
	public boolean isPassCodePresent(){
		return false;
	}
	
	
	/*private PasswordEntry cursorToPwdEntry(Cursor cursor) {
		  PasswordEntry comment = new PasswordEntry();
	    comment.setId(cursor.getLong(0));
	    comment.setActtype(cursor.getString(1));
	    comment.setUname(cursor.getString(2));
	    comment.setPassword(cursor.getString(3));
	    return comment;
	  }

	  public List<PasswordEntry> getAllComments() {
		    List<PasswordEntry> comments = new ArrayList<PasswordEntry>();

		    Cursor cursor = database.query(PasswordEntrySQLHelper.TABLE_PASS_ENTRY,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	PasswordEntry comment = cursorToPwdEntry(cursor);
		      comments.add(comment);
		      cursor.moveToNext();
		    }
		    // make sure to close the cursor
		    cursor.close();
		    return comments;
	  }
	  
	  public void deleteComment(PasswordEntry comment) {
		    long id = comment.getId();
		    System.out.println("Comment deleted with id: " + id);
		    database.delete(PasswordEntrySQLHelper.TABLE_PASS_ENTRY, PasswordEntrySQLHelper.COLUMN_ID
		        + " = " + id, null);
	  }

	  public void deleteAllPassword() {
		   
		  //  System.out.println("Comment deleted with id: " + id);
		    database.delete(PasswordEntrySQLHelper.TABLE_PASS_ENTRY, null, null);
	  }
*/
}
