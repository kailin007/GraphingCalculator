package com.zkl.GraphingCalculator.activities;

import com.GraphingCalculator.R;
import com.zkl.GraphingCalculator.Controllar.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.TextView;

public class AboutActivity extends Activity{
	TextView textView_title = null;
	TextView textView_body = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//Òþ²Ø±êÌâ
		setContentView(R.layout.about);
		SysApplication.getInstance().addActivity(this);
		
		textView_title = (TextView)findViewById(R.id.textview_about_title);
		textView_body = (TextView)findViewById(R.id.textview_about_body);
		
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int shortSide = 0;
		if (metrics.widthPixels>metrics.heightPixels) {
			shortSide = metrics.heightPixels;
		} else {
			shortSide = metrics.widthPixels;
		}
		int titleSize = shortSide/36;
		int bodySize = shortSide/48;
		if (titleSize < 15) {
			titleSize = 15;
		}
		if (bodySize < 15) {
			bodySize = 15;
		}
		textView_title.setTextSize(titleSize);
		textView_body.setTextSize(bodySize);
	}

}
