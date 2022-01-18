package com.zkl.GraphingCalculator.activities;

import com.GraphingCalculator.R;
import com.GraphingCalculator.R.layout;
import com.GraphingCalculator.R.menu;
import com.zkl.GraphingCalculator.Controllar.SysApplication;
import com.zkl.GraphingCalculator.settings.Project;
import com.zkl.GraphingCalculator.settings.SystemConfigeration;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

public class MenuPreferenceActivity extends PreferenceActivity {
	Project project = new Project(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preference);
		SysApplication.getInstance().addActivity(this);  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menupreference, menu);
		return true;
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		if (preference.getKey().equals("grid")) {
			SystemConfigeration systemConfigeration = project.OpenProject();
			systemConfigeration.isShowGrid = !systemConfigeration.isShowGrid;
			project.SaveProject(systemConfigeration);
		}
		else if (preference.getKey().equals("information1")) {
			SystemConfigeration systemConfigeration = project.OpenProject();
			systemConfigeration.isShowFunctionEquation = !systemConfigeration.isShowFunctionEquation;
			if (systemConfigeration.isShowFunctionEquation == false 
					&& systemConfigeration.isShowOriginPointPosition == false
					&& systemConfigeration.isShowCartesianLenth == false) {
				systemConfigeration.isShowInformation = false;
			}
			else{
				systemConfigeration.isShowInformation = true;
			}
			project.SaveProject(systemConfigeration);
		}
		else if (preference.getKey().equals("information2")) {
			SystemConfigeration systemConfigeration = project.OpenProject();
			systemConfigeration.isShowOriginPointPosition = !systemConfigeration.isShowOriginPointPosition;
			if (systemConfigeration.isShowFunctionEquation == false 
					&& systemConfigeration.isShowOriginPointPosition == false
					&& systemConfigeration.isShowCartesianLenth == false) {
				systemConfigeration.isShowInformation = false;
			}
			else{
				systemConfigeration.isShowInformation = true;
			}
			project.SaveProject(systemConfigeration);
		}
		else if (preference.getKey().equals("information3")) {
			SystemConfigeration systemConfigeration = project.OpenProject();
			systemConfigeration.isShowCartesianLenth = !systemConfigeration.isShowCartesianLenth;
			if (systemConfigeration.isShowFunctionEquation == false 
					&& systemConfigeration.isShowOriginPointPosition == false
					&& systemConfigeration.isShowCartesianLenth == false) {
				systemConfigeration.isShowInformation = false;
			}
			else{
				systemConfigeration.isShowInformation = true;
			}
			project.SaveProject(systemConfigeration);
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
        	setResult(RESULT_OK);
        }
        return super.onKeyDown(keyCode, event);
    }
	


}
