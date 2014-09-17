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
	private String editNoteSubject=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_note);
		
		TextView subjText = (TextView) findViewById(R.id.update_note_title);
		TextView detailText = (TextView) findViewById(R.id.update_note_details_id);
		TextView idTxt = (TextView) findViewById(R.id.update_note_id);
		
		Intent intent = getIntent();
		
		subjText.setText(intent.getStringExtra("noteSubject"));
		editNoteSubject = intent.getStringExtra("noteSubject");
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        	case R.id.update_note_rstpwd_action_id:
	            Intent resetPwdIntent = new Intent(this, ResetPwdActivity.class);
	            startActivity(resetPwdIntent);
	            return true;
            
        }
        return false;
    }

	@Override
	public void onClick(View v) {
		if(isValidEntry()){
			TextView subjText = (TextView) findViewById(R.id.update_note_title);
			TextView detailText = (TextView) findViewById(R.id.update_note_details_id);
			TextView idTxt = (TextView) findViewById(R.id.update_note_id);
			
			Note note = new Note(Integer.parseInt(idTxt.getText().toString()),subjText.getText().toString(),detailText.getText().toString());
			//System.out.println("Note "+note.getId()+" "+note.getSubject()+" "+note.getNote());
			noteData.updateNote(note);
			Intent intent = new Intent(this, HomeActivity.class);
			intent.putExtra("virified", true);
			
			startActivity(intent);
		}
		
	}
	
	private boolean isValidEntry(){
		boolean valid=true;
		TextView noteView = (TextView) findViewById(R.id.update_note_details_id);
		
		TextView noteSubjView = (TextView) findViewById(R.id.update_note_title);
		
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
		}else if(editNoteSubject!=null && !editNoteSubject.equalsIgnoreCase(noteSubjView.getText().toString().trim())&&
				noteData.isDuplicateTitle(noteSubjView.getText().toString().trim())){
			noteView.setError("Note title already exists");
			valid = false;
		}
		
		
		return valid;
	}
	
}
