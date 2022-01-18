package com.zkl.GraphingCalculator.activities;

import com.GraphingCalculator.R;
import com.zkl.GraphingCalculator.Controllar.SysApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class HelpActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//Òþ²Ø±êÌâ
		SysApplication.getInstance().addActivity(this); 
		
		LinearLayout linearLayout = new LinearLayout(this);
		ScrollView scrollView = new ScrollView(this);
		TextView textView_help = new TextView(this);
		textView_help.setTextSize(24);
		textView_help.setText(getResources().getString(R.string.help_document));
		scrollView.addView(textView_help);
		linearLayout.addView(scrollView);
		setContentView(linearLayout);
	}

}
