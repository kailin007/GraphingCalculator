package com.zkl.GraphingCalculator.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;

public class SystemConfigeration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8009671147599782751L;
	//System configuration
	public int pointsDensity = 10;
	public int functionThickness = 3;
	public boolean isShowGrid = true;
	public boolean isShowInformation = true;
	public boolean isShowFunctionEquation = true;
	public boolean isShowOriginPointPosition = false;
	public boolean isShowCartesianLenth = true;
	public List<String> history = new ArrayList<String>();
	public float fontSize = -1;
	public int accuracy = 2;
}
