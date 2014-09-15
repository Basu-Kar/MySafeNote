package com.ksoft.mynote;

import com.ksoft.data.Note;
import com.ksoft.data.NoteData;
import com.ksoft.data.PassCodeData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateNoteActivity extends Activity implements View.OnClickListener{

	private NoteData noteData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_note);
		
		TextView subjText = (TextView) findViewById(R.id.update_note_title);
		TextView detailText = (TextView) findViewById(R.id.update_note_details_id);
		TextView idTxt = (TextView) findViewById(R.id.update_note_id);
		
		Intent intent = getIntent();
		
		subjText.setText(intent.getStringExtra("noteSubject"));
		detailText.setText(intent.getStringExtra("noteDetails"));
		idTxt.setText(intent.getStringExtra("noteId"));
		
		//System.out.println("intent.getStringExtra"+intent.getIntExtra("noteId",0));
		Button btn = (Button) findViewById(R.id.update_note_update);
		btn.setOnClickListener(this);
		
		noteData = new NoteData(this);
		noteData.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.update_note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		TextView subjText = (TextView) findViewById(R.id.update_note_title);
		TextView detailText = (TextView) findViewById(R.id.update_note_details_id);
		TextView idTxt = (TextView) findViewById(R.id.update_note_id);
		Note note = new Note(Integer.parseInt(idTxt.getText().toString()),subjText.getText().toString(),detailText.getText().toString());
		System.out.println("Note "+note.getId()+" "+note.getSubject()+" "+note.getNote());
		noteData.updateNote(note);
		Intent intent = new Intent(this, HomeActivity.class);
		intent.putExtra("virified", true);
		
		startActivity(intent);
		
	}
}
