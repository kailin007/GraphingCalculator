package com.sunbx.GraphingCalculator.SeekBar;

import com.GraphingCalculator.R;
import com.zkl.GraphingCalculator.settings.Project;
import com.zkl.GraphingCalculator.settings.SystemConfigeration;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ThicknessSeekBar extends DialogPreference implements
OnSeekBarChangeListener {

private SeekBar seekBar;
private TextView textView;
Project project = null;
public ThicknessSeekBar(Context context, AttributeSet attrs) {
super(context, attrs);
project = new Project(context);
// TODO Auto-generated constructor stub
}

@Override
protected void onBindDialogView(View view) {
// TODO Auto-generated method stub
super.onBindDialogView(view);
seekBar = (SeekBar) view.findViewById(R.id.seekBar2);
textView = (TextView) view.findViewById(R.id.seekbar_tv2);
seekBar.setOnSeekBarChangeListener(this);
SystemConfigeration systemConfigeration = project.OpenProject();
setProgress(systemConfigeration.functionThickness);
}

@Override
protected void onDialogClosed(boolean positiveResult) {
	// TODO Auto-generated method stub
    if (positiveResult) {
        //this.getOnPreferenceChangeListener().onPreferenceChange(this, progress);
    	SystemConfigeration systemConfigeration = project.OpenProject();
    	systemConfigeration.functionThickness = seekBar.getProgress();
    	project.SaveProject(systemConfigeration);
    }
}
@Override
public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	// TODO Auto-generated method stub
	if (progress < 1) {
		setProgress(1);
	}
	else{
		textView.setText(progress + "/10");
	}
}

@Override
public void onStartTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
	
}
@Override
public void onStopTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
	
}
public int getProgress(){
	//return progress;
	return seekBar.getProgress();
}
public void setProgress(int progress){
	//this.progress = progress;
	seekBar.setProgress(progress);
}
}
