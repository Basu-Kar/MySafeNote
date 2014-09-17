package com.ksoft.mynote;

import com.ksoft.data.Note;
import com.ksoft.data.NoteData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddNoteActivity extends Activity implements View.OnClickListener{

	private NoteData noteData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_note);
		//Intent intent = getIntent();
		//TextView noteSubjView = (TextView) findViewById(R.id.add_note_subject_id);
		//noteSubjView.setText(intent.getStringExtra("subjectId"));
				
		//Button btn = (Button) findViewById(R.id.add_note_saveandexit);
		//btn.setOnClickListener(this);
		
		Button btn2 = (Button) findViewById(R.id.add_note_saveandaddnew);
		btn2.setOnClickListener(this);
		noteData = new NoteData(this);
		noteData.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_note, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        	case R.id.add_note_rstpwd_action_id:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	            //Toast.makeText(HomeActivity.this, "Reset Password Selected", Toast.LENGTH_SHORT).show();
	            Intent resetPwdIntent = new Intent(this, ResetPwdActivity.class);
	            startActivity(resetPwdIntent);
	            return true;
            
        }
        return false;
    }
	
	@Override
	public void onClick(View v) {
		TextView view = (TextView)v;
		//TextView noteSubjView = (TextView) findViewById(R.id.add_note_subject_id);
		TextView noteSubjView = (TextView) findViewById(R.id.note_title);
		TextView noteView = (TextView) findViewById(R.id.editNote);
		if(isValidEntry()){
			if("Save".equals(view.getText().toString())){
				Toast.makeText(AddNoteActivity.this, noteSubjView.getText().toString(), Toast.LENGTH_LONG).show();
				noteData.insertNote(new Note(noteSubjView.getText().toString().trim(),noteView.getText().toString().trim()));
				Intent intent = new Intent(this, HomeActivity.class);
				intent.putExtra("virified", true);
			    startActivity(intent);
			}/*else if("Save and AddNew".equals(view.getText().toString())){
				Toast.makeText(AddNoteActivity.this, noteView.getText().toString(), Toast.LENGTH_LONG).show();
			}*/
		}
		
	}
	
	private boolean isValidEntry(){
		boolean valid=true;
		TextView noteView = (TextView) findViewById(R.id.editNote);
		
		TextView noteSubjView = (TextView) findViewById(R.id.note_title);
		
		if("".equals(noteSubjView.getText().toString().trim()) ){
			noteSubjView.setError("Blank Not Allowed");
			valid = false;
		}else if(noteSubjView.getText().toString().trim().length()<3){
			noteSubjView.setError("Minimum 3 characters");
			valid = false;
		}else if("".equals(noteView.getText().toString().trim()) ){
			noteView.setError("Blank Not Allowed");
			valid = false;
		}else if(noteView.getText().toString().trim().length()<5){
			noteView.setError("Minimum 5 characters");
			valid = false;
		}else if(noteData.isDuplicateTitle(noteSubjView.getText().toString().trim())){
			noteView.setError("Note title already exists");
			valid = false;
		}
		
		
		return valid;
	}
	
	
	

}
