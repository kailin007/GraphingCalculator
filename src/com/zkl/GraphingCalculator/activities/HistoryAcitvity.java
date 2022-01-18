package com.zkl.GraphingCalculator.activities;

import com.GraphingCalculator.R;
import com.sunbx.GraphingCalculator.MainActivity;
import com.zkl.GraphingCalculator.Controllar.SysApplication;
import com.zkl.GraphingCalculator.Controllar.ToastShow;
import com.zkl.GraphingCalculator.draw.MainUI;
import com.zkl.GraphingCalculator.settings.Project;
import com.zkl.GraphingCalculator.settings.SystemConfigeration;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class HistoryAcitvity extends Activity{
	TextView textView_history = null;
//	ScrollView scrollView_history = null;
	RadioGroup radioGroup_history = null; 
	Button button_draw_history = null;
	Button button_clear_history = null;
	Button button_exit_history = null;
	ToastShow toastShow =null;
	Project project = new Project(this);
	SystemConfigeration systemConfigeration = null;
	
	String source = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);
		
		SysApplication.getInstance().addActivity(this); 
		toastShow = new ToastShow(HistoryAcitvity.this);
		
		textView_history = (TextView)findViewById(R.id.textview_search_history);
		radioGroup_history = (RadioGroup)findViewById(R.id.radiogroup_history);
		button_clear_history = (Button)findViewById(R.id.button_clear_history);
		button_draw_history = (Button)findViewById(R.id.button_draw_history);
		button_exit_history = (Button)findViewById(R.id.button_exit_history);
		
		//get resolution
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int shortSide = 0;
		if (metrics.widthPixels>metrics.heightPixels) {
			shortSide = metrics.heightPixels;
		} else {
			shortSide = metrics.widthPixels;
		}
		
		//set textview size = 30
		if (shortSide/24.0f >= 15) {
			textView_history.setTextSize(shortSide/24.0f);
		} else {
			textView_history.setTextSize(15);
		}

		//get history
		systemConfigeration = project.OpenProject();
		for(int i = systemConfigeration.history.size()-1 ; i >= 0 ; i--){
			RadioButton radioButton = new RadioButton(this);
			radioButton.setText(systemConfigeration.history.get(i).substring(0, systemConfigeration.history.get(i).length()-1));
			radioButton.setOnClickListener(radioButtonListener);
			radioGroup_history.addView(radioButton);
			}
		
		//Clear Button
		button_clear_history.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder dialog = new AlertDialog.Builder(HistoryAcitvity.this);
//			    LayoutInflater inflater = (LayoutInflater)HistoryAcitvity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			    LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.compute_dialog, null);
//			    dialog.setView(layout);
				dialog.setTitle(getResources().getString(R.string.history_confirm_dialog_title));
			    dialog.setPositiveButton(R.string.history_confirm_dialog_confirm, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	radioGroup_history.removeAllViews();
			        	systemConfigeration = project.OpenProject();
			        	systemConfigeration.history.clear();
			        	project.SaveProject(systemConfigeration);
			        }
			    });
			    dialog.setNegativeButton(getResources().getString(R.string.history_confirm_dialog_cancel), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			             
			        }
			    });
			    dialog.show();
			}
		});
		
        //cancle button
		button_exit_history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//draw button
		button_draw_history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(source != null){
					//save to history
					systemConfigeration = project.OpenProject();
					systemConfigeration.history.add(source);
					project.SaveProject(systemConfigeration);
					//draw
					Intent intent = new Intent(HistoryAcitvity.this,DrawingAcitity.class);
					Bundle bundle = new Bundle();
					bundle.putString("result", source);
					intent.putExtras(bundle);
					setResult(RESULT_OK);
					startActivity(intent);
					finish();
				}
				else{
					toastShow.ShowToast(getResources().getString(R.string.history_nochoose)); 
				}
			}
		});
	}
	
	OnClickListener radioButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			source = ((RadioButton)v).getText() + "#";
		}
	};
}
