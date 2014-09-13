package com.ksoft.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static com.ksoft.data.NoteConstant.*;

public class NoteSQLHelper extends SQLiteOpenHelper{

	// Database creation sql statement
	  private static final String DATABASE_CREATE_NOTE_PWD = "create table "
	      + NOTE_PASSCODE_TABLE + "(" + NOTE_PASSCODE_ID_COL
	      + " integer primary key autoincrement, " 
	      + NOTE_PASSCODE_PWD_COL + " text not null, "
	      + NOTE_PASSCODE_HNT_ANS_COL + " text not null "
	      + ");";
	  private static final String DATABASE_CREATE_NOTE = "create table "
		      + NOTE_TABLE + "(" + NOTE_ID_COL
		      + " integer primary key autoincrement, " 
		      + NOTE_TITLE_COL + " text not null, "
		      + NOTE_VAL_COL + " text not null "
		      + ");";
		  
	public NoteSQLHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_NOTE);
		db.execSQL(DATABASE_CREATE_NOTE_PWD);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 Log.w(NoteSQLHelper.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_NOTE);  
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CREATE_NOTE_PWD);
	    onCreate(db);
		
	}

	
}
