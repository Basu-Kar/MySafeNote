package com.ksoft.mynote;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowNoteActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_note);
		
		TextView subjText = (TextView) findViewById(R.id.show_note_subj_txt_id);
		TextView detailText = (TextView) findViewById(R.id.show_note_details_txt_id);
		TextView idTxt = (TextView) findViewById(R.id.show_note_id_txt_id);
		
		Intent intent = getIntent();
		
		subjText.setText(intent.getStringExtra("noteSubject"));
		detailText.setText(intent.getStringExtra("noteDetails"));
		idTxt.setText(intent.getIntExtra("noteId",0)+"");
		System.out.println("intent.getStringExtra"+intent.getIntExtra("noteId",0));
		Button btn = (Button) findViewById(R.id.show_note_edit_btn_id);
		btn.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_note, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		TextView subjText = (TextView) findViewById(R.id.show_note_subj_txt_id);
		TextView detailText = (TextView) findViewById(R.id.show_note_details_txt_id);
		TextView idTxt = (TextView) findViewById(R.id.show_note_id_txt_id);
		
		Intent intent = new Intent(this, UpdateNoteActivity.class);
		intent.putExtra("noteId", idTxt.getText().toString());
   	 	intent.putExtra("noteSubject", subjText.getText().toString());
   	 	intent.putExtra("noteDetails", detailText.getText().toString());
	    startActivity(intent);
		
	}

}
