package com.ksoft.mynote;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class AddNoteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		Intent intent = getIntent();
		TextView userNameView = (TextView) findViewById(R.id.add_note_subject_id);
		userNameView.setText(intent.getStringExtra("subjectId"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_note, menu);
		return true;
	}

}
