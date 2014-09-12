package com.ksoft.mynote;



import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class HomeActivity extends Activity implements View.OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Button btn = (Button) findViewById(R.id.home_note_btn_id);
		btn.setOnClickListener(this);
		SharedPreferences settings = getSharedPreferences("Test", Context.MODE_PRIVATE);
		
		
		
		//SharedPreferences settings2 = PreferenceManager.getDefaultSharedPreferences(this);
		//String url = settings2.getString("url", "n/a"); 
		
		//Editor edit = settings2.edit();
		//edit.putString("username", "new_value_for_user");
		//edit.apply(); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        case R.id.home_menu_enbpwd_action_id:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(HomeActivity.this, "Enable Password Selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, EnablePwdActivity.class);
            startActivity(intent);
            return true;
        case R.id.home_menu_rstpwd_action_id:
            // Single menu item is selected do something
            // Ex: launching new activity/screen or show alert message
            Toast.makeText(HomeActivity.this, "Reset Password Selected", Toast.LENGTH_SHORT).show();
            Intent resetPwdIntent = new Intent(this, ResetPwdActivity.class);
            startActivity(resetPwdIntent);
            return true;
            
        }
        return false;
    }

	@Override
	public void onClick(View v) {
		TextView subjectId = (TextView) findViewById(R.id.home_text_edit_id);
		System.out.println("subjectId: "+subjectId.getText());
		
		
		Intent intent = new Intent(this, AddNoteActivity.class);
	   
	    intent.putExtra("subjectId", subjectId.getText().toString());
	    startActivity(intent);
		
	}

}
