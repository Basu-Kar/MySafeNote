package com.ksoft.mynote;

import com.ksoft.data.PassCode;
import com.ksoft.data.PassCodeData;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EnablePwdActivity extends Activity implements View.OnClickListener{

	private PassCodeData passCodeData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enable_pwd);
		Button btn = (Button) findViewById(R.id.save_pwd);
		btn.setOnClickListener(this);
		passCodeData = new PassCodeData(this);
		passCodeData.open();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enable_pwd, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		TextView newPwd = (TextView) findViewById(R.id.new_password);
		TextView hintAnsView = (TextView) findViewById(R.id.hint_ans);
		//System.out.println("new Password: "+newPwd.getText());
		
		if(isValidEntry() && !passCodeData.isPassCodePresent()){
			passCodeData.insertPassCode(new PassCode(newPwd.getText().toString().trim(),hintAnsView.getText().toString().trim()));
			Intent intent = new Intent(this, HomeActivity.class);
			   
		   // intent.putExtra("newPwd", newPwd.getText().toString());
		    startActivity(intent);
		}
	
		
	}
	private boolean isValidEntry(){
		boolean valid=true;
		TextView newPwdView = (TextView) findViewById(R.id.new_password);
		TextView hintAnsView = (TextView) findViewById(R.id.hint_ans);
		
		if("".equals(newPwdView.getText().toString().trim()) ){
			newPwdView.setError("Blank Not Allowed");
			valid = false;
		}else if(newPwdView.getText().toString().trim().length()<3){
			newPwdView.setError("Minimum 3 characters");
			valid = false;
		} else if("".equals(hintAnsView.getText().toString().trim()) ){
			hintAnsView.setError("Blank Not Allowed");
			valid = false;
		}else if(hintAnsView.getText().toString().trim().length()<3){
			hintAnsView.setError("Minimum 3 characters");
			valid = false;
		}
		return valid;
	}
}
