package com.ksoft.mynote;

import com.ksoft.data.PassCode;
import com.ksoft.data.PassCodeData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends Activity implements View.OnClickListener{

	private PassCodeData passCodeData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		Button btn = (Button) findViewById(R.id.pwd_save);
		btn.setOnClickListener(this);
		
		passCodeData = new PassCodeData(this);
		passCodeData.open();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.password, menu);
		return true;
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
	public void onClick(View arg0) {
		TextView pwdView = (TextView) findViewById(R.id.pwd_Text);
		System.out.println("subjectId: "+pwdView.getText());
		
		if(isValidEntry()){
			Intent intent = new Intent(this, HomeActivity.class);
			   
		    intent.putExtra("virified", true);
		    startActivity(intent);
		}
		
	}
	
	private boolean isValidEntry(){
		boolean valid=true;
		TextView pwdView = (TextView) findViewById(R.id.pwd_Text);
		
		if("".equals(pwdView.getText().toString().trim()) ){
			pwdView.setError("Blank Not Allowed");
			valid = false;
		}else if(pwdView.getText().toString().trim().length()<3){
			pwdView.setError("Minimum 3 characters");
			valid = false;
		}else if(!passCodeData.verifyPassCode(pwdView.getText().toString().trim())){
			pwdView.setError("Invalid Password.");
			valid = false;
		}
		
		
		/*List<PasswordEntry> values = passwordDAO.getAccount(acTypeView.getText().toString());
		if(values!=null && values.size()>0){
			acTypeView.setError("Already Exists");
			valid = false;
		}*/
		return valid;
	}
	

}
