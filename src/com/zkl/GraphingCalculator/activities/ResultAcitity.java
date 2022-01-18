package com.zkl.GraphingCalculator.activities;

import java.lang.reflect.Field;

import com.GraphingCalculator.R;
import com.sunbx.GraphingCalculator.MainActivity;
import com.sunbx.GraphingCalculator.MenupreferenceActivity;
import com.sunbx.GraphingCalculator.calculate.Calculate;
import com.sunbx.GraphingCalculator.calculate.FinalCalculate;
import com.sunbx.GraphingCalculator.convert.ConvertExpression;
import com.sunbx.GraphingCalculator.rebuild.ReBuild;
import com.sunbx.GraphingCalculator.structer.Attribute;
import com.sunbx.GraphingCalculator.structer.MyLinkList;
import com.sunbx.GraphingCalculator.structer.ParseString;
import com.sunbx.GraphingCalculator.sysprint.PrintList;
import com.zkl.GraphingCalculator.ActivityManager.SysApplication;
import com.zkl.GraphingCalculator.draw.MainUI;
import com.zkl.GraphingCalculator.settings.Project;
import com.zkl.GraphingCalculator.settings.SystemConfigeration;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ResultAcitity extends Activity {

	TextView tv1,tv2 = null;
	ConvertExpression ce = new ConvertExpression();
	ParseString ps = new ParseString();
	ReBuild rb = new ReBuild();
	PrintList pl = new PrintList();
	Calculate cl = new Calculate(); 
	MyLinkList<Attribute> resultList = new MyLinkList<Attribute>();
	View mainUI = null;
	String source = null;
	Project project = new Project(this);
	SystemConfigeration systemConfigeration = null;
	//Menu item
	public static final int ITEM0=Menu.FIRST;
	public static final int ITEM1=Menu.FIRST+1;
	public static final int ITEM2=Menu.FIRST+2;
	public static final int ITEM3=Menu.FIRST+3;
	public static final int ITEM4=Menu.FIRST+4;
	public static final int ITEM5=Menu.FIRST+5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SysApplication.getInstance().addActivity(this);  
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		  WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
		
		Bundle bundle = this.getIntent().getExtras();
		source = bundle.getString("result");
		mainUI = new MainUI(this, source);
		getSetting();
		setContentView(mainUI);
		
		//save to history
		systemConfigeration = project.OpenProject();
		systemConfigeration.history.add(source);
		project.SaveProject(systemConfigeration);
	}
	private void getSetting()
	{
		systemConfigeration = project.OpenProject();
		((MainUI)mainUI).pointsDensity = systemConfigeration.pointsDensity;
		((MainUI)mainUI).functionThickness = systemConfigeration.functionThickness;
		((MainUI)mainUI).accuracy = systemConfigeration.accuracy;
		((MainUI)mainUI).isShowGrid = systemConfigeration.isShowGrid;
		((MainUI)mainUI).isShowInformation = systemConfigeration.isShowInformation;
		((MainUI)mainUI).isShowFunctionEquation = systemConfigeration.isShowFunctionEquation;
		((MainUI)mainUI).isShowOriginPointPosition = systemConfigeration.isShowOriginPointPosition;
		((MainUI)mainUI).isShowCartesianLenth = systemConfigeration.isShowCartesianLenth;
		if (systemConfigeration.fontSize > 0) {
			((MainUI)mainUI).myTextSize = systemConfigeration.fontSize;
			((MainUI)mainUI).isNeedGetInitialTextSize = false;
		}
//		else{
//			systemConfigeration.fontSize = ((MainUI)mainUI).myTextSize;
//			project.SaveProject(systemConfigeration);
//		}
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		((MainUI)mainUI).isScreenNeedRedefine = true;
//		 if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {   
//         } 
//		 else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { 
//         } 
		super.onConfigurationChanged(newConfig);
	}
	@Override
	protected void onResume() {
//	 /**
//	  * 设置为横屏
//	  */
//	 if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//	  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//	 }
		//Restart thread
		
		((MainUI)mainUI).isThreadAlive = true;
		Thread thread = new Thread((MainUI)mainUI);
		thread.start();
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		//Stop thread to save energy
		((MainUI)mainUI).isThreadAlive = false;
		super.onPause();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0,ITEM0,0,getResources().getString(R.string.menu_compute)).setIcon(R.drawable.calculate);
		menu.add(0,ITEM1,0,getResources().getString(R.string.menu_history)).setIcon(R.drawable.history);
		menu.add(0,ITEM2,0,getResources().getString(R.string.menu_setting)).setIcon(R.drawable.setting);
		menu.add(0,ITEM3,0,getResources().getString(R.string.menu_help)).setIcon(R.drawable.help);
		menu.add(0,ITEM4,0,getResources().getString(R.string.menu_about)).setIcon(R.drawable.about);
		menu.add(0,ITEM5,0,getResources().getString(R.string.menu_exit)).setIcon(R.drawable.exit1);
		return super.onCreateOptionsMenu(menu);
	}
	EditText edittext_compute = null;
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//compute
		if(item.getItemId() == ITEM0){
			Builder dialog = new AlertDialog.Builder(this);
		    LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.compute_dialog, null);
		    dialog.setView(layout);
		    edittext_compute = (EditText)layout.findViewById(R.id.edittext_compute);
		    edittext_compute.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		    dialog.setPositiveButton(getResources().getString(R.string.compute_compute), new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	try {
		        		float x = Float.valueOf(edittext_compute.getText()+"");
		        		((MainUI)mainUI).computeXByUserInput(x);
		        		//used to close dialog
			        	try {
			        		Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
			        		field.setAccessible(true);
			        		field.set(dialog, true);
			        	} catch (Exception e) {
			        		e.printStackTrace();
			        	} 
					} catch (Exception e) {
						// TODO: handle exception
						Toast toast = Toast.makeText(ResultAcitity.this, getResources().getString(R.string.wrong_expression), Toast.LENGTH_SHORT); 
						toast.show();
						//used to keep open status
						try { 
							Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing"); 
							field.setAccessible(true); 
							field.set(dialog, false); 
						} catch (Exception ex) { 
							ex.printStackTrace(); 
						}
					}
		        }
		    });
		     
		    dialog.setNegativeButton(getResources().getString(R.string.compute_cancel), new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) {
		        	//used to close dialog
		        	try {
		        		Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
		        		field.setAccessible(true);
		        		field.set(dialog, true);
		        	} catch (Exception e) {
		        		e.printStackTrace();
		        	} 
		        }
		    });
		    dialog.show();
        }
		//history
		else if(item.getItemId() == ITEM1){
			Intent intent = new Intent(ResultAcitity.this, HistoryAcitvity.class);
			startActivityForResult(intent,0);
        }
		//setting
		else if(item.getItemId() == ITEM2){
			systemConfigeration = project.OpenProject();
			if(systemConfigeration.fontSize <= 0){
				systemConfigeration.fontSize = ((MainUI)mainUI).myTextSize;
				project.SaveProject(systemConfigeration);
			}
			Intent intent = new Intent(ResultAcitity.this,MenuPreferenceActivity.class);
			startActivityForResult(intent,1);
        }
		//help
		else if(item.getItemId() == ITEM3){
			Intent intent = new Intent(ResultAcitity.this, HelpActivity.class);
			startActivity(intent);
        }
		//about
		else if(item.getItemId() == ITEM4){
			Intent intent = new Intent(ResultAcitity.this, AboutActivity.class);
			startActivity(intent);
        }
		//exit
		else if(item.getItemId() == ITEM5){
			SysApplication.getInstance().exit();
        }
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // TODO Auto-generated method stub
		if(requestCode == 0 && resultCode == RESULT_OK) {
			finish();
		}
		else if(requestCode == 1 && resultCode == RESULT_OK) {
			getSetting();
			((MainUI)mainUI).resetCanvas();
		}
	}
}
