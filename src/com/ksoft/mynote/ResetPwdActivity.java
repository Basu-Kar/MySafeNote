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

public class ResetPwdActivity extends Activity implements View.OnClickListener{

	private PassCodeData passCodeData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_pwd);
		
		Button btn = (Button) findViewById(R.id.reset_btn);
		btn.setOnClickListener(this);
		passCodeData = new PassCodeData(this);
		passCodeData.open();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reset_pwd, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        	case R.id.reset_menu_pwd_action_id:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	            //Toast.makeText(HomeActivity.this, "Reset Password Selected", Toast.LENGTH_SHORT).show();
	            Intent pwdIntent = new Intent(this, PasswordActivity.class);
	            startActivity(pwdIntent);
	            return true;
            
        }
        return false;
    }

	@Override
	public void onClick(View v) {
		//TextView oldPwdView = (TextView) findViewById(R.id.old_pwd);
		TextView newPwdView = (TextView) findViewById(R.id.new_pwd);
		TextView hintAnsView = (TextView) findViewById(R.id.hint_ans);
		
		if(isValidEntry()){
			//passCodeData.deletePassCode();
			passCodeData.updatePassCode(new PassCode(newPwdView.getText().toString().trim(),hintAnsView.getText().toString().trim()));
			Intent intent = new Intent(this, PasswordActivity.class);
			   
		    startActivity(intent);
		}
		
	}
	
	private boolean isValidEntry(){
		boolean valid=true;
		boolean validPwd=false;
		TextView oldPwdView = (TextView) findViewById(R.id.old_pwd);
		TextView newPwdView = (TextView) findViewById(R.id.new_pwd);
		TextView hintAnsView = (TextView) findViewById(R.id.hint_ans);
		
		
		
		
		/*if("".equals(oldPwdView.getText().toString().trim()) && "".equals(hintAnsView.getText().toString().trim())){
			if("".equals(oldPwdView.getText().toString().trim())){
				oldPwdView.setError("Blank Not Allowed");
			}else if("".equals(hintAnsView.getText().toString().trim())){
				hintAnsView.setError("Blank Not Allowed");
			}
			
			valid = false;
		}else if(oldPwdView.getText().toString().trim().length()<3 && hintAnsView.getText().toString().trim().length()<3){
			//if(oldPwdView.getText().toString().trim().length()<3){
				oldPwdView.setError("Minimum 3 characters");
			//}else if(hintAnsView.getText().toString().trim().length()<3){
				hintAnsView.setError("Minimum 3 characters");
			//}
			
			valid = false;
		}else if(!passCodeData.verifyPassCode(oldPwdView.getText().toString().trim())
				|| !(!"".equals(hintAnsView.getText().toString().trim()) && !passCodeData.verifyHintAns(hintAnsView.getText().toString().trim()))
				){
			if("".equals(hintAnsView.getText().toString().trim())){
				
				valid = false;
			}
			if(!passCodeData.verifyPassCode(oldPwdView.getText().toString().trim())){
				oldPwdView.setError("Either old password or hint answer is not correct");
				valid = false;
			}
		}else */if("".equals(newPwdView.getText().toString().trim())){
			newPwdView.setError("Blank Not Allowed");
			valid = false;
		}else if(newPwdView.getText().toString().trim().length()<3){
			newPwdView.setError("Minimum 3 characters");
			valid = false;
		}else if("".equals(oldPwdView.getText().toString().trim()) && "".equals(hintAnsView.getText().toString().trim())){
				oldPwdView.setError("Blank Not Allowed");
				hintAnsView.setError("Blank Not Allowed");
				valid = false;
		}else if(oldPwdView.getText().toString().trim().length()<3 && hintAnsView.getText().toString().trim().length()<3){
				oldPwdView.setError("Minimum 3 characters");
				hintAnsView.setError("Minimum 3 characters");
				valid = false;
		}else if(!passCodeData.verifyPassCode(oldPwdView.getText().toString().trim()) && !passCodeData.verifyHintAns(hintAnsView.getText().toString().trim())){
			oldPwdView.setError("Both old password and hint answer are not correct");
			valid = false;
		}else if(passCodeData.verifyPassCode(oldPwdView.getText().toString().trim()) && !"".equals(hintAnsView.getText().toString().trim())
				&& hintAnsView.getText().toString().trim().length()<3){
			hintAnsView.setError("Minimum 3 characters");
		}
		
		/*List<PasswordEntry> values = passwordDAO.getAccount(acTypeView.getText().toString());
		if(values!=null && values.size()>0){
			acTypeView.setError("Already Exists");
			valid = false;
		}*/
		return valid;
	}
	


}
