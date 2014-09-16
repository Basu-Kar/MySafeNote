package com.ksoft.mynote;

import java.util.List;

import com.ksoft.data.Note;
import com.ksoft.data.NoteData;
import com.ksoft.data.PassCodeData;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class HomeActivity extends ListActivity implements View.OnClickListener{

	private String[] noteList;
	private NoteData noteData;
	private PassCodeData passCodeData;
	List<Note> notes = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Button btn = (Button) findViewById(R.id.home_note_btn_id);
		btn.setOnClickListener(this);
		//SharedPreferences settings = getSharedPreferences("Test", Context.MODE_PRIVATE);
		
		
		
		//SharedPreferences settings2 = PreferenceManager.getDefaultSharedPreferences(this);
		//String url = settings2.getString("url", "n/a"); 
		
		//Editor edit = settings2.edit();
		//edit.putString("username", "new_value_for_user");
		//edit.apply();
		
		/*pwdList = new String[values.size()];
		for(PasswordEntry entry:values){
			pwdList[cnt] = entry.getActtype();
			cnt++;
		}*/
		//pwdList=
		Intent intentVal = getIntent();
		if(intentVal!=null && !intentVal.getBooleanExtra("virified", false)){
			
			passCodeData = new PassCodeData(this);
			passCodeData.open();
			if(!passCodeData.isPassCodePresent()){
				Intent intent = new Intent(this, EnablePwdActivity.class);
	            startActivity(intent);
			}else{
				Intent intent = new Intent(this, PasswordActivity.class);
	            startActivity(intent);
			}
			
		}else{
			noteData=new NoteData(this);
			noteData.open();
			updateListView();
		}
		
	}
	
	private void updateListView(){
		notes = noteData.getNotes();
		System.out.println(notes+">>>>>>>>>>>>>");		
		noteList=new String[notes.size()];
		int count=0;
		for(Note note:notes){
			noteList[count]=note.getSubject();
			count++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, noteList);
		ListView list=(ListView)findViewById(android.R.id.list);
		
		list.setAdapter(adapter);
		registerForContextMenu(list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override  
	   public void onCreateContextMenu(ContextMenu menu, View v,  
	             ContextMenuInfo menuInfo) {  
	        // TODO Auto-generated method stub  
	        super.onCreateContextMenu(menu, v, menuInfo); 
	        System.out.println("............onCreateContextMenu()......called");
	        MenuInflater m = getMenuInflater();  
	        m.inflate(R.menu.list_notes, menu);  
	   }  
	@Override  
	   public boolean onContextItemSelected(MenuItem item) {
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();  
			int position = (int) info.id;  
			Note noteobj = notes.get(position);
	        switch(item.getItemId()){  
	             case R.id.delete_item:  
	                  
	                //Note note= new Note(noteList[position]);
	                 noteData.deleteNote( noteobj);
	                 updateListView();
	                 Toast.makeText (this,
	                         "Note Deleted: " +  noteobj.getSubject(),
	                         Toast.LENGTH_SHORT).show();
	                  return true;
	             case R.id.view_item:
	            	 
	            	 Intent intent = new Intent(this,ShowNoteActivity.class);
	            	 intent.putExtra("noteId", noteobj.getId());
	            	 intent.putExtra("noteSubject", noteobj.getSubject());
	            	 intent.putExtra("noteDetails", noteobj.getNote());
	            	 startActivity(intent);
	            	 return true;
	             case R.id.edit_item:
	            	
	            	 Intent intentEdit = new Intent(this,UpdateNoteActivity.class);
	            	 intentEdit.putExtra("noteId", noteobj.getId()+"");
	            	 intentEdit.putExtra("noteSubject", noteobj.getSubject());
	            	 intentEdit.putExtra("noteDetails", noteobj.getNote());
	            	 startActivity(intentEdit);
	            	 return true;
	            	 
	        }  
	        return super.onContextItemSelected(item);  
	   }  
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        	case R.id.home_menu_rstpwd_action_id:
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
		//TextView subjectId = (TextView) findViewById(R.id.home_text_edit_id);
		//System.out.println("subjectId: "+subjectId.getText());
		
		//if(isValidEntry()){
			Intent intent = new Intent(this, AddNoteActivity.class);
			   
		    //intent.putExtra("subjectId", subjectId.getText().toString());
		    startActivity(intent);
		//}
		
		
	}
	
	//private boolean isValidEntry(){
		/*boolean valid=true;
		TextView noteView = (TextView) findViewById(R.id.home_text_edit_id);
		
		if("".equals(noteView.getText().toString().trim()) ){
			noteView.setError("Blank Not Allowed");
			valid = false;
		}else if(noteView.getText().toString().trim().length()<3){
			noteView.setError("Minimum 3 characters");
			valid = false;
		}*/
		
		/*List<PasswordEntry> values = passwordDAO.getAccount(acTypeView.getText().toString());
		if(values!=null && values.size()>0){
			acTypeView.setError("Already Exists");
			valid = false;
		}*/
		//return valid;
	//}
	
/*	public class PwdEntryAdapter extends BaseAdapter{

		private Context context;

	    public PwdEntryAdapter(Context c)
	    {
	        context = c;
	    }
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return noteList.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView textview = null;
			if(convertView==null){
				textview = new TextView(context);
				textview.setLayoutParams(new
                        GridView.LayoutParams(85, 85));
				
				textview.setPadding(5, 5, 5, 5);
			}else {
				textview = (TextView) convertView;
            }
			textview.setText(noteList[position]);
			return textview;
		}
		
	}

*/
}
