package com.example.test;

import com.lidroid.xutils.util.LogUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

public class MainActivity extends Activity{

	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
	EditText et = (EditText) findViewById(R.id.et);
	et.setOnFocusChangeListener(new OnFocusChangeListener() {
		
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus) {
				LogUtils.i("---------------hasFocus-----------------");
			}else {
				LogUtils.i("---------------not hasFocus-----------------");
			}
		}
	});
	
	
}

	
	
	
	
	
	
	
	
	
}
