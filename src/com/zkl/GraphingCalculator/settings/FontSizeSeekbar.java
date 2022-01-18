package com.zkl.GraphingCalculator.settings;

import com.GraphingCalculator.R;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class FontSizeSeekbar extends DialogPreference implements
OnSeekBarChangeListener {

private SeekBar seekBar;
private TextView textView;
//private Context context;
//private int progress = 10;
Project project = null;
int value = 0;
int minSize = 9,maxSize = 72;
public FontSizeSeekbar(Context context, AttributeSet attrs) {
super(context, attrs);
//this.context = context;
project = new Project(context);
// TODO Auto-generated constructor stub
}

@Override
protected void onBindDialogView(View view) {
// TODO Auto-generated method stub
	super.onBindDialogView(view);
	seekBar = (SeekBar) view.findViewById(R.id.seekBar3);
	textView = (TextView) view.findViewById(R.id.seekbar_tv3);
	seekBar.setOnSeekBarChangeListener(this);
	seekBar.setMax(maxSize - minSize);
	
	
	SystemConfigeration systemConfigeration = project.OpenProject();
	value = (int)systemConfigeration.fontSize;
	seekBar.setProgress(value - minSize);
	//setProgress((int)systemConfigeration.fontSize);
}

@Override
protected void onDialogClosed(boolean positiveResult) {
	// TODO Auto-generated method stub
    if (positiveResult) {
        //this.getOnPreferenceChangeListener().onPreferenceChange(this, progress);
    	SystemConfigeration systemConfigeration = project.OpenProject();
    	systemConfigeration.fontSize = value;
    	project.SaveProject(systemConfigeration);
    }
}
@Override
public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	// TODO Auto-generated method stub
	//this.progress = progress;
	setProgress(progress);
	textView.setText(value+"");
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
	return seekBar.getProgress() - minSize;
}
public void setProgress(int progress){
	//this.progress = progress;
	value = progress + minSize;
	//seekBar.setProgress(value);
}
}
