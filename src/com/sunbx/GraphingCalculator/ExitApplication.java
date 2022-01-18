package com.sunbx.GraphingCalculator;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class ExitApplication extends Application{

	private static ExitApplication instance;
	private List<Activity> activityList = new LinkedList<Activity>();
	
	public static ExitApplication getInstance(){
		if (null == instance) {
			instance = new ExitApplication();
		}
		return instance;
	}
	public void addActivity(Activity activity){
		activityList.add(activity);
	}
	public void exit(){
		for (Activity ay: activityList) {
			ay.finish();
		}
		System.exit(0);
	}
}
