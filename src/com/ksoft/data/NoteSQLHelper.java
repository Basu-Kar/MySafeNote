package com.ksoft.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static com.ksoft.data.NoteConstant.*;

public class NoteSQLHelper extends SQLiteOpenHelper{

	// Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_PASS_ENTRY + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " 
	      + COLUMN_ACT_TYPE + " text not null, "
	      + COLUMN_UNAME + " text not null, "
	      + COLUMN_PASSWORD + " text not null "
	      + ");";
		  
	public NoteSQLHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 Log.w(NoteSQLHelper.class.getName(),
			        "Upgrading database from version " + oldVersion + " to "
			            + newVersion + ", which will destroy all old data");
		  
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASS_ENTRY);
	    onCreate(db);
		
	}

	
}
