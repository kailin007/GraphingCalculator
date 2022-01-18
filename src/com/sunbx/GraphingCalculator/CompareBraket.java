package com.sunbx.GraphingCalculator;

import android.widget.EditText;

public class CompareBraket {

	int left,right=0;
	String str = null;
	boolean compare(EditText et){
		
		str=et.getText().toString();
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i)==')'){right++;}
			else if (str.charAt(i)=='(') {left++;}
		}
		System.out.println(str);
		System.out.println(right);
		System.out.println(left);
		if(right==left){
			right=left=0;
			return true;
			}
		else {
			right=left=0;
			return false;
		}
	}
}
