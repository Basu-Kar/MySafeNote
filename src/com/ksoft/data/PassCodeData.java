package com.ksoft.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static com.ksoft.data.NoteConstant.*;

public class PassCodeData {

	private SQLiteDatabase database;
	private NoteSQLHelper dbHelper;
	private String[] all_pwd_Columns = { NOTE_PASSCODE_ID_COL,
			  NOTE_PASSCODE_PWD_COL,
			  NOTE_PASSCODE_HNT_ANS_COL
			  
	};
	private String[] all_note_Columns = { NOTE_ID_COL,
			NOTE_TITLE_COL,
			NOTE_VAL_COL 
			};  
	private String[] password_Column = { NOTE_PASSCODE_PWD_COL};
	
	public String getPassCode(){
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
				password_Column, null, null,null, null, null);
		
		cursor.moveToFirst();
		String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(0));
		cursor.close();
		
		return passCodeDcpt;
	}
	  
	public void deletePassCode(){
		database.delete(NOTE_PASSCODE_TABLE, null,null);
	}
	public PassCode insertPassCode(PassCode paascode){
		
		String passCodeEnc = PassCodeUtil.encryptedPassword(NoteConstant.APP_KEY, paascode.getPasscode().trim());
		String hintAnsEnc = PassCodeUtil.encryptedPassword(NoteConstant.APP_KEY, paascode.getHintAns().trim());
		
		ContentValues values = new ContentValues();
	    values.put(NOTE_PASSCODE_PWD_COL, passCodeEnc);
	    values.put(NOTE_PASSCODE_HNT_ANS_COL, hintAnsEnc);
	    
	    long insertId = database.insert(NOTE_PASSCODE_TABLE, null,
	        values);
	    Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, NOTE_PASSCODE_ID_COL + " = " + insertId, null,null, null, null);
	    
	    cursor.moveToFirst();
	    
	    String passCodeDcpt = PassCodeUtil.encryptedPassword(NoteConstant.APP_KEY, cursor.getString(1));
		String hintAnsDcpt = PassCodeUtil.encryptedPassword(NoteConstant.APP_KEY, cursor.getString(2));
		
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),passCodeDcpt,hintAnsDcpt);
	    cursor.close();
	    
	    return passCodeObj;
	}
	public PassCode updatePassCode(PassCode paascode){
		
		updateAllNotes(paascode.getPasscode().trim());
		
		String passCodeEnc = PassCodeUtil.encryptedPassword(NoteConstant.APP_KEY, paascode.getPasscode().trim());
		String hintAnsEnc = PassCodeUtil.encryptedPassword(NoteConstant.APP_KEY, paascode.getHintAns().trim());
		
		
		ContentValues values = new ContentValues();
	    values.put(NOTE_PASSCODE_PWD_COL, passCodeEnc);
	    if(!"".equals(paascode.getHintAns().trim())){
	    	values.put(NOTE_PASSCODE_HNT_ANS_COL, hintAnsEnc);
	    }
	    database.update(NOTE_PASSCODE_TABLE, values, null,null);
	    Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
	    cursor.moveToFirst();
	    String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(1));
		String hintAnsDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(2));
		
	    PassCode passCode = new PassCode(cursor.getInt(0),passCodeDcpt,hintAnsDcpt);
	    cursor.close();
	    return passCode;
	}
	
	
	private void updateAllNotes(String newPasscode){
		String passCode = getPassCode();
		
		Cursor cursor = database.query(NOTE_TABLE,
	    		all_note_Columns, null,null,null, null, null);
	    
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			int noteId = cursor.getInt(0);
			String oldNote = PassCodeUtil.decryptedData(passCode, cursor.getString(2));
			String updateNote = PassCodeUtil.encryptedData(newPasscode, oldNote);
			//System.out.println("oldNote="+oldNote);
			//System.out.println("updateNote="+updateNote);
			ContentValues values = new ContentValues();
		    values.put(NOTE_VAL_COL, updateNote);
		    database.update(NOTE_TABLE, values, NOTE_ID_COL+" = "+noteId,null );
		    
			cursor.moveToNext();
	    }
	    cursor.close();
	    
	}
	public PassCode getPassCodeDetails(){
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
	    cursor.moveToFirst();
	    String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(1));
		String hintAnsDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(2));
		
	    PassCode passCode = new PassCode(cursor.getInt(0),passCodeDcpt,hintAnsDcpt);
	    cursor.close();
	    
	    return passCode;
	}
	public boolean verifyPassCode(String passcode){
		
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
		if(cursor.getCount()<=0){
			return false;
		}
	    cursor.moveToFirst();
	    
	    String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(1));
		String hintAnsDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(2));
		
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),passCodeDcpt,hintAnsDcpt);
	    
	    cursor.close();
	    if(passcode!=null && passCodeObj!=null && passcode.trim().equals(passCodeObj.getPasscode())){
	    	return true;
	    }else{
	    	return false;
	    }
		
	}
	public boolean verifyHintAns(String hintAns){
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
		if(cursor.getCount()<=0){
			return false;
		}
	    cursor.moveToFirst();
	    String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(1));
		String hintAnsDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(2));
		
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),passCodeDcpt,hintAnsDcpt);
	    cursor.close();
	    if(hintAns!=null && passCodeObj!=null && passCodeObj.getHintAns()!=null && hintAns.trim().equals(passCodeObj.getHintAns())){
	    	return true;
	    }else{
	    	return false;
	    }
		
	}
	public boolean isPassCodePresent(){
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
		System.out.println("cursor.getCount() "+cursor.getCount());
		if(cursor.getCount()<=0){
			return false;	
		}
	    cursor.moveToFirst();
	    String passCodeDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(1));
		String hintAnsDcpt = PassCodeUtil.decryptedPassword(NoteConstant.APP_KEY, cursor.getString(2));
		
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),passCodeDcpt,hintAnsDcpt);
	    cursor.close();
	    
	    if(passCodeObj!=null &&  passCodeObj.getId()!=0){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	public PassCodeData(Context context) {
	    dbHelper = new NoteSQLHelper(context);
	 }
	
	 public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	 }
	
	 public void close() {
	    dbHelper.close();
	 }
}
