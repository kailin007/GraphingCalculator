package com.sunbx.GraphingCalculator;



import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.GraphingCalculator.R;
import com.sunbx.GraphingCalculator.calculate.SimpleCalculate;
import com.sunbx.GraphingCalculator.check.CheckError;
import com.zkl.GraphingCalculator.Controllar.SysApplication;
import com.zkl.GraphingCalculator.Controllar.ToastShow;
import com.zkl.GraphingCalculator.activities.AboutActivity;
import com.zkl.GraphingCalculator.activities.HelpActivity;
import com.zkl.GraphingCalculator.activities.DrawingAcitity;
import com.zkl.GraphingCalculator.draw.*;
import com.zkl.GraphingCalculator.settings.Project;
import com.zkl.GraphingCalculator.settings.SystemConfigeration;

import android.os.Bundle;
import android.R.color;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.Layout;
import android.text.style.AlignmentSpan.Standard;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnLongClickListener{

	String outOfBound = null;
	bitmaptest bt = new bitmaptest();
	EditText et = null;
	Editable edit = null;
	TextView tv = null;
	Button btn01,btn021,btn022,btn03,btn04,btn05,btn06,btn07=null;
	Button btn11,btn12,btn13,btn14,btn15,btn16,btn17=null;
	Button btn21,btn22,btn23,btn24,btn25,btn26,btn27=null;
	Button btn31,btn32,btn33,btn34,btn35,btn36,btn37=null;
	Button btn41,btn42,btn43,btn44,btn45,btn46,btn47=null;
	Button btncal = null;
	Button simple_btn_11,simple_btn_12,simple_btn_13,simple_btn_14,
				simple_btn_21,simple_btn_22,simple_btn_23,simple_btn_24,
				simple_btn_31,simple_btn_32,simple_btn_33,simple_btn_34,
				simple_btn_41,simple_btn_42,simple_btn_43,simple_btn_44,
				simple_btn_51,simple_btn_52,simple_btn_53,simple_btn_54,
				simple_btn_61,simple_btn_62,simple_btn_63,simple_btn_64,
				simple_btn_71,simple_btn_72,simple_btn_73,simple_btn_74,
				simple_btn_81,simple_btn_82,simple_btn_83,simple_btn_84,
				simple_btn_91,simple_btn_92 = null;
	Button[] StandardBtn = null;
	Button[] lightgrayButton,redButton,darkgrayButton,orangeButton = null;
	Button btnequal;
	LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7=null;
	Button[] simpleBtn = null;
	Button[] complexBtn = null;
	Button[] allBtn = null;
	LinearLayout[] allLinearLayout = null;
	CompareBraket cb = new CompareBraket();
	String  f = "";
	int width,height;
	boolean IsPORTRAIT = true;
	SimpleCalculate sc;
	boolean IsEditResult = false;
	boolean IsNumberPointAvalible = false;
	CheckError ce = new CheckError();
	BigDecimal bg = null;
	Bitmap bmblack,bmblack_rotate,bmbrown,bmbrown_rotate,bmred,bmred_rotate,bmorange,bmorange_rotate;
	BitmapDrawable bmblack_zoomed,bmblack_rotate_zoomed,bmbrown_zoomed,bmbrown_rotate_zoomed,
							bmred_zoomed,bmred_rotate_zoomed,bmorange_zoomed,bmorange_rotate_zomed;
	//menu item
	public static final int ITEM0=Menu.FIRST;
	public static final int ITEM1=Menu.FIRST+1;
	public static final int ITEM2=Menu.FIRST+2;
	//toast
	ToastShow toastShow = null;
	String fontPath = "fonts/STHeiti-Light.ttc";
//	@Override
//	protected void onResume() {
//	 /**
//	  * 设置为横�?
//	  */
//	 if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
//	  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//	 }
//	 super.onResume();
//	}
	
	
	private boolean isInSimpleBtn(Button btn){
		for(int i = 0;i < simpleBtn.length;i++){
			if(btn == simpleBtn[i]){return true;}
		}
		return false;
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		 if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {   
        	 IsPORTRAIT = false;
        	 CreatePanel();
         } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { 
        	 IsPORTRAIT = true;
        	 CreatePanel();
         } 
		super.onConfigurationChanged(newConfig);
	}
	private boolean isInComplexBtn(Button btn){
		for(int i = 0;i < complexBtn.length;i++){
			if(btn == complexBtn[i]){return true;}
		}
		return false;
	}
	boolean isInStandardBtn(Button btn){
		for(int i = 0;i < StandardBtn.length;i++){
			if(btn == StandardBtn[i]){return true;}
		}
		return false;
	}
	boolean isInLightGrayBtn(Button btn){
		for(int i = 0;i < lightgrayButton.length;i++){
			if(btn == lightgrayButton[i]){return true;}
		}
		return false;
	}
	boolean isInRedBtn(Button btn) {
		for (int i = 0; i < redButton.length; i++) {
			if (btn == redButton[i]) {return true;}
			}
		return false;
		}
	boolean isInDarkGrayBtn(Button btn) {
		for (int i = 0; i < darkgrayButton.length; i++) {
			if (btn == darkgrayButton[i]) {return true;}
			}
		return false;
		}
	boolean isInOrangeBtn(Button btn){
		for (int i = 0; i < orangeButton.length; i++) {
			if (btn == orangeButton[i]) {return true;}
			}
		return false;
	}

	private boolean IsFunction(char c) {
		if(c >= 'a' && c <= 'z' && c != 'x' && c != 'e'){
			return true;
		}
		else return false;
	}
	
	private class SimpleOnTouchEvent implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (isInLightGrayBtn((Button)v)) {
				if (tv.getText().toString().equals(outOfBound)) {
					tv.setText("0");
				}
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmblack_rotate_zoomed);
					v.setBackgroundResource(R.drawable.lightgray_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmblack_zoomed);
					v.setBackgroundResource(R.drawable.lightgray_button_style);
				}
			}
			else if (isInRedBtn((Button)v)) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmred_rotate_zoomed);
					v.setBackgroundResource(R.drawable.red_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmred_zoomed);
					v.setBackgroundResource(R.drawable.red_button_style);
				}
			}
			else if (isInDarkGrayBtn((Button)v)) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmbrown_rotate_zoomed);
					v.setBackgroundResource(R.drawable.darkgray_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmbrown_zoomed);
					v.setBackgroundResource(R.drawable.darkgray_button_style);
				}
			}
			else if (isInOrangeBtn((Button)v)) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmbrown_rotate_zoomed);
					v.setBackgroundResource(R.drawable.orange_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmbrown_zoomed);
					v.setBackgroundResource(R.drawable.orange_button_style);
				}
			}
//			// 0
//			else if (v == simple_btn_91) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.black_zero_rotate), width/2, height/11));
//				}
//				else if (event.getAction() == MotionEvent.ACTION_UP) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.black_zero), width/2, height/11));
//				}
//			}
//			// equal
//			else if (v == btnequal) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.orange_equal_rotate), width/4, (int) (height/5.5f)));
//				}
//				else if (event.getAction() == MotionEvent.ACTION_UP) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.orange_equal), width/4, (int) (height/5.5f)));
//				}
//			}
			return false;
		}
		}
	
	private class ButtonOnTouchListener implements OnTouchListener{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (isInLightGrayBtn((Button)v)) {
				//LightGray Buttons
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmblack_rotate_zoomed);
					v.setBackgroundResource(R.drawable.lightgray_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmblack_zoomed);
					v.setBackgroundResource(R.drawable.lightgray_button_style);
				}
			}
			else if (isInRedBtn((Button)v)) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmred_rotate_zoomed);
					v.setBackgroundResource(R.drawable.red_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmred_zoomed);
					v.setBackgroundResource(R.drawable.red_button_style);
				}
			}
			else if (isInDarkGrayBtn((Button)v)) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmbrown_rotate_zoomed);
					v.setBackgroundResource(R.drawable.darkgray_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmbrown_zoomed);
					v.setBackgroundResource(R.drawable.darkgray_button_style);
				}
			}
			else if (isInOrangeBtn((Button)v)) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					//v.setBackgroundDrawable(bmbrown_rotate_zoomed);
					v.setBackgroundResource(R.drawable.orange_button_style_down);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP) {
					//v.setBackgroundDrawable(bmbrown_zoomed);
					v.setBackgroundResource(R.drawable.orange_button_style);
				}
			}
//			// ( )
//			else if (v == btn021 ||  v == btn022) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.black_bracket_rotate), width/14, height/8));
//				}
//				else if (event.getAction() == MotionEvent.ACTION_UP) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.black_bracket), width/14, height/8));
//				}
//			}
//			// equal
//			else if (v == btncal) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.orange_draw_rotate), width/7*2, (height-10)/8));
//				}
//				else if (event.getAction() == MotionEvent.ACTION_UP) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.orange_draw), width/7*2, (height-10)/8));
//				}
//			}
//			//x
//			else if (v == btn43) {
//				if (event.getAction() == MotionEvent.ACTION_DOWN) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.orange_rotate), width/7, (height-10)/8));
//				}
//				else if (event.getAction() == MotionEvent.ACTION_UP) {
//					v.setBackgroundDrawable(bt.ZoomImage(
//							BitmapFactory.decodeResource(getResources(), R.drawable.orange), width/7, (height-10)/8));
//				}
//			}
			return false;
		}
		
	}
/**
 * 所有的Button OnClick事件
 * @author lenovo
 *
 */
	private class SimpleButtonClick implements OnClickListener{
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (isInSimpleBtn((Button)v)) {
			edit = et.getEditableText();
			String strtemp = "";
				switch (((Button)v).getId()) {
				case R.id.simple_11:
				{
					if(cb.compare(et)){
						edit.insert(et.getSelectionStart(), "()");
						et.setSelection(et.getSelectionStart()-1);
						}
					else {
						edit.insert(et.getSelectionStart(), "(");
					}
				}
				break;
				//TODO
				// 数字
				default:{
					strtemp = ((Button)v).getText().toString();
				}
					break;
				}
				if (IsEditResult) {
					et.requestFocus();
					//et.setFocusableInTouchMode(IsEditResult);
					et.setText("");
					et.setTextColor(Color.WHITE);
					IsEditResult = false;
					edit =et.getEditableText();
					edit.insert(et.getSelectionStart(), strtemp);
				}
				else {
					edit =et.getEditableText();
					edit.insert(et.getSelectionStart(), strtemp);
				}
		}
		//standard button
		else if (isInStandardBtn((Button)v)) {
			edit = et.getEditableText();
			String strtemp="";
			switch (((Button)v).getId()) {
			case R.id.simple_53:
				strtemp = "/";
				break;
			case R.id.simple_54:
				strtemp = "*"; 
				break;
			case R.id.simple_24:
				strtemp = "^"; 
				break;
			default:strtemp = ((Button)v).getText().toString();
				break;
			}
			if (IsEditResult) {
				strtemp = tv.getText().toString()+strtemp;
				et.requestFocus();
				et.setText("");
				et.setSelection(et.getText().toString().length());
				et.setTextColor(Color.WHITE);
				IsEditResult = false;
				edit =et.getEditableText();
				edit.insert(et.getSelectionStart(), strtemp);
			}
			else {
				edit =et.getEditableText();
				edit.insert(et.getSelectionStart(), strtemp);
			}
		}
		//complex button
		else if (isInComplexBtn((Button)v)) {
			edit = et.getEditableText();
			String strtemp = "";

			if (IsEditResult) {
				strtemp = ((Button)v).getText().toString()+"("+tv.getText().toString()+")";
				et.requestFocus();
				et.setText("");
				et.setSelection(et.getText().toString().length());
				et.setTextColor(Color.WHITE);
				IsEditResult = false;
				edit =et.getEditableText();
				edit.insert(et.getSelectionStart(), strtemp);
			}
			else {
				strtemp = ((Button)v).getText().toString()+"()";
				edit =et.getEditableText();
				edit.insert(et.getSelectionStart(), strtemp);
			}
			et.setSelection(et.getSelectionStart()-1);
		}
		//special button
		else{
			String temp = "";
			temp = et.getText().toString();
			String strtemp="";
			edit = et.getEditableText();
			switch (((Button)v).getId()) {
			// del
			case R.id.simple_13:{
				if (IsEditResult) {
					edit = et.getEditableText();
					et.setTextColor(Color.WHITE);
					IsEditResult = false;
				}
				temp = et.getText().toString();
				temp = temp.substring(0,et.getSelectionStart());
				System.out.println("temp:"+temp+"length"+temp.length());
				System.out.println(temp);
				if (!(temp.length() == 0)) {
					if (!IsFunction(temp.charAt(temp.length()-1))) {
						edit.delete(et.getSelectionStart()-1, et.getSelectionStart());
					}
					else{
						while (!(temp.length() == 0) && IsFunction(temp.charAt(temp.length()-1))) {
							System.out.println(temp.charAt(temp.length()-1));
							edit.delete(et.getSelectionStart()-1,et.getSelectionStart());
							temp = temp.substring(0,et.getSelectionStart());
						}
					}
				}
			}
				break;
				//AC
			case R.id.simple_14:
				IsEditResult = false;
				et.setTextColor(Color.WHITE);
				et.setText("");
				edit = et.getEditableText();
				et.setSelection(et.getText().toString().length());
				tv.setText("0");
				break;
				//x^2
			case R.id.simple_22:{
				if (IsEditResult) {
					strtemp = tv.getText().toString();
					et.requestFocus();
					et.setText("");
					et.setSelection(et.getText().toString().length());
					et.setTextColor(Color.WHITE);
					IsEditResult = false;
					edit = et.getEditableText();
				}
				edit.insert(et.getSelectionStart(), "("+strtemp+")^2");
				et.setSelection(et.getSelectionStart()-3);
			}
			break;
			//x^(1/3)
			case R.id.simple_23:{
				if (IsEditResult) {
					strtemp = tv.getText().toString();
					et.requestFocus();
					et.setText("");
					et.setSelection(et.getText().toString().length());
					et.setTextColor(Color.WHITE);
					IsEditResult = false;
					edit = et.getEditableText();
				}
					edit.insert(et.getSelectionStart(), "(" + strtemp + ")^(1/3)");
					et.setSelection(et.getSelectionStart()-7);
			}
			break;
			default:
				break;
			}
		}
	}
		
	}
	
	private class ButtonClick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (isInSimpleBtn(((Button)v))) {
				if (IsEditResult) {
					
				}
				String strtemp = ((Button)v).getText().toString();
				edit =et.getEditableText();
	            edit.insert(et.getSelectionStart(), strtemp);
			}
			else if (isInComplexBtn(((Button)v))) {
				String strtemp = ((Button)v).getText().toString()+"()";
				edit =et.getEditableText();
                edit.insert(et.getSelectionStart(), strtemp);
                et.setSelection(et.getSelectionStart()-1);
			}
			else {
				edit =et.getEditableText();
				switch (((Button)v).getId()) {
				//(
				case R.id.btn021:{
					if(cb.compare(et)){
						edit.insert(et.getSelectionStart(), "()");
						et.setSelection(et.getSelectionStart()-1);
						}
					else {
						edit.insert(et.getSelectionStart(), "(");
					}
				}
				break;
				//AC
				case R.id.btn04:
					et.setText("f(x)=");
					edit = et.getEditableText();
					et.setSelection(et.getText().toString().length());
					break;
				//Del
				case R.id.btn03:{
					String temp = et.getText().toString();
					System.out.println(temp);
					temp = temp.substring(0,et.getSelectionStart());
					if (!IsFunction(temp.charAt(temp.length()-1))) {
						if(temp.charAt(temp.length()-1) == '='){}
						else{edit.delete(et.getSelectionStart()-1, et.getSelectionStart());}
					}
					else{
							while (IsFunction(temp.charAt(temp.length()-1))) {
								System.out.println(temp.charAt(temp.length()-1));
								edit.delete(et.getSelectionStart()-1,et.getSelectionStart());
								temp = temp.substring(0,et.getSelectionStart());
							}
					}
				}
					break;
				//*
				case R.id.btn34:
				{
					edit.insert(et.getSelectionStart(), "*");
				}
					break;
					//   /
				case R.id.btn44:
				{
					edit.insert(et.getSelectionStart(), "/");
				}
				break;
				// |x|
				case R.id.btn45:
				{
					edit.insert(et.getSelectionStart(), "abs()");
	                et.setSelection(et.getSelectionStart()-1);
				}
				break;
				case R.id.btn36:{
					edit.insert(et.getSelectionStart(), "()^(1/3)");
					et.setSelection(et.getSelectionStart()-7);
				}
				break;
				
				case R.id.btn37:{
					edit.insert(et.getSelectionStart(), "()^2");
					et.setSelection(et.getSelectionStart()-3);
				}
				break;
				default:
					break;
				}
			}
		}
	}
	/**
	 * createpanel
	 */
	void CreatePanel(){
		// TODO Auto-generated method stub
		if (!IsPORTRAIT) {
			IsEditResult = false;
			setContentView(R.layout.panel);
			Resources res = getResources();
		    Drawable drawable = res.getDrawable(R.drawable.bgcolor);
			this.getWindow().setBackgroundDrawable(drawable);
			DisplayMetrics dm = new DisplayMetrics();   
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        width = dm.widthPixels;
	        height = dm.heightPixels;
			et =  (EditText)findViewById(R.id.tv1);
			Typeface typeface = Typeface.createFromAsset(getAssets(), fontPath);
			et.setTypeface(typeface);
			
			edit =et.getEditableText();
			et.setSelection(et.getText().toString().length());
			et.requestFocus();
			

			btn01 = (Button)findViewById(R.id.btn01);
			btn021 = (Button)findViewById(R.id.btn021);
			btn022 = (Button)findViewById(R.id.btn022);
			btn03 = (Button)findViewById(R.id.btn03);
			btn04 = (Button)findViewById(R.id.btn04);
			btn05 = (Button)findViewById(R.id.btn05);
			btn06 = (Button)findViewById(R.id.btn06);
			btn07 = (Button)findViewById(R.id.btn07);
			
			btn11 = (Button)findViewById(R.id.btn11);
			btn12 = (Button)findViewById(R.id.btn12);
			btn13 = (Button)findViewById(R.id.btn13);
			btn14 = (Button)findViewById(R.id.btn14);
			btn15 = (Button)findViewById(R.id.btn15);
			btn16 = (Button)findViewById(R.id.btn16);
			btn17 = (Button)findViewById(R.id.btn17);
			
			btn21 = (Button)findViewById(R.id.btn21);
			btn22 = (Button)findViewById(R.id.btn22);
			btn23 = (Button)findViewById(R.id.btn23);
			btn24 = (Button)findViewById(R.id.btn24);
			btn25 = (Button)findViewById(R.id.btn25);
			btn26 = (Button)findViewById(R.id.btn26);
			btn27 = (Button)findViewById(R.id.btn27);
			
			btn31 = (Button)findViewById(R.id.btn31);
			btn32 = (Button)findViewById(R.id.btn32);
			btn33 = (Button)findViewById(R.id.btn33);
			btn34 = (Button)findViewById(R.id.btn34);
			btn35 = (Button)findViewById(R.id.btn35);
			btn36 = (Button)findViewById(R.id.btn36);
			btn37 = (Button)findViewById(R.id.btn37);
			
			btn41 = (Button)findViewById(R.id.btn41);
			btn42 = (Button)findViewById(R.id.btn42);
			btn43 = (Button)findViewById(R.id.btn43);
			btn44 = (Button)findViewById(R.id.btn44);
			btn45 = (Button)findViewById(R.id.btn45);
			btn46 = (Button)findViewById(R.id.btn46);
			btn47 = (Button)findViewById(R.id.btn47);
			
			btncal = (Button)findViewById(R.id.btncal);
			
			layout1 = (LinearLayout)findViewById(R.id.layout1);
			layout2 = (LinearLayout)findViewById(R.id.layout2);
			layout3 = (LinearLayout)findViewById(R.id.layout3);
			layout4 = (LinearLayout)findViewById(R.id.layout4);
			layout5 = (LinearLayout)findViewById(R.id.layout5);
			layout6 = (LinearLayout)findViewById(R.id.layout6);
			layout7 = (LinearLayout)findViewById(R.id.layout7);
			
			//�?��的按钮，只需要将按钮上的内容打印到框�?
			simpleBtn =new Button[] {btn01,btn022,
											btn11,btn12,btn13,btn14,
											btn21,btn22,btn23,btn24,btn27,
											btn31,btn32,btn33,
											btn41,btn42,btn43,btn46};
			//复杂的按钮，�?��将按钮上的内容加括号后光标位置减�?��印到框中
			//另特殊按钮，�?��加到上边switch�?
			complexBtn = new Button[] {btn05,btn06,btn07,
											  		 btn15,btn16,btn17,
											  		 btn25,btn26,
											  		 btn35,          
											                      		btn47};
			//�?��的按�?
			allBtn = new Button[]{btn01,btn021,btn022,btn03,btn04,btn05,btn06,btn07,
											btn11,btn12,btn13,btn14, btn15,btn16,btn17,
											btn21,btn22,btn23,btn24,btn25,btn26,btn27,
											btn31,btn32,btn33,btn34,btn35,btn36, btn37,
											btn41,btn42,btn43,btn44,btn45,btn46,btn47};
			//red buttons
			redButton = new Button[]{btn03,btn04};
			//black buttons
			lightgrayButton = new Button[]{btn11,btn12,btn13,
													 btn21,btn22,btn23,
													 btn31,btn32,btn33,
													 btn41,btn42};
			//darkgray buttons
			darkgrayButton = new Button[]{btn01,btn021,btn022,
																	btn05,btn06,btn07,
																	btn15,btn16,btn17,
																	btn25,btn26,btn27,
																	btn35,btn36,btn37,
																	btn45,btn46,btn47};
			//orange buttons
			orangeButton = new Button[]{btn14,btn24,btn34,btn43,btn44,btncal};
			
			for (int i = 0; i < allBtn.length; i++) {
				allBtn[i].setWidth(width/7);
				allBtn[i].setHeight((height-10)/8);
				//allBtn[i].setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
				//allBtn[i].setTextColor(Color.WHITE);
				allBtn[i].setTypeface(typeface);
				allBtn[i].setTextSize(height/36f);
			}
			allBtn[1].setWidth(width/14);
			allBtn[2].setWidth(width/14);
			btncal.setWidth(width/7*2);
			btncal.setHeight((height-10)/8);
			btncal.setTypeface(Typeface.create(Typeface.SERIF, Typeface.ITALIC));
			btncal.setTextColor(Color.WHITE);
			btncal.setTextSize(height/36f);
			et.setHeight(height/4);
			//text size = 27
			et.setTextSize(width/26.7f);
			
//			//处理背景图片
//			bmblack_zoomed = bt.ZoomImage(bmblack, width/7, (height-10)/8);
//			bmblack_rotate_zoomed = bt.ZoomImage(bmblack_rotate, width/7, (height-10)/8);
//			bmbrown_zoomed = bt.ZoomImage(bmbrown, width/7, (height-10)/8);
//			bmbrown_rotate_zoomed = bt.ZoomImage(bmbrown_rotate, width/7, (height-10)/8);
//			bmred_zoomed = bt.ZoomImage(bmred, width/7, (height-10)/8);
//			bmred_rotate_zoomed = bt.ZoomImage(bmred_rotate, width/7, (height-10)/8);
//			//为按钮贴背景图
//			//red
//			for (int i = 0; i < redButton.length; i++) {
//				redButton[i].setBackgroundDrawable(bmred_zoomed);
//			}
//			//black
//			for (int i = 0; i < blackButton.length; i++) {
//				blackButton[i].setBackgroundDrawable(bmblack_zoomed);
//			}
//			//brown
//			for (int i = 0; i < brownButton.length; i++) {
//				brownButton[i].setBackgroundDrawable(bmbrown_zoomed);
//			}
//			btn43.setBackgroundDrawable(bt.ZoomImage(bmorange, width/7, (height-10)/8));
//			btn021.setBackgroundDrawable(bt.ZoomImage(BitmapFactory.decodeResource(getResources(), R.drawable.black_bracket), width/14, (height-10)/8));
//			btn022.setBackgroundDrawable(bt.ZoomImage(BitmapFactory.decodeResource(getResources(), R.drawable.black_bracket), width/14, (height-10)/8));
//			btncal.setBackgroundDrawable(bt.ZoomImage(BitmapFactory.decodeResource(getResources(), R.drawable.orange_draw), width/7*2, (height-10)/8));
//			
			
			for (int i = 0; i < allBtn.length; i++) {
				allBtn[i].setOnTouchListener(new ButtonOnTouchListener());
			}
			btncal.setOnTouchListener(new ButtonOnTouchListener());
			
			if (android.os.Build.VERSION.SDK_INT <= 10) {  
                et.setInputType(InputType.TYPE_NULL);  
            } else {  
                getWindow().setSoftInputMode(  
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
                try {  
                    Class<EditText> cls = EditText.class;  
                    Method setShowSoftInputOnFocus;  
                    setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
                            boolean.class);  
                    setShowSoftInputOnFocus.setAccessible(true);  
                    setShowSoftInputOnFocus.invoke(et, false);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }   
  }  
			
			//设置按钮相应
			//平方
			for (int i = 0; i < allBtn.length; i++) {
				allBtn[i].setOnClickListener(new ButtonClick());
			}
			btncal.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String resultString = null;
					String fx = null;
					System.out.println(System.currentTimeMillis());
					Intent intent = new Intent(MainActivity.this,DrawingAcitity.class);
					Bundle bundle = new Bundle();
					resultString = et.getText().toString();
					fx = resultString;
					fx = fx.substring(0,5);
					resultString = resultString.substring(5);
					resultString += "#";
					System.out.println("resultstring:"+resultString);
					if(!cb.compare(et)){
						toastShow.ShowToast(getResources().getString(R.string.mismatched_bracket));
					}
					else if (!fx.equals("f(x)=")) {
						toastShow.ShowToast(getResources().getString(R.string.wrong_expression));
					}
					else if (!ce.IsHasError(resultString)) {
						toastShow.ShowToast(getResources().getString(R.string.wrong_expression));
					}
					else if (resultString.equals("#")) {
						toastShow.ShowToast(getResources().getString(R.string.no_expression));
					}
					else{
						//save to history
						Project project = new Project(MainActivity.this);
						SystemConfigeration systemConfigeration = project.OpenProject();
						systemConfigeration.history.add(resultString);
						project.SaveProject(systemConfigeration);
						//start activity
						bundle.putString("result", resultString);
						intent.putExtras(bundle);
						startActivity(intent);
						System.out.println("start drawing activity");
					}
				}
			});
			
			et.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					if (android.os.Build.VERSION.SDK_INT <= 10) {  
		                et.setInputType(InputType.TYPE_NULL);  
		            } else {  
		                getWindow().setSoftInputMode(  
		                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
		                try {  
		                    Class<EditText> cls = EditText.class;  
		                    Method setShowSoftInputOnFocus;  
		                    setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
		                            boolean.class);  
		                    setShowSoftInputOnFocus.setAccessible(true);
		                    setShowSoftInputOnFocus.invoke(et, false);  
		                } catch (Exception e) {  
		                    e.printStackTrace();  
		                }   
		            }
					return true;
				}
			});
			
			edit = et.getEditableText();
			et.setText("f(x)=");
			edit = et.getEditableText();
			et.setSelection(et.getText().toString().length());
			
		}
		else if (IsPORTRAIT) {
			// TODO portrait
			sc = new SimpleCalculate();
			setContentView(R.layout.panelsimple);
			Resources res = getResources();
		    Drawable drawable = res.getDrawable(R.drawable.bgcolor);
			this.getWindow().setBackgroundDrawable(drawable);
			DisplayMetrics dm = new DisplayMetrics();   
	        getWindowManager().getDefaultDisplay().getMetrics(dm);
	        width = dm.widthPixels;
	        height = dm.heightPixels;
	    	Typeface typeface = Typeface.createFromAsset(getAssets(), fontPath);
			et =  (EditText)findViewById(R.id.et2);
			et.setTypeface(typeface);
			et.requestFocus();
			edit =et.getEditableText();
			et.setSelection(et.getText().toString().length());
			tv = (TextView)findViewById(R.id.tv2);
			

			simple_btn_11 = (Button)findViewById(R.id.simple_11);
			simple_btn_12 = (Button)findViewById(R.id.simple_12);
			simple_btn_13 = (Button)findViewById(R.id.simple_13);
			simple_btn_14 = (Button)findViewById(R.id.simple_14);
			
			simple_btn_21 = (Button)findViewById(R.id.simple_21);
			simple_btn_22 = (Button)findViewById(R.id.simple_22);
			simple_btn_23 = (Button)findViewById(R.id.simple_23);
			simple_btn_24 = (Button)findViewById(R.id.simple_24);

			simple_btn_31 = (Button)findViewById(R.id.simple_31);
			simple_btn_32 = (Button)findViewById(R.id.simple_32);
			simple_btn_33 = (Button)findViewById(R.id.simple_33);
			simple_btn_34 = (Button)findViewById(R.id.simple_34);
			
			simple_btn_41 = (Button)findViewById(R.id.simple_41);
			simple_btn_42 = (Button)findViewById(R.id.simple_42);
			simple_btn_43 = (Button)findViewById(R.id.simple_43);
			simple_btn_44 = (Button)findViewById(R.id.simple_44);
			
			simple_btn_51 = (Button)findViewById(R.id.simple_51);
			simple_btn_52 = (Button)findViewById(R.id.simple_52);
			simple_btn_53 = (Button)findViewById(R.id.simple_53);
			simple_btn_54 = (Button)findViewById(R.id.simple_54);
			
			simple_btn_61 = (Button)findViewById(R.id.simple_61);
			simple_btn_62 = (Button)findViewById(R.id.simple_62);
			simple_btn_63 = (Button)findViewById(R.id.simple_63);
			simple_btn_64 = (Button)findViewById(R.id.simple_64);
			
			simple_btn_71 = (Button)findViewById(R.id.simple_71);
			simple_btn_72 = (Button)findViewById(R.id.simple_72);
			simple_btn_73 = (Button)findViewById(R.id.simple_73);
			simple_btn_74 = (Button)findViewById(R.id.simple_74);
			
			simple_btn_81 = (Button)findViewById(R.id.simple_81);
			simple_btn_82 = (Button)findViewById(R.id.simple_82);
			simple_btn_83 = (Button)findViewById(R.id.simple_83);
			simple_btn_84 = (Button)findViewById(R.id.simple_84);
			
			simple_btn_91 = (Button)findViewById(R.id.simple_91);
			simple_btn_92 = (Button)findViewById(R.id.simple_92);
			
			btnequal = (Button)findViewById(R.id.simple_84);
			
			allBtn = new Button[]{simple_btn_11,simple_btn_12,simple_btn_13,simple_btn_14,
					simple_btn_21,simple_btn_22,simple_btn_23,simple_btn_24,
					simple_btn_31,simple_btn_32,simple_btn_33,simple_btn_34,
					simple_btn_41,simple_btn_42,simple_btn_43,simple_btn_44,
					simple_btn_51,simple_btn_52,simple_btn_53,simple_btn_54,
					simple_btn_61,simple_btn_62,simple_btn_63,simple_btn_64,
					simple_btn_71,simple_btn_72,simple_btn_73,simple_btn_74,
					simple_btn_81,simple_btn_82,simple_btn_83,simple_btn_84,
					simple_btn_91,simple_btn_92};
			/**
			 * 抹掉之前的内容,添加内容
			 */
			simpleBtn = new Button[]{simple_btn_11,simple_btn_12,
					simple_btn_51,simple_btn_52,
					simple_btn_61,simple_btn_62,simple_btn_63,
					simple_btn_71,simple_btn_72,simple_btn_73,
					simple_btn_81,simple_btn_82,simple_btn_83,
					simple_btn_91,simple_btn_92};
			
			StandardBtn = new Button[]{
					simple_btn_53,simple_btn_54,simple_btn_64,simple_btn_74,simple_btn_24
			};
			
			/**
			 * 添加指定文本到制定位置
			 */
			complexBtn = new Button[]{simple_btn_21,
					simple_btn_31,simple_btn_32,simple_btn_33,simple_btn_34,
					simple_btn_41,simple_btn_42,simple_btn_43,simple_btn_44};
			
			lightgrayButton = new Button[]{simple_btn_51,simple_btn_52,
					simple_btn_61,simple_btn_62,simple_btn_63,
					simple_btn_71,simple_btn_72,simple_btn_73,
					simple_btn_81,simple_btn_82,simple_btn_83,
					simple_btn_91,simple_btn_92
			};
			redButton = new Button[]{simple_btn_13,simple_btn_14};
			orangeButton = new Button[]{simple_btn_53,simple_btn_54,simple_btn_64, simple_btn_74, simple_btn_84};
			darkgrayButton = new Button[]{simple_btn_11,simple_btn_12,
					simple_btn_21,simple_btn_22,simple_btn_23,simple_btn_24,
					simple_btn_31,simple_btn_32,simple_btn_33,simple_btn_34,
					simple_btn_41,simple_btn_42,simple_btn_43,simple_btn_44};
			for (int i = 0; i < allBtn.length; i++) {
				allBtn[i].setWidth(width/4);
				allBtn[i].setHeight((height-10)/11);
				allBtn[i].setTypeface(typeface);
				allBtn[i].setTextSize(width/36f);
			}
			et.setHeight((int) ((height-10)/11*1.2));
			tv.setHeight((int) ((height-10)/11*0.8));
			simple_btn_91.setWidth(width/2);
			simple_btn_84.setHeight((height-10)/11*2);
			btnequal.setTypeface(typeface);
			btnequal.setTextSize(width/36f);
			et.setTextSize(height/24f);
			tv.setTextSize(height/32f);
			
			//设置文本框没有键盘
			if (android.os.Build.VERSION.SDK_INT <= 10) {  
                et.setInputType(InputType.TYPE_NULL);  
            } else {  
                getWindow().setSoftInputMode(  
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
                try {  
                    Class<EditText> cls = EditText.class;  
                    Method setShowSoftInputOnFocus;  
                    setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
                            boolean.class);  
                    setShowSoftInputOnFocus.setAccessible(true);
                    setShowSoftInputOnFocus.invoke(et, false);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }   
            }
			et.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					// TODO Auto-generated method stub
					if (android.os.Build.VERSION.SDK_INT <= 10) {  
		                et.setInputType(InputType.TYPE_NULL);  
		            } else {  
		                getWindow().setSoftInputMode(  
		                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
		                try {  
		                    Class<EditText> cls = EditText.class;  
		                    Method setShowSoftInputOnFocus;  
		                    setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
		                            boolean.class);  
		                    setShowSoftInputOnFocus.setAccessible(true);
		                    setShowSoftInputOnFocus.invoke(et, false);  
		                } catch (Exception e) {  
		                    e.printStackTrace();  
		                }   
		            }
					return true;
				}
			});
			/**
			 * set button back
			 */
//			//设置按钮背景图片
//			bmblack_zoomed = bt.ZoomImage(bmblack, width/4, (height-10)/11);
//			bmblack_rotate_zoomed = bt.ZoomImage(bmblack_rotate, width/4, (height-10)/11);
//			bmbrown_zoomed = bt.ZoomImage(bmbrown, width/4, (height-10)/11);
//			bmbrown_rotate_zoomed = bt.ZoomImage(bmbrown_rotate, width/4, (height-10)/11);
//			bmred_zoomed = bt.ZoomImage(bmred, width/4, (height-10)/11);
//			bmred_rotate_zoomed = bt.ZoomImage(bmred_rotate, width/4, (height-10)/11);
//			// set buttons backgroud pic
//			for (int i = 0; i < lightgrayButton.length; i++) {
//				lightgrayButton[i].setBackgroundDrawable(bmblack_zoomed);
//			}
//			for (int i = 0; i < redButton.length; i++) {
//				redButton[i].setBackgroundDrawable(bmred_zoomed);
//			}
//			for (int i = 0; i < darkgrayButton.length; i++) {
//				darkgrayButton[i].setBackgroundDrawable(bmbrown_zoomed);
//			}
//			// 0 
//			simple_btn_91.setBackgroundDrawable(bt.ZoomImage(BitmapFactory.decodeResource(getResources(), R.drawable.black_zero), width/2, (height-10)/11));
//			//等号
//			btnequal.setBackgroundDrawable(bt.ZoomImage(BitmapFactory.decodeResource(getResources(), R.drawable.orange_equal), width/4,(int) ((height-10)/5.5f)));
//			
			
			
			
			
			// set on touch event
			for (int i = 0; i < allBtn.length; i++) {
				allBtn[i].setOnTouchListener(new SimpleOnTouchEvent());
			}
			//设置按钮相应
			//平方
			for (int i = 0; i < allBtn.length; i++) {
				allBtn[i].setOnClickListener(new SimpleButtonClick());
			}
			//注册长监听事件
			simple_btn_13.setOnLongClickListener(MainActivity.this);
			
			btnequal.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String resultString = null;
					System.out.println(System.currentTimeMillis());
					resultString = et.getText().toString();
					resultString += "#";
					float result;
					System.out.println("result:"+resultString);
					if(!cb.compare(et)){
						toastShow.ShowToast(getResources().getString(R.string.mismatched_bracket));
					}
					else if (resultString.equals("#")) {
						toastShow.ShowToast(getResources().getString(R.string.no_expression));
					}
					else if (!ce.IsHasError(resultString)) {
						toastShow.ShowToast(getResources().getString(R.string.wrong_expression));
					}
					else{
						result = (float) sc.finalCalculate(resultString);
						tv.setText(result+"");
						//et.setFocusableInTouchMode(IsEditResult);
						et.setTextColor(Color.GRAY);
						IsEditResult = true;
					}
					if (tv.getText().toString().equals("Infinity")) {
						tv.setText(outOfBound);
					}
				}
			});
			et.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if (android.os.Build.VERSION.SDK_INT <= 10) {  
		                et.setInputType(InputType.TYPE_NULL);  
		            } else {  
		                getWindow().setSoftInputMode(  
		                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
		                try {  
		                    Class<EditText> cls = EditText.class;  
		                    Method setShowSoftInputOnFocus;  
		                    setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
		                            boolean.class);  
		                    setShowSoftInputOnFocus.setAccessible(true);  
		                    setShowSoftInputOnFocus.invoke(et, false);  
		                } catch (Exception e) {  
		                    e.printStackTrace();  
		                }
		            }
					et.setTextColor(Color.WHITE);
					IsEditResult = false;
				}
			});
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//used for close all activity
		 bmblack = BitmapFactory.decodeResource(getResources(), R.drawable.black);
		 bmbrown = BitmapFactory.decodeResource(getResources(), R.drawable.brown);
		 bmred = BitmapFactory.decodeResource(getResources(), R.drawable.red);
		 bmblack_rotate = BitmapFactory.decodeResource(getResources(), R.drawable.black_rotate);
		 bmbrown_rotate = BitmapFactory.decodeResource(getResources(), R.drawable.brown_rotate);
		 bmred_rotate = BitmapFactory.decodeResource(getResources(), R.drawable.red_rotate);
		 bmorange = BitmapFactory.decodeResource(getResources(), R.drawable.orange);
		 bmorange_rotate = BitmapFactory.decodeResource(getResources(), R.drawable.orange_rotate);
		 //get value
		 outOfBound = getResources().getString(R.string.out_of_bound);
		SysApplication.getInstance().addActivity(this); 
		toastShow = new ToastShow(MainActivity.this);
		 if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {   
        	 IsPORTRAIT = false;
        	 CreatePanel();
         } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) { 
        	 IsPORTRAIT = true;
        	 CreatePanel();
         }
		CreatePanel();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0,ITEM0,0,getResources().getString(R.string.menu_help)).setIcon(R.drawable.help);
		menu.add(0,ITEM1,0,getResources().getString(R.string.menu_about)).setIcon(R.drawable.about);
		menu.add(0,ITEM2,0,getResources().getString(R.string.menu_exit)).setIcon(R.drawable.exit);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//help
		if(item.getItemId() == ITEM0){
			Intent intent = new Intent(MainActivity.this, HelpActivity.class);
			startActivity(intent);
        }
		//about
		else if(item.getItemId() == ITEM1){
			Intent intent = new Intent(MainActivity.this, AboutActivity.class);
			startActivity(intent);
        }
		//exit
		else if(item.getItemId() == ITEM2){
			SysApplication.getInstance().exit();
        }
		return super.onOptionsItemSelected(item);
	}
	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		if (v == simple_btn_13) {
			et.setTextColor(Color.WHITE);
			et.setText("");
			edit = et.getEditableText();
		}
		return false;
	}
	
	private long mExitTime;
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                        Toast.makeText(this, getResources().getString(R.string.press_again_to_exit), Toast.LENGTH_SHORT).show();
                        mExitTime = System.currentTimeMillis();
                } else {
                	SysApplication.getInstance().exit();
                }
                return true;
        }
        return super.onKeyDown(keyCode, event);
}
	
	
}
