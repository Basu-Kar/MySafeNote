package com.ksoft.data;

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
	  
	  
	public void deletePassCode(){
		database.delete(NOTE_PASSCODE_TABLE, null,null);
	}
	public PassCode insertPassCode(PassCode paascode){
		ContentValues values = new ContentValues();
	    values.put(NOTE_PASSCODE_PWD_COL, paascode.getPasscode());
	    values.put(NOTE_PASSCODE_HNT_ANS_COL, paascode.getHintAns());
	    
	    long insertId = database.insert(NOTE_PASSCODE_TABLE, null,
	        values);
	    Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, NOTE_PASSCODE_ID_COL + " = " + insertId, null,null, null, null);
	    
	    cursor.moveToFirst();
	    PassCode passCode = new PassCode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
	    cursor.close();
	    
	    return passCode;
	}
	public PassCode updatePassCode(PassCode paascode){
		ContentValues values = new ContentValues();
	    values.put(NOTE_PASSCODE_PWD_COL, paascode.getPasscode());
	    values.put(NOTE_PASSCODE_HNT_ANS_COL, paascode.getHintAns());
	    
	    database.update(NOTE_PASSCODE_TABLE, values, null,null);
	    Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
	    cursor.moveToFirst();
	    PassCode passCode = new PassCode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
	    cursor.close();
	    return passCode;
	}
	public PassCode getPassCodeDetails(){
		Cursor cursor = database.query(NOTE_PASSCODE_TABLE,
	    		all_pwd_Columns, null, null,null, null, null);
	    
	    cursor.moveToFirst();
	    PassCode passCode = new PassCode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
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
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
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
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
	    System.out.println("passCodeObj.hintAns"+passCodeObj.getHintAns());
	    System.out.println("hintAns"+hintAns);
	    cursor.close();
	    if(hintAns!=null && passCodeObj!=null && passCodeObj.getHintAns()!=null && hintAns.trim().equals(passCodeObj.getHintAns().trim())){
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
	    PassCode passCodeObj = new PassCode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
	    cursor.close();
	    System.out.println("passCodeObj: "+passCodeObj.getId());
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
